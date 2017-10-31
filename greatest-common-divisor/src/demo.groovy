// Recursive

def gcdR
gcdR = { m, n -> m = m.abs(); n = n.abs(); n == 0 ? m : m%n == 0 ? n : gcdR(n, m%n) }


// Iterative

def gcdI = { m, n -> m = m.abs(); n = n.abs(); n == 0 ? m : { while(m%n != 0) { t=n; n=m%n; m=t }; n }() }


// Test program:

println "                R     I"
println "gcd(28, 0)   = ${gcdR(28, 0)} == ${gcdI(28, 0)}"
println "gcd(0, 28)   = ${gcdR(0, 28)} == ${gcdI(0, 28)}"
println "gcd(0, -28)  = ${gcdR(0, -28)} == ${gcdI(0, -28)}"
println "gcd(70, -28) = ${gcdR(70, -28)} == ${gcdI(70, -28)}"
println "gcd(70, 28)  = ${gcdR(70, 28)} == ${gcdI(70, 28)}"
println "gcd(28, 70)  = ${gcdR(28, 70)} == ${gcdI(28, 70)}"
println "gcd(800, 70) = ${gcdR(800, 70)} == ${gcdI(800, 70)}"
println "gcd(27, -70) =  ${gcdR(27, -70)} ==  ${gcdI(27, -70)}"