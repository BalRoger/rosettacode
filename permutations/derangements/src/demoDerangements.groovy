// Solution:

def fact = { n -> [1,(1..<(n+1)).inject(1) { prod, i -> prod * i }].max() }
def subfact
subfact = { BigInteger n -> (n == 0) ? 1 : (n == 1) ? 0 : ((n-1) * (subfact(n-1) + subfact(n-2))) }
 
def derangement = { List l ->
    def d = []
  if (l) l.eachPermutation { p -> if ([p,l].transpose().every{ pp, ll -> pp != ll }) d << p }
    d
}


// Test:

def d = derangement([1,2,3,4])
assert d.size() == subfact(4)
d.each { println it }
 
println """
n   # derangements   subfactorial
=   ==============   ============"""
(0..9). each { n ->
    def dr = derangement((1..<(n+1)) as List)
    def sf = subfact(n)
    printf('%1d   %14d   %12d\n', n, dr.size(), sf)
}
 
println """
!20 == ${subfact(20)}
"""