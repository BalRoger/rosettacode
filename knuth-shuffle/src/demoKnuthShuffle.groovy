// Solution:

def shuffle = { list ->
    if (list == null || list.empty) return list
    def r = new Random()
    def n = list.size()
    (n..1).each { i ->
        def j = r.nextInt(i)
        list[[i-1, j]] = list[[j, i-1]]
    }
    list
}


// Test:

def list = [] + (0..20)
println list
println shuffle(list)
println shuffle(list)
println shuffle(list)