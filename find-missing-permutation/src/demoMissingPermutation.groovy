// Solution:

def fact = { n -> [1,(1..<(n+1)).inject(1) { prod, i -> prod * i }].max() }
def missingPerms = {List elts, List perms ->
    perms.empty ? elts.permutations() : elts.collect { e ->
        def ePerms = perms.findAll { e == it[0] }.collect { it[1..-1] }
        ePerms.size() == fact(elts.size() - 1) ? [] \
            : call(elts - e, ePerms).collect { [e] + it }
    }.sum()
}


// Test:

def e = 'ABCD' as List
def p = ['ABCD', 'CABD', 'ACDB', 'DACB', 'BCDA', 'ACBD', 'ADCB', 'CDAB', 'DABC', 'BCAD', 'CADB', 'CDBA',
        'CBAD', 'ABDC', 'ADBC', 'BDCA', 'DCBA', 'BACD', 'BADC', 'BDAC', 'CBDA', 'DBCA', 'DCAB'].collect { it as List }
 
def mp = missingPerms(e, p)
mp.each { println it }