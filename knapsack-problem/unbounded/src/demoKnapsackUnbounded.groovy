// Solution: dynamic programming

class Item {
    String name
    BigDecimal weight, volume, value
}

def items = [
    new Item(name: "panacea (vials of)", weight: 0.3, volume: 0.025, value: 3000),
    new Item(name: "ichor (ampules of)",   weight: 0.2, volume: 0.015, value: 1800),
    new Item(name: "gold (bars of)",    weight: 2.0, volume: 0.002, value: 2500)
]

def totalWeight = { list -> list.collect{ it.item.weight * it.count }.sum() }
def totalVolume = { list -> list.collect{ it.item.volume * it.count }.sum() }
def totalValue = { list -> list.collect{ it.item.value * it.count }.sum() }
 
def knapsackUnbounded = { possibleItems, BigDecimal weightMax, BigDecimal volumeMax ->
    def n = possibleItems.size()
    def wm = weightMax.unscaledValue()
    def vm = volumeMax.unscaledValue()
    def m = (0..n).collect{ i -> (0..wm).collect{ w -> (0..vm).collect{ v -> [] } } }
    (1..wm).each { w ->
        (1..vm).each { v ->
            (1..n).each { i ->
                def item = possibleItems[i-1]
                def wi = item.weight.unscaledValue()
                def vi = item.volume.unscaledValue()
                def bi = [w.intdiv(wi),v.intdiv(vi)].min()
                m[i][w][v] = (0..bi).collect{ count ->
                    m[i-1][w - wi * count][v - vi * count] + [[item:item, count:count]]
                }.max(totalValue).findAll{ it.count }
            }
        }
    }
    m[n][wm][vm]
}


// Test:

Set solutions = []
items.eachPermutation { itemList ->
    def start = System.currentTimeMillis()
    def packingList = knapsackUnbounded(itemList, 25.0, 0.250)
    def elapsed = System.currentTimeMillis() - start
 
    println "\n  Item Order: ${itemList.collect{ it.name.split()[0] }}"
    println "Elapsed Time: ${elapsed/1000.0} s"
 
    solutions << (packingList as Set)
}
 
solutions.each { packingList ->
    println "\nTotal Weight: ${totalWeight(packingList)}"
    println "Total Volume: ${totalVolume(packingList)}"
    println " Total Value: ${totalValue(packingList)}"
    packingList.each {
        printf ('  item: %-22s  count:%2d  weight:%4.1f  Volume:%5.3f\n',
                it.item.name, it.count, it.item.weight * it.count, it.item.volume * it.count)
    }
}