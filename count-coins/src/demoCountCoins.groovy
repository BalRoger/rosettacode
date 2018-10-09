// Translation of: Go
// Intuitive Recursive Solution:

def ccR
ccR = { BigInteger tot, List<BigInteger> coins ->
    BigInteger n = coins.size()
    switch ([tot:tot, coins:coins]) {
        case { it.tot == 0 } :
            return 1g
        case { it.tot < 0 || coins == [] } :
            return 0g
        default:
            return ccR(tot, coins[1..<n]) +
                ccR(tot - coins[0], coins)
    }
}

// Fast Iterative Solution:

def ccI = { BigInteger tot, List<BigInteger> coins ->
    List<BigInteger> ways = [0g] * (tot+1)
    ways[0] = 1g
    coins.each { BigInteger coin ->
        (coin..tot).each { j ->
            ways[j] += ways[j-coin]
        }
    }
    ways[tot]
}


// Test:

println '\nBase:'
[iterative: ccI, recursive: ccR].each { label, cc ->
    print "${label} "
    def start = System.currentTimeMillis()
    def ways = cc(100g, [25g, 10g, 5g, 1g])
    def elapsed = System.currentTimeMillis() - start
    println ("answer: ${ways}   elapsed: ${elapsed}ms")
}

print '\nExtra Credit:\niterative '
def start = System.currentTimeMillis()
def ways = ccI(1000g * 100, [100g, 50g, 25g, 10g, 5g, 1g])
def elapsed = System.currentTimeMillis() - start
println ("answer: ${ways}   elapsed: ${elapsed}ms")
