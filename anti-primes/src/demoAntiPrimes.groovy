// Solution (uses [[Factors_of_an_integer#Groovy|Factors of an integer]] function "factorize()")

def factorize(long target) {
    if (target == 1) return [1L]

    if (target < 4) return [1L, target]

    def targetSqrt = Math.sqrt(target)
    def lowfactors = (2L..targetSqrt).grep { (target % it) == 0 }
    if (lowfactors == []) return [1L, target]
    def nhalf = lowfactors.size() - ((lowfactors[-1] == targetSqrt) ? 1 : 0)

    [1] + lowfactors + (0..<nhalf).collect { target.intdiv(lowfactors[it]) }.reverse() + [target]
}

def getAntiPrimes(def limit = 10) {
    def antiPrimes = []
    def candidate = 1L
    def maxFactors = 0

    while (antiPrimes.size() < limit) {
        def factors = factorize(candidate)
        if (factors.size() > maxFactors) {
            maxFactors = factors.size()
            antiPrimes << candidate
        }
        candidate++
    }
    antiPrimes
}


// Test:

println (getAntiPrimes(20))


//Output:
//[1, 2, 4, 6, 12, 24, 36, 48, 60, 120, 180, 240, 360, 720, 840, 1260, 1680, 2520, 5040, 7560]
