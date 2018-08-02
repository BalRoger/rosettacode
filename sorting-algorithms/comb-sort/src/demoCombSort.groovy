// Combsort solution:
def makeSwap = { a, i, j -> print "."; a[i] ^= a[j]; a[j] ^= a[i]; a[i] ^= a[j] }
 
def checkSwap = { a, i, j -> [(a[i] > a[j])].find { it }.each { makeSwap(a, i, j) } }
 
def combSort = { input ->
    def swap = checkSwap.curry(input)
    def size = input.size()
    def gap = size
    def swapped = true
    while (gap != 1 || swapped) {
        gap = (gap / 1.247330950103979) as int
        gap = (gap < 1) ? 1 : gap
        swapped = (0..<(size-gap)).any { swap(it, it + gap) }
    }
    input
}

// Combsort11 solution:
def combSort11 = { input ->
    def swap = checkSwap.curry(input)
    def size = input.size()
    def gap = size
    def swapped = true
    while (gap != 1 || swapped) {
        gap = (gap / 1.247330950103979) as int
        gap = ((gap < 1) ? 1 : ([10,9].contains(gap) ? 11 : gap))
        swapped = (0..<(size-gap)).any { swap(it, it + gap) }
    }
    input
}

// Test:
println   (combSort([23,76,99,58,97,57,35,89,51,38,95,92,24,46,31,24,14,12,57,78,4]))
println (combSort11([23,76,99,58,97,57,35,89,51,38,95,92,24,46,31,24,14,12,57,78,4]))
println ()
println   (combSort([88,18,31,44,4,0,8,81,14,78,20,76,84,33,73,75,82,5,62,70,12,7,1]))
println (combSort11([88,18,31,44,4,0,8,81,14,78,20,76,84,33,73,75,82,5,62,70,12,7,1]))
