// Solution:

def life1D = { self ->
    def right = self[1..-1] + [false]
    def left = [false] + self[0..-2]
    [left, self, right].transpose().collect { hood -> hood.count { it } == 2 }
}


// Test:

def cells = ('_###_##_#_#_#_#__#__' as List).collect { it == '#' }
println "Generation 0: ${cells.collect { g -> g ? '#' : '_' }.join()}"
(1..9).each {
    cells = life1D(cells)
    println "Generation ${it}: ${cells.collect { g -> g ? '#' : '_' }.join()}"
}