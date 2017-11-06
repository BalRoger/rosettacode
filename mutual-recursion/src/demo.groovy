// Solution:
def f, m  // recursive closures must be declared before they are defined
f = { n -> n == 0 ? 1 : n - m(f(n-1)) }
m = { n -> n == 0 ? 0 : n - f(m(n-1)) }

// Test program:
println 'f(0..20): ' + (0..20).collect { f(it) }
println 'm(0..20): ' + (0..20).collect { m(it) }