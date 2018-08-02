// "assertConformable()" asserts that a & b are both rectangular lists of lists, and that row-length
// (number of columns) of a is equal to the column-length (number of rows) of b.
def assertConformable = { a, b ->
    assert a instanceof List
    assert b instanceof List
    assert a.every { it instanceof List && it.size() == b.size() }
    assert b.every { it instanceof List && it.size() == b[0].size() }
}
 
// Without Indexed Loops
// Uses transposition to avoid indirect element access via ranges of indexes.
def matmulWOIL = { a, b ->
    assertConformable(a, b)
 
    def bt = b.transpose()
    a.collect { ai ->
        bt.collect { btj ->
            [ai, btj].transpose().collect { it[0] * it[1] }.sum()
        }
    }
}

// Without Transposition
// Uses ranges of indexes, the way that matrix multiplication is typically defined. Not as elegant,
// but it avoids expensive transpositions.

def matmulWOT = { a, b ->
    assertConformable(a, b)
 
    (0..<a.size()).collect { i ->
        (0..<b[0].size()).collect { j ->
            (0..<b.size()).collect { k -> a[i][k] * b[k][j] }.sum()
        }
    }
}
Test:

def m4by2 = [ [  1,  2 ],
              [  3,  4 ],
              [  5,  6 ],
              [  7,  8 ] ]
 
def m2by3 = [ [  1,  2,  3 ],
              [  4,  5,  6 ] ]
 
matmulWOIL(m4by2, m2by3).each { println it }
println()
matmulWOT(m4by2, m2by3).each { println it }