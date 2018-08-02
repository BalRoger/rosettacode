def pairwiseOperation = { x, y, Closure binaryOp ->
    assert x && y && x.size() == y.size()
    [x, y].transpose().collect(binaryOp)
}
 
def pwMult =  pairwiseOperation.rcurry { it[0] * it[1] }
 
// dot product
def dotProduct = { x, y ->
    assert x && y && x.size() == y.size()
    pwMult(x, y).sum()
}

// cross product using scalar operations
def crossProductS = { x, y ->
    assert x && y && x.size() == 3 && y.size() == 3
    [x[1]*y[2] - x[2]*y[1], x[2]*y[0] - x[0]*y[2] , x[0]*y[1] - x[1]*y[0]]
}

// corss product using vector operations
def rotR = {
    assert it && it.size() > 2
    [it[-1]] + it[0..-2]
}
 
def rotL = {
    assert it && it.size() > 2
    it[1..-1] + [it[0]]
}
 
def pwSubtr = pairwiseOperation.rcurry { it[0] - it[1] }
 
def crossProductV = { x, y ->
    assert x && y && x.size() == 3 && y.size() == 3
    pwSubtr(pwMult(rotL(x), rotR(y)), pwMult(rotL(y), rotR(x)))
}

// test program, including triple products
def test = { crossProduct ->

    def scalarTripleProduct = { x, y, z ->
        dotProduct(x, crossProduct(y, z))
    }

    def vectorTripleProduct = { x, y, z ->
        crossProduct(x, crossProduct(y, z))
    }

    def a = [3, 4, 5]
    def b = [4, 3, 5]
    def c = [-5, -12, -13]

    println("      a • b = " + dotProduct(a,b))
    println("      a x b = " + crossProduct(a,b))
    println("a • (b x c) = " + scalarTripleProduct(a,b,c))
    println("a x (b x c) = " + vectorTripleProduct(a,b,c))
    println()
}
    
test(crossProductS)
test(crossProductV)