// Solution, both "collection type" and "element type" agnostic:

def mode(Iterable col) {
    assert col
    def m = [:]
    col.each {
        m[it] = m[it] == null ? 1 : m[it] + 1 
    }
    def keys = m.keySet().sort { -m[it] }
    keys.findAll { m[it] == m[keys[0]] }
}


// Test:

def random = new Random()
def sourceList = [ 'Lamp', 42.0, java.awt.Color.RED, new Date(), ~/pattern/]
(0..10).each {
    def a = (0..10).collect { sourceList[random.nextInt(5)] }
    println "${mode(a)}    ==    mode(${a})"
}