import java.math.RoundingMode;

// Recursive

def rFact
rFact = { (it > 1) ? it * rFact(it - 1) : 1 as BigInteger }

// Iterative

def iFact = { (it > 1) ? (2..it).inject(1 as BigInteger) { i, j -> i*j } : 1 }

// Analytic (Sterling's approximation)

final π = Math.PI
final e = Math.E
def aFact = { BigDecimal it -> (it > 1) ? ((2*π*it)**(1/2)*(it/e)**it as BigDecimal).setScale(0,RoundingMode.HALF_UP) as BigInteger : 1 }

//////////////////////////////

// Test

def time = { Closure c ->
    def start = System.currentTimeMillis()
    def result = c()
    def elapsedMS = (System.currentTimeMillis() - start)/1000
    printf '(%6.4fs elapsed)', elapsedMS
    result
}

def dashes = '---------------------'
print "   n!       elapsed time   "; (0..15).each { def length = Math.max(it - 3, 3); printf " %${length}d", it }; println()
print "--------- -----------------"; (0..15).each { def length = Math.max(it - 3, 3); print " ${dashes[0..<length]}" }; println()
[recursive:rFact, iterative:iFact, analytic:aFact].each { name, fact ->
    printf "%9s ", name
    def factList = time { (0..15).collect {fact(it)} }
    factList.each { printf ' %3d', it }
    println()
}
