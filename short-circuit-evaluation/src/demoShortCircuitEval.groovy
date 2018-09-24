// Like all C-based languages (of which I am aware), Groovy short-circuits the logical and (&&)
// and logical or (||) operations, but not the bitwise and (&) and bitwise or (|) operations.

def f = { println '  AHA!'; it instanceof String }
def g = { printf ('%5d ', it); it > 50 }
 
println 'bitwise'
assert g(100) & f('sss')
assert g(2) | f('sss')
assert ! (g(1) & f('sss'))
assert g(200) | f('sss')
 
println '''
logical'''
assert g(100) && f('sss')
assert g(2) || f('sss')
assert ! (g(1) && f('sss'))
println()
assert g(200) || f('sss')
println()