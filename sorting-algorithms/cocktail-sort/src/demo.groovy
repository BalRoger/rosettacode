// Solution:
def makeSwap = { a, i, j = i+1 -> print "."; a[[j,i]] = a[[i,j]] }
 
def checkSwap = { a, i, j = i+1 -> [(a[i] > a[j])].find{ it }.each { makeSwap(a, i, j) } }
 
def cocktailSort = { list ->
    if (list == null || list.size() < 2) return list
    def n = list.size()
    def swap = checkSwap.curry(list)
    while (true) {
        def swapped = (0..(n-2)).any(swap) && ((-2)..(-n)).any(swap)
        if ( ! swapped ) break
    }
    list
}

// Test:
println cocktailSort([23,76,99,58,97,57,35,89,51,38,95,92,24,46,31,24,14,12,57,78,4])
println cocktailSort([88,18,31,44,4,0,8,81,14,78,20,76,84,33,73,75,82,5,62,70,12,7,1])
 
println cocktailSort([ 3, 14, 1, 5, 9, 2, 6, 3 ])
println cocktailSort([ 3, 14 ])
println cocktailSort([ 33, 14 ])
