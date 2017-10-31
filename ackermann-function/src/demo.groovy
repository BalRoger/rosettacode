// Solution:

def ack ( m, n ) {
    assert m >= 0 && n >= 0 : 'both arguments must be non-negative'
    m == 0 ? n + 1 : n == 0 ? ack(m-1, 1) : ack(m-1, ack(m, n-1))
}

////////////////////////////////

// Test program:

def ackMatrix = (0..3).collect { m -> (0..8).collect { n -> ack(m, n) } }
ackMatrix.each { it.each { elt -> printf "%7d", elt }; println() }