# Lottery Odds MIPS
# By Jeffrey Wan, Oct 2019

.globl main

.data
	promptPoolLarge: .asciiz "Input the size of large pool: "
	promptSelectLarge: .asciiz "Input the number of matching numbers required from the large pool: "
	promptPoolSmall: .asciiz "Input the size of smaller second pool: "
	promptSelectSmall: .asciiz "Input the  number matching numbers required from the small pool: "
	promptOdds: .asciiz "The odds of winning are 1 in "
	promptyProgramEnd: .asciiz "This is the end of the program!"
	newline: .asciiz "\n"
	check: .asciiz "value is:"

.text

main:
	# Testing
	# addi $t1, $zero, 6
	# addi $t2, $zero, 2
	# addi $t3, $zero, 8
	# addi $t4, $zero, 3
	# addi $t5, $zero, 4
	# addi $t6, $zero, 5
	
	
	# Configure the size of the large pool
	li $v0, 4                       # 4 is the sys call for printing string
        la $a0, promptPoolLarge
        syscall
                
        li $v0, 5                       # 5 is the sys call for inputing an integer
        syscall
        move $t1, $v0           

        # Configure the number of matches required from for large pool
        li $v0, 4                       # 4 is the sys call for printing string
        la $a0, promptSelectLarge
        syscall
                
        li $v0, 5                       # 5 is the sys call for inputing an integer
        syscall
	move $t2, $v0     

        # Configure the size of the second smaller pool
        li $v0, 4                       # same thing again, 4 is the sys call for printing a string
	la $a0, promptPoolSmall
	syscall
                
	li $v0, 5                       # Same thing again, 5 is the sys call for inputing a number
	syscall
                
	move $t3, $v0

	# Configure the number of matches required from the second smaller pool
	li $v0, 4
	la $a0, promptSelectSmall
	syscall
                
	li $v0, 5
	syscall
	move $t4, $v0    

 	li  $v0, 4
	la  $a0, newline
	syscall             

	# Large pool (n-r)
	sub $t5, $t1, $t2 

	# Small pool (n-r)
	sub $t6, $t3, $t4
	

	# factorial for matches required of large pool (r!). This is the denominator later.
	move $a0, $t2
  	jal factrl
	move $s0, $v0

	# factorial for matches required of small pool (r!). This is the denominator later.
	move $a0, $t4
	jal factrl
	move $s1, $v0
	

	# Call function while for large pool
	move $a0, $t1 # $a0 == size of large pool
	move $a1, $t2 # $a1 == number of matches required of large pool
	move $a2, $t5 # $a2 = n - r
	jal ComputeNumerator
	move $s2, $v0

	# Call function while for second pool
	move $a0, $t3
	move $a1, $t4
	move $a2, $t6
	jal ComputeNumerator
	move $s3, $v0

	# Call function operation for large pool
	move $a0, $s2
	move $a1, $s0
	jal divide
	move $s6, $v0

	# Call function operation for second pool
	move $a0, $s3
	move $a1, $s1
	jal divide
	move $s7, $v0
  
	# Multiply combinations of both pools
	mul $s5, $s6, $s7 # we multiply to get the odds of BOTH occurring
	
	li $v0, 4
	la $a0, promptOdds
	syscall  

	li $v0, 1
	move $a0, $s5 # s5 has the odds of both occuring
	syscall

	li  $v0, 4
	la  $a0, newline
	syscall 

	li $v0, 4
	la $a0, promptyProgramEnd
	syscall  

	#  Exit this damned program
	li $v0,10          # 10 == syscall for exit
	syscall

###########################################
# Given n, in register $a0, calculate n! and store the reuslt in 4v0

factrl: 

	sw $ra, 4($sp) # save the return address
  	sw $a0, 0($sp) # save the current value of n
 	addi $sp, $sp, -8 # move stack pointer
	slti $t0, $a0, 2 # save 1 iteration, n=0 or n=1; n!=1
	beq $t0, $zero, L1 # not, calculate n(n-1)!
	addi $v0, $zero, 1 # n=1; n!=1
	jr $ra # now multiply

L1: 
	addi $a0, $a0, -1 # n = n-1
	
	jal factrl # now (n-1)!
	
	addi $sp, $sp, 8 # reset the stack pointer
	lw $a0, 0($sp) # fetch saved (n-1)
	lw $ra, 4($sp) # fetch return address
	mul $v0, $a0, $v0 # multiply (n)*(n-1)
	jr $ra # return value n!


ComputeNumerator:
        # $a0 == n, $a1 == r, $a2 = n - r
	sw $ra, 4($sp) # save the return address
	sw $a0, 0($sp) # $a0
	addi $sp, $sp, -8 # move stack pointer
	
	bgt $a0, $a2, L2 # if n > n - k, go to L2
	addi $v0, $zero, 1 # n = 1
	addi $sp, $sp, 8 # I have to do this because at this point, we stored a number we do not want stored and the stack pointer is 
	# 8 below the wrong number. So we have to go up 16 to get back to the correct number. This all stems from the problem that 
	# the factorial algorithm is done incorrectly... we're storing values above teh stack pointer before allocating the space. 
	# We also allocate after storing so the stack point is too far away from the values it's supposed to work with.
	jr $ra # now do the abbreviated factorial

L2: 
	addi $a0, $a0, -1 # n = n-1
	jal ComputeNumerator # $a0 now == (n-1)
	
	addi $sp, $sp, 8 # reset the stack pointer
	lw $a0, 0($sp) # fetch saved (n-1)
	lw $ra, 4($sp) # fetch return address
	mul $v0, $a0, $v0 # multiply (n)*(n-1)
	jr $ra # return value


divide:

	div $v0, $a0, $a1
	jr $ra
	
	
	
####### Debug
# You can use this function to debug a value quickly. Just replace <register to debug> with a register
#	li $v0, 4
#	la $a0, check
#	syscall
#	
#	li $v0, 1
#	move $a0, <register to debug>
#	syscall
#	
#	li $v0, 4
#	la $a0, newline
#	syscall