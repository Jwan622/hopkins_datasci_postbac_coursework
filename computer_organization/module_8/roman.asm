# From Rome to Arabia MIPS
# By Jeffrey Wan, Oct 2019

.globl main

.data
	buffer: .space 30   		# This is 30 bytes, each character is a byte, so this is probably overkill
	promptIntro: .asciiz "This program will read in Roman Numerals and output Arabic Integer values. Type in your roman input or 0 to end the program.\n"
	promptInputRoman: .asciiz "Input the Roman Numeral. Valid Roman numerals include I, V, X, L, C, D, M.\nYour input: "
	promptyProgramEnd: .asciiz "This is the end of the program."
	newline: .asciiz "\n"
	promptOutput1: .asciiz "The Integer value of "
	promptOutput2: .asciiz " is: "
	
.text
main:
	# Intro to program
	li $v0, 4                       # 4 is the sys call for printing string
        la $a0, promptIntro
        syscall
        
        li $v0, 4
        la $a0, promptInputRoman
        syscall

     	jal _readString
     	move $s0, $a0                   # now the address of the user input is at $s0
     	# to save us a couple iterations, we have a newline and null character in our input to remove first
     	li $t0, 0    			# initialize the index of the input string before removing newlines
     	jal _removeAnnoyingAssNewLine
     	
     	li $s7, 0			# set the tally register to 0
     	li $s2, 0 			# $s2 will eventually hold the integer value of an input char. Used for comparison later.
     	li $s4, 0   			# this is the register that will store the integer value of the character evaluated in the loop before. Used to determine if we add or subtract the current integer value from the long-running tally.
        li $s3, 0			# $s3 will be the loop index. Once this hits 0 and we've evaluated the character at index 0, we're done.
       	jal getLength
       	j evaluateInput          		# the end of the input string has been reach, continue on. $s3 holds the length of the string now.
       	
_removeAnnoyingAssNewLine: 
   	lbu $a3, buffer($t0)    		# Load character at index
    	addi $t0, $t0, 1      			# Increment index
    	bnez $a3, _removeAnnoyingAssNewLine     # Loop until the end of string is reached
    	beq $a1, $t0, dontCleanInput    	# Do not remove \n when string = maxlength
    	subiu $t0, $t0, 2    			# If above not true, Backtrack index to '\n'
    	sb $0, buffer($t0)    			# Add the terminating character in its place
    	jr $ra
       
     
getLength:
	addu $s1, $s0, $s3              	# $s1 is the address of the character and $s3 will keep getting incremented one byte at a time until the end of input is reached, $s0 is starting address of user input
        lbu $t0, 0($s1)                 	# load the next byte into a register to see if we've reached the end yet
        bne $t0, $zero, increment          	# if the character is null, the end of the string has been reached. Until then, we increment
	jr $ra
     
increment:
	# $s3 will hold the length of the input string
	addi $s3, $s3, 1                	# increment the index
	j getLength                		# go back to the loop
	
	
# Let's evaluate the roman numeral input starting with the last character.
evaluateInput:  
        addu $s1, $s0, $s3 			# $s0 is the address of the input, $s3 is the length of the input so $s1 is the address of the last char
        lbu $t0, 0($s1)  			# load the character as a byte integer value into $t0. I think unsigned is what we want so it doesn't pad with 1s or 0s.
        addi $t1, $0, 48 			# load ASCII value of 0 into $t1
        move $s5, $t0				# store the character that we're iterating through in a permanent register to help us quickly iterate. We'll see below.
        bne $t0, $t1, mapRomanToInteger 	# keep converting input until the end of the input is reached which == the null character 
        j exit 					# if the input is 0, just end it all.
        
        
# t0 has the character that we're iterating through
# t1 has ASCII value that we're checking the character against
mapRomanToInteger:
charInSixties:
	bge $s5, 70, charInSeventies 			# If the input char is greater than 70, we pass it to the next character evaluator label
        li $t1, 67 					# ASCII 67 == C and we store it in $t1
        beq $s5, $t1, convertCharByteToHundy 		# if the character == C, we then convert it to 100
        li $t1, 68 					# ASCII 68 == D and we store it in  $t1
        beq $s5, $t1, convertCharByteToFiveHundy 	# if the character == D, we then convert it to 500
      
charInSeventies:
	bge $s5, 80, charInEighties 			# If the input char is greater than 80, we pass it to the next character evaluator label
        li $t1, 73 					# ASCII 73 == I and we store it in $t1
        beq $s5, $t1, convertCharByteToOne 		# if the character == I, we then convert it to 1
        li $t1, 76 					# ASCII 73 == L and we store it in $t1
        beq $s5, $t1, convertCharByteToFiddy 		# if the character == L, we then convert it to 50
        li $t1, 77 					# ASCII 73 == M and we store it in $t1
        beq $s5, $t1, convertCharByteToThousand 	# if the character == M, we then convert it to 1000
        
