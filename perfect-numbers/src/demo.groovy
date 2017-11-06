// Solution:
def isPerfect = { n ->
    n > 4 && (n == (2..Math.sqrt(n)).findAll { n % it == 0 }.inject(1) { factorSum, i -> factorSum += i + n/i })
}

// Test program:
(0..10000).findAll { isPerfect(it) }.each { println it }