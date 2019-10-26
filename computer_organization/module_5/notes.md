# module 1:

---

## 1.6 (on performance):

#### Notes

#### Additional Notes**

#### Lecture Notes

so remember slt at, t3, t2. if t3 < t2, at = 1, else at = 0
pseudoinstruction
1.
 blt t4, t5, end  =

it equals:
 slt at, t4, t5 
 bne at, $zero, end 

and

2.
ble t2, t3, next  =

it equals:
slt at, t3, t2
 beq at, $zero, next 

slt is stricly equal

3.
bgt t4, t5, continue

it equals:

slt $at, $t5, $t4
bne $at, $zero, next ?

the diff between blt adn bne is flipping the order of comparison.


here's how to do a while loop generally:
python:
t1 = 0 
t2 = 5
 while x < 5:
 print("Joe is the best teacher!")

mips:
 while:
  beq t1, t2 finish
 ...
 .. .
  ...
 addi t1, t1, 1
 j while
   finish …..

#### Formulas

#### Outside notes

#### Definitions

