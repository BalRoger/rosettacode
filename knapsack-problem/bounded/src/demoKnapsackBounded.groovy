// Solution: dynamic programming

def totalWeight = { list -> list.collect{ it.item.weight * it.count }.sum() }
def totalValue = { list -> list.collect{ it.item.value * it.count }.sum() }
 
def knapsackBounded = { possibleItems ->
    def n = possibleItems.size()
    def m = (0..n).collect{ i -> (0..400).collect{ w -> []} }
    (1..400).each { w ->
        (1..n).each { i ->
            def item = possibleItems[i-1]
            def wi = item.weight, pi = item.pieces
            def bi = [w.intdiv(wi),pi].min()
            m[i][w] = (0..bi).collect{ count ->
                m[i-1][w - wi * count] + [[item:item, count:count]]
            }.max(totalValue).findAll{ it.count }
        }
    }
    m[n][400]
}


// Test:

def items = [ 
        [name:"map",                    weight:  9, value:150, pieces:1],
        [name:"compass",                weight: 13, value: 35, pieces:1],
        [name:"water",                  weight:153, value:200, pieces:2],
        [name:"sandwich",               weight: 50, value: 60, pieces:2],
        [name:"glucose",                weight: 15, value: 60, pieces:2],
        [name:"tin",                    weight: 68, value: 45, pieces:3],
        [name:"banana",                 weight: 27, value: 60, pieces:3],
        [name:"apple",                  weight: 39, value: 40, pieces:3],
        [name:"cheese",                 weight: 23, value: 30, pieces:1],
        [name:"beer",                   weight: 52, value: 10, pieces:3],
        [name:"suntan cream",           weight: 11, value: 70, pieces:1],
        [name:"camera",                 weight: 32, value: 30, pieces:1],
        [name:"t-shirt",                weight: 24, value: 15, pieces:2],
        [name:"trousers",               weight: 48, value: 10, pieces:2],
        [name:"umbrella",               weight: 73, value: 40, pieces:1],
        [name:"waterproof trousers",    weight: 42, value: 70, pieces:1],
        [name:"waterproof overclothes", weight: 43, value: 75, pieces:1],
        [name:"note-case",              weight: 22, value: 80, pieces:1],
        [name:"sunglasses",             weight:  7, value: 20, pieces:1],
        [name:"towel",                  weight: 18, value: 12, pieces:2],
        [name:"socks",                  weight:  4, value: 50, pieces:1],
        [name:"book",                   weight: 30, value: 10, pieces:2],
]
 
def start = System.currentTimeMillis()
def packingList = knapsackBounded(items)
def elapsed = System.currentTimeMillis() - start
 
println "Elapsed Time: ${elapsed/1000.0} s"
println "Total Weight: ${totalWeight(packingList)}"
println " Total Value: ${totalValue(packingList)}"
packingList.each {
    printf ('  item: %-22s  weight:%4d  value:%4d  count:%2d\n',
            it.item.name, it.item.weight, it.item.value, it.count)
}