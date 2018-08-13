// Generate Arbitrary String of Bracket Pairs:

def random = new Random()
 
def factorial = { (it > 1) ? (2..it).inject(1) { i, j -> i*j } : 1 }
 
def makePermutation;
makePermutation = { string, i ->
    def n = string.size()
    if (n < 2) return string
    def fact = factorial(n-1)
    assert i < fact*n
 
    def index = i.intdiv(fact)
    string[index] + makePermutation(string[0..<index] + string[(index+1)..<n], i % fact)
}
 
def randomBrackets = { n ->
    if (n == 0) return ''
    def base = '['*n + ']'*n
    def p = random.nextInt(factorial(n*2))
    makePermutation(base, p)
}


// Check Balance of Bracket String:

boolean balancedBrackets(String brackets, int depth=0) {
    if (brackets == null || brackets.empty) return depth == 0
    switch (brackets[0]) {
        case '[': 
            return brackets.size() > 1  &&  balancedBrackets(brackets[1..-1], depth + 1)
        case ']':
            return depth > 0  &&  (brackets.size() == 1  ||  balancedBrackets(brackets[1..-1], depth - 1))
        default:
            return brackets.size() == 1  ?  depth == 0  :  balancedBrackets(brackets[1..-1], depth)
    }
}
Test:

Set brackets = []
(0..100).each {
    (0..8).each { r ->
        brackets << randomBrackets(r)
    }
}
 
brackets.sort { a, b ->
    a.size() <=> b.size() ?: a <=> b
} .each {
    def bal = balancedBrackets(it) ? "balanced:   " : "unbalanced: "
    println "${bal} ${it}"
}