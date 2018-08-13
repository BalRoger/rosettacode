// Solution #1: brute force

def totalWeight = { list -> list*.weight.sum() }
def totalValue = { list -> list*.value.sum() }
 
def knapsack01bf = { possibleItems ->
    possibleItems.subsequences().findAll{ ss ->
        def w = totalWeight(ss)
        350 < w && w < 401
    }.max(totalValue)
}


// Solution #2: dynamic programming

def knapsack01dp = { possibleItems ->
    def n = possibleItems.size()
    def m = (0..n).collect{ i -> (0..400).collect{ w -> []} }
    (1..400).each { w ->
        (1..n).each { i ->
            def wi = possibleItems[i-1].weight
            m[i][w] = wi > w ? m[i-1][w] : ([m[i-1][w], m[i-1][w-wi] + [possibleItems[i-1]]].max(totalValue))
        }
    }
    m[n][400]
}


// Test:

def items = [ 
        [name:"map", weight:9, value:150],
        [name:"compass", weight:13, value:35],
        [name:"water", weight:153, value:200],
        [name:"sandwich", weight:50, value:160],
        [name:"glucose", weight:15, value:60],
        [name:"tin", weight:68, value:45],
        [name:"banana", weight:27, value:60],
        [name:"apple", weight:39, value:40],
        [name:"cheese", weight:23, value:30],
        [name:"beer", weight:52, value:10],
        [name:"suntan cream", weight:11, value:70],
        [name:"camera", weight:32, value:30],
        [name:"t-shirt", weight:24, value:15],
        [name:"trousers", weight:48, value:10],
        [name:"umbrella", weight:73, value:40],
        [name:"waterproof trousers", weight:42, value:70],
        [name:"waterproof overclothes", weight:43, value:75],
        [name:"note-case", weight:22, value:80],
        [name:"sunglasses", weight:7, value:20],
        [name:"towel", weight:18, value:12],
        [name:"socks", weight:4, value:50],
        [name:"book", weight:30, value:10],
]
 
[knapsack01bf, knapsack01dp].each { knapsack01 ->
    def start = System.currentTimeMillis()
    def packingList = knapsack01(items)
    def elapsed = System.currentTimeMillis() - start
 
    println "\n\n\nElapsed Time: ${elapsed/1000.0} s"
    println "Total Weight: ${totalWeight(packingList)}"
    println " Total Value: ${totalValue(packingList)}"
    packingList.each {
        printf ("  item: %-25s  weight:%4d  value:%4d\n", it.name, it.weight, it.value)
    }
}


//Output:
//Elapsed Time: 132.267 s
//Total Weight: 396
// Total Value: 1030
//  item: map                        weight:   9  value: 150
//  item: compass                    weight:  13  value:  35
//  item: water                      weight: 153  value: 200
//  item: sandwich                   weight:  50  value: 160
//  item: glucose                    weight:  15  value:  60
//  item: banana                     weight:  27  value:  60
//  item: suntan cream               weight:  11  value:  70
//  item: waterproof trousers        weight:  42  value:  70
//  item: waterproof overclothes     weight:  43  value:  75
//  item: note-case                  weight:  22  value:  80
//  item: sunglasses                 weight:   7  value:  20
//  item: socks                      weight:   4  value:  50
//
//
//
//Elapsed Time: 0.27 s
//Total Weight: 396
// Total Value: 1030
//  item: map                        weight:   9  value: 150
//  item: compass                    weight:  13  value:  35
//  item: water                      weight: 153  value: 200
//  item: sandwich                   weight:  50  value: 160
//  item: glucose                    weight:  15  value:  60
//  item: banana                     weight:  27  value:  60
//  item: suntan cream               weight:  11  value:  70
//  item: waterproof trousers        weight:  42  value:  70
//  item: waterproof overclothes     weight:  43  value:  75
//  item: note-case                  weight:  22  value:  80
//  item: sunglasses                 weight:   7  value:  20
//  item: socks                      weight:   4  value:  50
