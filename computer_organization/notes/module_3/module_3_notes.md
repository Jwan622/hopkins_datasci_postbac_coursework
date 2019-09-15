# module 3:

---

## 2.1-2.3 (on performance):

#### Notes

> The translation from C to MIPS assembly language instructions is performed by the compiler.

>  For now, we will use $s0, $s1, . . . for registers that correspond to variables in C and Java programs and $t0, $t1, . . . for temporary registers needed to compile the program into MIPS instructions.

> The processor can keep only a small amount of data in registers, but computer memory contains billions of data elements. Hence, data structures (arrays and structures) are kept in memory.

> As explained above, arithmetic operations occur only on registers in MIPS instructions; thus, MIPS must include instructions that transfer data between memory and registers. Such instructions are called data transfer instructions. To access a word in memory, the instruction must supply the memory address. Memory is just a large, single-dimensional array, with the address acting as the index to that array, starting at 0.

#### Additional Notes**


What is the base register of an array:
>  In this case the base  register is where the base address of the array A (address of A[0]) is stored . Usually only the base address of arrays are stored so the remaining elements of the array can be easily accessed by using offset. Also these addresses will be in a linear order .so we can easily calculate the remaining addresses using base adress

#### Formulas

#### Outside notes

#### Definitions
