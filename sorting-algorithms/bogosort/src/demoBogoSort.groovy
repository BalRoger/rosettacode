// Solution (also implicitly tracks the number of shuffles required):
def bogosort = { list ->
    def n = list.size()
    while (n > 1 && (1..<n).any{ list[it-1] > list[it] }) {
        print '.'*n
        Collections.shuffle(list)
    }
    list
}

// Test Program:
println (bogosort([3,1,2]))