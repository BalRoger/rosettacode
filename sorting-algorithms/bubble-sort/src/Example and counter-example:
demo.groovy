// Solution:
def makeSwap = { a, i, j = i+1 -> print "."; a[[i,j]] = a[[j,i]] }
 
def checkSwap = { a, i, j = i+1 -> [(a[i] > a[j])].find { it }.each { makeSwap(a, i, j) } }
 
def bubbleSort = { list ->
    boolean swapped = true
    while (swapped) { swapped = (1..<list.size()).any { checkSwap(list, it-1) } }
    list
}

// Test Program:
println bubbleSort([23,76,99,58,97,57,35,89,51,38,95,92,24,46,31,24,14,12,57,78,4])
println bubbleSort([88,18,31,44,4,0,8,81,14,78,20,76,84,33,73,75,82,5,62,70,12,7,1])