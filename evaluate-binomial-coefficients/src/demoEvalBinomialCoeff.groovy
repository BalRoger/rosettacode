// Solution:
def factorial = { x ->
    assert x > -1
    x == 0 ? 1 : (1..x).inject(1G) { BigInteger product, BigInteger factor -> product *= factor }
}
 
def combinations = { n, k ->
    assert k >= 0
    assert n >= k
    factorial(n).intdiv(factorial(k)*factorial(n-k))
}

// Test:
assert combinations(20, 0) == combinations(20, 20)
assert combinations(20, 10) == (combinations(19, 9) + combinations(19, 10))
assert combinations(5, 3) == 10
println combinations(5, 3)