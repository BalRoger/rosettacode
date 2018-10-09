// Solution:

def makeIdentityMatrix = { n ->
    (0..<n).collect { i -> (0..<n).collect { j -> (i == j) ? 1 : 0 } }
}


// Test:

(2..6).each { order ->
    def iMatrix = makeIdentityMatrix(order)
    iMatrix.each { println it }
    println()
}
