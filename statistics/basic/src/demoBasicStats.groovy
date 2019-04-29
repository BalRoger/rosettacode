// Brute Force Solution (will not scale to sample size of 1 trillion):

def x̄ = { sample -> sample.sum() / sample.size() }
def σ = { sample -> def m = x̄(sample); (sample.sum { (it - m)**2 } / sample.size()) ** 0.5 }

def hist = { range, increment, sample ->
    def histogram = [:]
    for ( def i = range.from; i < range.to; i+= increment ) {
        histogram["$i..${i + increment}".toString()] = sample.count { i <= it && it < i + increment }
    }
    def scale = (Math.floor(Math.log10(histogram.values().max())) as int) - 1
    histogram.keySet().each {
        histogram[it] = '*' * ((histogram[it] as int).intdiv(10**scale))
    }
    histogram
}


// Test:
def r = new Random()
def sampleSizes = [100, 1000]//, 10000, 1000000]

sampleSizes.each { sampleSize ->
    def sample = (0..<sampleSize).collect {
        r.nextDouble()
    }
    assert sample.size() == sampleSize

    def mean = x̄(sample)
    def sdev = σ(sample)
    def graph = hist((0.0g..1.0g), 0.1g, sample)

    println "Sample Size: $sampleSize"
    graph.each { k, v ->
        println "${k}: ${v}"
    }
    println "x̄ = $mean    σ = $sdev\n\n"
}
/*
Sample Size: 100
0.0..0.1: ************
0.1..0.2: *********
0.2..0.3: ***********
0.3..0.4: **********
0.4..0.5: *******
0.5..0.6: *************
0.6..0.7: ******
0.7..0.8: ***********
0.8..0.9: **************
0.9..1.0: *******
x̄ = 0.49341884183890294    σ = 0.29667213001521914


Sample Size: 1000
0.0..0.1: ***********
0.1..0.2: ********
0.2..0.3: **********
0.3..0.4: *********
0.4..0.5: *********
0.5..0.6: ********
0.6..0.7: **********
0.7..0.8: ***********
0.8..0.9: **********
0.9..1.0: *********
x̄ = 0.5043231055444959    σ = 0.29236629576433965


Sample Size: 10000
0.0..0.1: **********
0.1..0.2: *********
0.2..0.3: **********
0.3..0.4: **********
0.4..0.5: *********
0.5..0.6: *********
0.6..0.7: *********
0.7..0.8: *********
0.8..0.9: *********
0.9..1.0: **********
x̄ = 0.5009408691685082    σ = 0.2892393904198257


Sample Size: 1000000
0.0..0.1: **********
0.1..0.2: **********
0.2..0.3: *********
0.3..0.4: **********
0.4..0.5: **********
0.5..0.6: *********
0.6..0.7: **********
0.7..0.8: **********
0.8..0.9: **********
0.9..1.0: *********
x̄ = 0.49968291801710696    σ = 0.288671809987513

*/