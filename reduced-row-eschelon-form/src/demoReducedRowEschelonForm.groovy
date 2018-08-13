// This solution implements the transformation to reduced row echelon form with optional pivoting.
// Options are provided for both partial pivoting and scaled partial pivoting. The default option
// is no pivoting at all.

enum Pivoting {
    NONE({ i, it -> 1 }),
    PARTIAL({ i, it -> - (it[i].abs()) }),
    SCALED({ i, it -> - it[i].abs()/(it.inject(0) { sum, elt -> sum + elt.abs() } ) });
 
    public final Closure comparer
 
    private Pivoting(Closure c) {
        comparer = c
    }
}
 
def isReducibleMatrix = { matrix ->
    def m = matrix.size()
    m > 1 && matrix[0].size() > m && matrix[1..<m].every { row -> row.size() == matrix[0].size() }
}
 
def reducedRowEchelonForm = { matrix, Pivoting pivoting = Pivoting.NONE ->
    assert isReducibleMatrix(matrix)
    def m = matrix.size()
    def n = matrix[0].size()
    (0..<m).each { i ->
        matrix[i..<m].sort(pivoting.comparer.curry(i))
        matrix[i][i..<n] = matrix[i][i..<n].collect { it/matrix[i][i] }
        ((0..<i) + ((i+1)..<m)).each { k ->
            (i..<n).reverse().each { j ->
                matrix[k][j] -= matrix[i][j]*matrix[k][i]
            }
        } 
    }
    matrix
}


// This test first demonstrates the test case provided, and then demonstrates another test case
// designed to show the dangers of not using pivoting on an otherwise solvable matrix. Both test
// cases exercise all three pivoting options.

def matrixCopy = { matrix -> matrix.collect { row -> row.collect { it } } }
 
println "Tests for matrix A:"
def a = [
    [1, 2, -1, -4],
    [2, 3, -1, -11],
    [-2, 0, -3, 22]
]
a.each { println it }
println()
 
println "pivoting == Pivoting.NONE"
reducedRowEchelonForm(matrixCopy(a)).each { println it }
println()
println "pivoting == Pivoting.PARTIAL"
reducedRowEchelonForm(matrixCopy(a), Pivoting.PARTIAL).each { println it }
println()
println "pivoting == Pivoting.SCALED"
reducedRowEchelonForm(matrixCopy(a), Pivoting.SCALED).each { println it }
println()
 
 
println "Tests for matrix B (divides by 0 without pivoting):"
def b = [
    [1, 2, -1, -4],
    [2, 4, -1, -11],
    [-2, 0, -6, 24]
]
b.each { println it }
println()
 
println "pivoting == Pivoting.NONE"
try {
    reducedRowEchelonForm(matrixCopy(b)).each { println it }
    println()
} catch (e) {
    println "KABOOM! ${e.message}"
    println()
}
 
println "pivoting == Pivoting.PARTIAL"
try {
    reducedRowEchelonForm(matrixCopy(b), Pivoting.PARTIAL).each { println it }
    println()
} catch (e) {
    println "KABOOM! ${e.message}"
    println()
}
println "pivoting == Pivoting.SCALED"
try {
    reducedRowEchelonForm(matrixCopy(b), Pivoting.SCALED).each { println it }
    println()
} catch (e) {
    println "KABOOM! ${e.message}"
    println()
}


//Output:
//Tests for matrix A:
//[1, 2, -1, -4]
//[2, 3, -1, -11]
//[-2, 0, -3, 22]
//
//pivoting == Pivoting.NONE
//[1, 0, 0, -8]
//[0, 1, 0, 1]
//[0, 0, 1, -2]
//
//pivoting == Pivoting.PARTIAL
//[1, 0.0, 0E-11, -7.9999999997000000000150]
//[0, 1, 0E-10, 0.999999999700000000010]
//[0, 0.0, 1, -2.00000000030]
//
//pivoting == Pivoting.SCALED
//[1, 0, 0, -8]
//[0, 1, 0, 1]
//[0, 0, 1, -2]
//
//Tests for matrix B (divides by 0 without pivoting):
//[1, 2, -1, -4]
//[2, 4, -1, -11]
//[-2, 0, -6, 24]
//
//pivoting == Pivoting.NONE
//KABOOM! Division undefined
//
//pivoting == Pivoting.PARTIAL
//[1, 0, 0.00, -3.00]
//[0, 1, 0.00, -2.00]
//[0, 0, 1, -3]
//
//pivoting == Pivoting.SCALED
//[1, 0, 0, -3]
//[0, 1, 0, -2]
//[0, 0, 1, -3]
