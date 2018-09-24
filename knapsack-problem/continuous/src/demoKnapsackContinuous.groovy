// Solution: obvious greedy algorithm

import static java.math.RoundingMode.*
 
def knapsackCont = { list, maxWeight = 15.0 ->
    list.sort{ it.weight / it.value }
    def remainder = maxWeight
    List sack = []
    for (item in list) {
        if (item.weight < remainder) {
            sack << [name: item.name, weight: item.weight,
                        value: (item.value as BigDecimal).setScale(2, HALF_UP)]
        } else {
            sack << [name: item.name, weight: remainder,
                        value: (item.value * remainder / item.weight).setScale(2, HALF_UP)]
            break
        }
        remainder -= item.weight
    }
    sack
}


// Test:

def possibleItems = [
    [name:'beef',    weight:3.8, value:36],
    [name:'pork',    weight:5.4, value:43],
    [name:'ham',     weight:3.6, value:90],
    [name:'greaves', weight:2.4, value:45],
    [name:'flitch',  weight:4.0, value:30],
    [name:'brawn',   weight:2.5, value:56],
    [name:'welt',    weight:3.7, value:67],
    [name:'salami',  weight:3.0, value:95],
    [name:'sausage', weight:5.9, value:98],
]
 
def contents = knapsackCont(possibleItems)
println "Total Value: ${contents*.value.sum()}"
contents.each {
    printf("    name: %-7s  weight: ${it.weight}  value: ${it.value}\n", it.name)
}