// Solution

def logical = { a, b ->
    println """
a AND b   = ${a} && ${b}   = ${a & b}
a OR b    = ${a} || ${b}   = ${a | b}
NOT a     = ! ${a}         = ${! a}
a XOR b   = ${a} != ${b}   = ${a != b}
a EQV b   = ${a} == ${b}   = ${a == b}
"""
}

// Test/Demo

[true, false].each { a -> [true, false].each { b-> logical(a, b) } }