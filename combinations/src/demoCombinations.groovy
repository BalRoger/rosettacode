// Solution:
def comb = { m, list ->
    def n = list.size()
    m == 0
        ? [[]]
        : (0..(n-m)).inject([]) { newlist, k ->
            newlist += call(m-1, list[(k+1)..<n]).collect { [list[k]] + it }
        }
}

// Test program:
def csny = [ "Crosby", "Stills", "Nash", "Young" ]
println "Choose from ${csny}"
(0..(csny.size())).each { i -> println "Choose ${i}:"; comb(i, csny).each { println it }; println() }

//////////////////////////////////

// Zero-based Integers:
def comb0 = { m, n -> comb(m, (0..<n)) }

// One-based Integers:
def comb1 = { m, n -> comb(m, (1..n)) }

// Test program:
['zero-based':comb0, 'one-based':comb1].each { title, combN ->
    println "Choose out of 5 (${title}):"
    (0..3).each { i -> println "Choose ${i}:"; combN(i, 5).each { println it }; println() }
}