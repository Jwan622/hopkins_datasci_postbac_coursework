Given this code fragment in an Intermediate file of a Compiler:
(1) := #1 Indx
(2) BGT Indx #8 (20)
(3) - Indx #1 i1
(4) * i1 #10 i2
(5) * #3 HAL i3
(6) - i3 #1 i4
(7) - i4 #1 i5
(8) + i2 i5 i6
(9) * i6 #4 i7
(10) - Indx #1 i8
(11) * i8 #10 i9
(12) * #3 HAL i10
(13) - i10 #1 i11
(14) + i9 i11 i12
(15) * i12 #4 i13
(16) := Y[i13] X[i7]
(17) + #1 Indx i14
(18) := i14 Indx
(19) JMP (2)
(20)
Optimize the code.
Indicate, by line number, which quadruples need to be moved,
modified, or deleted.

Answer:

(1) := #1 Indx
(2) BGT Indx #8 (16)
(3) - Indx #1 i1
(4) * i1 #10 i2
(5) * #3 HAL i3
(6) - i3 #1 i4
(7) - i4 #1 i5
(8) + i2 i5 i6
(9) * i6 #4 i7
(10) + i2 i14 i12
(11) * i12 #4 i13
(12) := Y[i13] X[i7]
(13) + #1 Indx i14
(14) := i14 Indx
(15) JMP (2)
(16)


Could be removed and replaced with earlier registers:
(10) - Indx #1 i8
(11) * i8 #10 i9
(12) * #3 HAL i10
(13) - i10 #1 i11