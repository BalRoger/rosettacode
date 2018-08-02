// Solution:
def nonSquare = { long n -> n + ((1/2 + n**0.5) as long) }

// Test Program:
(1..22).each { println nonSquare(it) }
(1..1000000).each { assert ((nonSquare(it)**0.5 as long)**2) != nonSquare(it) }
