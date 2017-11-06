// Solution:
def insertionSort = { list ->
 
    def size = list.size()
    (1..<size).each { i ->
        def value = list[i]
        def j = i - 1
        for (; j >= 0 && list[j] > value; j--) {
            print "."; list[j+1] = list[j]
        }
        print "."; list[j+1] = value
    }
    list
}

// Test:
println (insertionSort([23,76,99,58,97,57,35,89,51,38,95,92,24,46,31,24,14,12,57,78,4]))
println (insertionSort([88,18,31,44,4,0,8,81,14,78,20,76,84,33,73,75,82,5,62,70,12,7,1]))