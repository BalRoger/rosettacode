// Builds on the Combinations solution. Sets are not a "natural" collection type in Groovy. Lists are much more richly supported. Thus, this solution is liberally sprinkled with coercion from Set to List and from List to Set.
def comb
comb = { m, List list ->
    def n = list.size()
    m == 0 ?
        [[]] :
        (0..(n-m)).inject([]) { newlist, k ->
            def sublist = (k+1 == n) ? [] : list[(k+1)..<n]
            newlist += comb(m-1, sublist).collect { [list[k]] + it }
        }
}
 
def powerSet = { set ->
    (0..(set.size())).inject([]){ list, i ->  list + comb(i,set as List)}.collect { it as LinkedHashSet } as LinkedHashSet
}

// Test program:
def vocalists = [ "C", "S", "N", "Y" ] as LinkedHashSet
println "${vocalists}"
println powerSet(vocalists)