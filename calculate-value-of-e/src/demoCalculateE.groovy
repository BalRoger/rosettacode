// Solution:

/**
 * If the difference between previous and next iteration is less than the tolerance (ε)
 * we judge that the sequence of partial sums has converged "enough".
 *
 * Since the difference betwen partial sums is always the "last" term, it suffices to
 * ensure that the "last" term is less than the tolerance.
 */
def ε = 1.0e-15
def φ = 1/ε

def generateAddends = {
    def addends = []
    def n = 0.0
    def fact = 1.0
    while (true) {
        fact *= (n < 2 ? 1.0 : n) as double
        addends << 1.0/fact
        if (fact > φ) break // any further addends would not pass the tolerance test
        n++
    }
    addends.sort(false) // smallest addends first for better response to rounding error
}

def e = generateAddends().sum()

// Test:
printf "%17.15f\n%17.15f\n", e, Math.E

// Output:
//
// 2.718281828459045
// 2.718281828459045

