// This formulation of the pancake sort achieves stability by picking the last index (rather than,
// say, the first) in the remaining sublist that matches the max value of the remaining sublist.
// Performance is enhanced somewhat by not flipping if the flipPoint is already at the end of the
// remaining sublist.
def makeSwap = { a, i, j = i+1 -> print "."; a[[j,i]] = a[[i,j]] }
 
def flip = { list, n -> (0..<((n+1)/2)).each { makeSwap(list, it, n-it) } }
 
def pancakeSort = { list ->
    def n = list.size()
    (1..<n).reverse().each { i ->
        def max = list[0..i].max()
        def flipPoint = (i..0).find{ list[it] == max }
        if (flipPoint != i) {
            flip(list, flipPoint)
            flip(list, i)
        }
    }
    list
}

// Test:
println (pancakeSort([23,76,99,58,97,57,35,89,51,38,95,92,24,46,31,24,14,12,57,78,4]))
println (pancakeSort([88,18,31,44,4,0,8,81,14,78,20,76,84,33,73,75,82,5,62,70,12,7,1]))
println ()
println (pancakeSort([10, 10.0, 10.00, 1]))
println (pancakeSort([10, 10.00, 10.0, 1]))
println (pancakeSort([10.0, 10, 10.00, 1]))
println (pancakeSort([10.0, 10.00, 10, 1]))
println (pancakeSort([10.00, 10, 10.0, 1]))
println (pancakeSort([10.00, 10.0, 10, 1]))

// The use of decimals and integers that compare as equal demonstrates, but of course not prove,
// that the sort is stable.