charInEighties:     
	bge $s5, 90, charInNineties 			# same shit
	li $t1, 86 					# ASCII 86 == V and we store it in $t1
        beq $s5, $t1, convertCharByteToFive
        li $t1, 88 					# ASCII 88 == X and we store it in $t1
        beq $s5, $t1, convertCharByteToTen
        
charInNineties:
        bge $s5, 100, charInHundies  			# again, same shit, but dealing with lowercase
        li $t1, 99 					# ASCII 99 == c and we store it in $t1
        beq $s5, $t1, convertCharByteToHundy
        
charInHundies:
        li $t1, 100 					# ASCII 100 == d and we store it in $t1
        beq $s5, $t1, convertCharByteToFiveHundy
        li $t1, 105 					# ASCII 105 == i and we store it in $t1
        beq $s5, $t1, convertCharByteToOne
        li $t1, 108 					# ASCII 108 == l and we store it in $t1
        beq $s5, $t1, convertCharByteToFiddy
        li $t1, 109 					# ASCII 109 == m and we store it in $t1
        beq $s5, $t1, convertCharByteToThousand
        li $t1, 118 					# ASCII 118 == v and we store it in $t1
        beq $s5, $t1, convertCharByteToFive
        li $t1, 120 					# ASCII 120 == x and we store it in $t1
        beq $s5, $t1, convertCharByteToTen
        j catchAllChars
        

catchAllChars:        
#if you're here, it means you didn't branch earlier
	add $s4, $zero, 0   	# initialize the $s4 which holds the previous evaluated integer value register back to 0
	jal continue
	j evaluateInput
		        
convertCharByteToOne:      
	li $s2, 1 # we store the value of 1 into $s2. This is used to both determine if we add or subtract from the tally and we use it to change the tally.
        jal tally
        j evaluateInput
        
convertCharByteToFive:       
	li $s2, 5 # we store the value of 5 into $s2. This is used to both determine if we add or subtract from the tally and we use it to change the tally.
	jal tally
        j evaluateInput
        
convertCharByteToTen:        
	li $s2, 10 # we store the value of 10 into $s2. This is used to both determine if we add or subtract from the tally and we use it to change the tally.
        jal tally
        j evaluateInput			
			
convertCharByteToFiddy:      
	li $s2, 50 # same shit as above again
        jal tally
        j evaluateInput
         
convertCharByteToHundy:   
	li $s2, 100
        jal tally
        j evaluateInput
        
convertCharByteToFiveHundy:    
	li $s2, 500
        jal tally
        j evaluateInput
        
convertCharByteToThousand:   
	li $s2, 1000
        jal tally
        j evaluateInput
        
# s7 is going to hold the running integer total of the Roman numerals
tally:
# Check to see if we should add or subtract first by omparing $s2 and $s4 ($s4 is the previous evaluated char's integer value)
addToTally:       
        blt $s2, $s4, subtractFromTally 	# If current value is lt the 'next' value like in 'iv', we need to subtract from the tally
        add $s7, $s7, $s2 			# if the current value is greater than the 'next' value like in 'vi', we need to add to tally $s7 
        j continue 				# jump to the continue function

subtractFromTally:   
	sub $s7, $s7, $s2 # The next string is smaller, perform subtraction 
	j continue # jump to the continue function

continue:
	beq $s3, $0, output 			# $ if we've reached the beginning of the input, we can exit and output $s7 our tally
        addi $s3, $s3, -1 			# decrement the index of the input loop.
        move $s4, $s2 				# Look at the `addToTally` label. we save the 'next' ASCII value in $s4 to determine if 'previous' value is less than or greater to determine whether to add or subtract from tally $s7
        jr $ra 					# return to the register $ra
      
_readString:
    	li $v0, 8 				# syscall 8 reads input strng
    	la $a0, buffer 				# create the buffer's worth of byte space
    	addi $a1, $zero, 20 			# allot the byte space for input string
    	syscall
    	jr   $ra
    	
dontCleanInput:
	jr $ra
    	
output:	
  	# Output
        li $v0, 4
        la $a0, promptOutput1
        syscall
        
        li $v0, 4
        move $a0, $s0                   	# output Roman numeral
        syscall
        
        li $v0, 4
        la $a0, promptOutput2			# sandwiching the fucking input took more time than I thought it would because of the damned newline at the end of the input string. GAhhhh.
        syscall
        
        li $v0, 1
        move $a0, $s7                   	# output answer which is in $s7
        syscall
        
        li, $v0, 4
        la $a0, newline
        syscall
        
        j main 					# loop the program again. User can end it all by typing 0
        
exit:
	li $v0, 4 				# end this damned program
        la $a0, promptyProgramEnd 		# the text for ending program
        syscall 				# make a syscall to the kernel
	li $v0, 10 				# terminate the program
        syscall 				# make a syscall to the kernel 
    	
        
                         
