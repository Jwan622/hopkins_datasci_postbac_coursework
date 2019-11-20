Given the array address calculation method above, and this Array declaration:
X,Y : ARRAY [1..15,1..21] INTEGER
Generate the quadruples for this program code fragment:
Note: J is a declared integer variable
FOR I := 1 TO 15 DO
X[I,2*J+1] := Y[I, 2*J]
Note: The quadruples (about 20) are created as the fragment is parsed. The compiler will not
optimize the addressing calculation. That is another exercise.



(1) := #1 Indx
(2) := #1 lwr1
(3) := #1 lwr2
(4) := #21 upr2
(5) BGT Indx #15 (27)     # if index is greater than 15, go to (26)
(6) - Indx lwr1 i1        # s1 - lwr1
(7) + lwr2 #1 i2          # lwr2 + 1
(8) - upr2 i2  i3         # this line is = upr2 - (lwr2 + 1)
(9) * #2 J i4             # 2 * J
(10) + i4 #1  i5           # this line is = 2 * J + 1
(11) - i5 lwr2 i6         # s2 - lwr2
(12) * i1 i3 i7
(13) + i7 i6 i8           #[(s1 - lower1) * (upper2 - lower2 + 1) + (s2 - lower2)]
(14) * i8 #4 i9           # multiply by 4, the integer word size in bytes. This is the address for I, 2*J+1
(15) - Indx lwr i10       # s1 - lwr1, can be optimized later on because it’s the same as (5)
(16) + lwr2 #1 i11        # lwr2 + 1
(17) - upr2 i11 i12       # this line is = upr2 - (lwr2+1), can be optimized later because it’s teh same as (7)
(18) * #2 J i13           # this is 2*J, can be optimized because we can just reuse the register from (8)
(19) - i13 lwr2 i14       # s2 - lwr2
(20) * i10 i12 i15
(21) + i15 i14 i16
(22) * i16 #4 i17         # multiply by 4, the integer word size in bytes. This is the address for I, 2*J
(23) := Y[i17] X[i9]
(24) + #1 Indx i18
(25) := i18 Indx
(26) JMP (4)
(27)