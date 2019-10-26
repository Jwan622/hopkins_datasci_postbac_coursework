def parse_rtype(instruction):
    # parse 32 bits using Python slicing according to instruction format

    # use 'get_instruction' to get the name of the instruction
    # use 'get_register' to get the appropriate register names

    # return the properly formatted instruction
	op = instruction[0:6]
	rs = instruction[6:11]
	rt = instruction[11:16]
	rd = instruction[16:21]
	func = instruction[26:]

	func_assembly, rs_assembly, rt_assembly, rd_assembly = get_instruction(op, "r", func), get_register(rs), get_register(rt), get_register(rd)

	return create_assembly(func_assembly, rd=rd_assembly, rs=rs_assembly, rt=rt_assembly, type="r")


def create_assembly(func_assembly, rs=None, rt=None, rd=None, type=None, constant=None):
	if type == "r":
		if func_assembly == "jr":
			return f"{func_assembly} {rs}"
		return f"{func_assembly} {rd}, {rs}, {rt}"
	if type == "i":
		if func_assembly == "lw" or func_assembly == "sw":
			return f"{func_assembly} {rt}, {constant}({rs})"
		return f"{func_assembly} {rt}, {rs}, {constant}"
	if type == "j":
		return f"{func_assembly} {constant}"


def parse_itype(instruction):
    # parse 32 bits using Python slicing according to instruction format

    # use 'get_instruction' to get the name of the instruction
    # use 'get_register' to get the appropriate register names

    # return the properly formatted instruction
	op = instruction[0:6]
	rs = instruction[6:11]
	rt = instruction[11:16]
	constant = instruction[16:]

	func_assembly, rs_assembly, rt_assembly, constant_assembly = get_instruction(op, "i"), get_register(rs), get_register(rt), int(constant, 2)

	return create_assembly(func_assembly, rs=rs_assembly, rt=rt_assembly, constant=constant_assembly, type="i")


def parse_jtype(instruction):
	func = instruction[0:6]
	constant = instruction[6:]

	func_assembly, constant_assembly = get_instruction(func, "j"), int(constant, 2)

	return create_assembly(func_assembly, constant=constant_assembly, type='j')
    # parse 32 bits using Python slicing according to instruction format

    # use 'get_instruction' to get the name of the instruction
    # use 'get_register' to get the appropriate register names

    # return the properly formatted instruction

def get_register(register_number):

    # define a dictionary mapping 5-digit register values to their names:
    # ex: registers = {"00000": "$zero", "01000": "$t0", ...}

    # look-up the register
    # ex: register = registers[register_number] (will return $t0 if register_number = 01000

    # return the register name
	registers = {
		"00000": "$zero",
		"00010": "$v0",
		"00011": "$v1",
		"00100": "$a0",
		"00101": "$a1",
		"00110": "$a2",
		"00111": "$a3",
		"01000": "$t0",
		"01001": "$t1",
		"01010": "$t2",
		"01011": "$t3",
		"01100": "$t4",
		"01101": "$t5",
		"01110": "$t6",
		"01111": "$t7",
		"10000": "$s0",
		"10001": "$s1",
		"10010": "$s2",
		"10011": "$s3",
		"10100": "$s4",
		"10101": "$s5",
		"10110": "$s6",
		"10111": "$s7",
		"11000": "$t8",
		"11001": "$t9",
		"11111": "$ra"
	}

	return registers[register_number]


def get_instruction(opcode, instr_format, func=None):
	op_codes = {
		"000010": "j",
		"100011": "lw",
		"101011": "sw",
		"001101": "ori",

	}

	func_codes = {
		"100100": "and",
		"100010": "sub",
		"001000": "jr",
		"100000": "add"
	}

	return func_codes[func] if instr_format == "r" else op_codes[opcode]

    # define a dictionary mapping binary opcodes to the instructions listed in the assignment
    # define a dictionary mapping binary function codes to the instruction listed in the assignment

    # if instr_format is r-type, return the instruction based on a function code lookup
    # if instr_format - i/j-type, return the instruction based on an opcode lookup

# here are the 8 instructions to disassemble
instr1 = "00000001101011100101100000100100"
instr2 = "10001101010010010000000000001000"
instr3 = "00001000000000010010001101000101"
instr4 = "00000010101010010101100000100010"
instr5 = "00000011111000000000000000001000"
instr6 = "00110101111100001011111011101111"
instr7 = "10101110100011010000000000100000"
instr8 = "00000010110011010101000000100000"

# call the correct parser for each instruction format
print(parse_rtype(instr1))
print(parse_itype(instr2))
print(parse_jtype(instr3))
print(parse_rtype(instr4))
print(parse_rtype(instr5))
print(parse_itype(instr6))
print(parse_itype(instr7))
print(parse_rtype(instr8))

#TESTS
assert(parse_rtype(instr1) == "and $t3, $t5, $t6")
assert(parse_itype(instr2) == "lw $t1, 8($t2)")
assert(parse_jtype(instr3) == "j 74565")
assert(parse_rtype(instr4) == "sub $t3, $s5, $t1")
assert(parse_rtype(instr5) == "jr $ra")
assert(parse_itype(instr6) == "ori $s0, $t7, 48879")
assert(parse_itype(instr7) == "sw $t5, 32($s4)")
assert(parse_rtype(instr8) == "add $t2, $s6, $t5")
print("==========TESTS ALL PASS================")