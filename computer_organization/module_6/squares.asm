.globl main
main: # sum of integers from 1 to 100
.data
str: .asciiz "The sum of the squares of the integers 1 ... 100 is "
stopped:
.asciiz "\nStopped."
.text
add $t0, $zero, $zero # I is zero. $t0 is = 0 at this point.
add $s4, $zero, $zero # Sum is zero. $s4 is = 0 at this point
addi $t1, $zero, 100 # set the limit value (100). $t1 = 100.
loop:
addi $t0, $t0, 1 # $t0 = $t0 + 1.

mult $t0 $t0
mflo $t3 

add $s4, $s4, $t3 # sum = sum + $t3 which = the squared digit

blt $t0, $t1, loop # if $t0 < 100 loop to do again

#print the sum of squares string
addi $v0, $zero, 4 # store the print syscall in $v0
la $a0, str # store the text for str in $a0
syscall # call opsys
addi $v0, $zero, 1 # print integer
add $a0, $zero, $s4 # store the integer sum $s4 in $a0 for syscall
syscall # call opsys
addi $v0, $zero, 4 # print string
la $a0, stopped # the text for output
syscall # call opsys
addi $v0, $zero, 10 # finished .. stop .. return
syscall # to the Operating System