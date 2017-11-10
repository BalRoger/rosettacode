// Solution:
def makeSwap = { a, i, j = i+1 -> print "."; a[[j,i]] = a[[i,j]] }
 
def checkSwap = { list, i, j = i+1 -> [(list[i] > list[j])].find{ it }.each { makeSwap(list, i, j) } }
 
def gnomeSort = { input ->
    def swap = checkSwap.curry(input)
    def index = 1
    while (index < input.size()) {
        index += (swap(index-1) && index > 1) ? -1 : 1
    }
    input
}

// Test:
println (gnomeSort([23,76,99,58,97,57,35,89,51,38,95,92,24,46,31,24,14,12,57,78,4]))
println (gnomeSort([88,18,31,44,4,0,8,81,14,78,20,76,84,33,73,75,82,5,62,70,12,7,1]))
