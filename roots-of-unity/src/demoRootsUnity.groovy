
Number.metaClass.mixin ComplexCategory

/** The following closure creates a list of n evenly-spaced points around the unit circle,
 * useful in FFT calculations, among other things */
def rootsOfUnity = { n ->
   (0..<n).collect {
       1.cis(2 * Math.PI * it / n)
   }
}



// Test program:

def ε = 0.000000001  // tolerance: acceptable "wrongness" to account for rounding error

((1..6) + [16]). each { n ->
   println "rootsOfUnity(${n}):"
   def rou = rootsOfUnity(n)
   def rouPretty = rou.collect { [Math.round(it.real / ε) * ε, Math.round(it.imag / ε) * ε] as Complex }
   rouPretty.each { println (it.toString().replaceAll("0+ ", " ").replaceAll('0+$', "")) }
   assert rou[0] == 1
   def actual = n > 1 ? rou[Math.floor(n/2) as int] : rou[0]
   def expected = n > 1 ? (n%2 == 0) ? -1 : ~rou[Math.ceil(n/2) as int] : rou[0]
   def message = n > 1 ? (n%2 == 0) ? 'middle-most root should be -1' : 'two middle-most roots should be conjugates' : ''
   assert (actual - expected).abs() < ε : message
   assert rou.every { (it.ρ - 1) < ε } : 'all roots should have magnitude 1'
   println()
}