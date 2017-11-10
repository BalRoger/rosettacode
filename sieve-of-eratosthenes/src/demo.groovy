// This solution uses a BitSet for compactness and speed, but in Groovy, BitSet has full List
// semantics. It also uses both the "square root of the boundary" shortcut and the "square of the
// prime" shortcut.
def sievePrimes = { bound ->
    def isPrime  = new BitSet(bound)
    isPrime[0..1] = false
    isPrime[2..bound] = true
    (2..(Math.sqrt(bound))).each { pc ->
        if (isPrime[pc]) {
            ((pc**2)..bound).step(pc) { isPrime[it] = false }
        }
    }
    (0..bound).findAll { isPrime[it] }
}

// Test:
println sievePrimes(100)
