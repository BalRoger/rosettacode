// Solution:
// Uses [[Fibonacci_sequence#Analytic_8|Fibonacci sequence analytic formula]]

final φ = (1 + 5**(1/2))/2
def aFib = { (φ**it - (-φ)**(-it))/(5**(1/2)) as BigInteger }

def tallyFirstDigits = { size, generator ->
    def population = (0..<size).collect { generator(it) }
    def firstDigits = [0]*10
    population.each { number ->
        firstDigits[(number as String)[0] as int] ++
    }
    firstDigits
}


// Test:

def digitCounts = tallyFirstDigits(1000, aFib)
println "d    actual    predicted"
(1..<10).each {
    printf ("%d %10.6f %10.6f\n", it, digitCounts[it]/1000, Math.log10(1.0 + 1.0/it))
}


// Output:
//d    actual    predicted
//1   0.301000   0.301030
//2   0.177000   0.176091
//3   0.125000   0.124939
//4   0.095000   0.096910
//5   0.080000   0.079181
//6   0.067000   0.066947
//7   0.056000   0.057992
//8   0.053000   0.051153
//9   0.045000   0.045757
