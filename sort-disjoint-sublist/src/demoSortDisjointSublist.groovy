// Groovy allows List-valued indexing to "gather" and "scatter" arbitrary sublists,
// making the solution almost trivial.

def sparseSort = { a, indices = ([] + (0..<(a.size()))) ->
    indices.sort().unique()
    a[indices] = a[indices].sort()
    a
}


// Test:

tests = [
    "no indices to sort":[],
    "suggested sample indices":[6,1,7],
    "another set of sample indices":[6,4,0,2]
].each { desc, indices ->
    println "\n$desc : $indices"
    def a = [7, 6, 5, 4, 3, 2, 1, 0]
    println "before: $a"
    println "after: ${sparseSort(a, indices)}"
}

println "\ndefault == sort all"
def a = [7, 6, 5, 4, 3, 2, 1, 0]
println "before: $a"
println "after: ${sparseSort(a)}"