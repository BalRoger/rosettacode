// Solution:

random = new Random()
 
int rand5() {
    random.nextInt(5) + 1
}
 
int rand7From5() {
    def raw = 25
    while (raw > 21) {
        raw = 5*(rand5() - 1) + rand5()
    }
    (raw % 7) + 1
}


// Test:

def test = {
    (1..6). each {
        def counts = [0g, 0g, 0g, 0g, 0g, 0g, 0g]
        def target = 10g**it
        def popSize = 7*target
        (0..<(popSize)).each {
            def i = rand7From5() - 1
            counts[i] = counts[i] + 1g
        }
        BigDecimal stdDev = (counts.collect { it - target}.collect { it * it }.sum() / popSize) ** 0.5g
        def countMap = (0..<counts.size()).inject([:]) { map, index -> map + [(index+1):counts[index]] }
 
        println """\
         counts: ${countMap}
population size: ${popSize}
        std dev: ${stdDev.round(new java.math.MathContext(3))}
"""
    }
}
 
4.times {
    println """
TRIAL #${it+1}
=============="""
    test(it)
}