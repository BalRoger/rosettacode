// This solution assumes the radix is a power of 2:
def radixSort = { final radixExponent, list ->
    def fromBuckets = new TreeMap([0:list])
    def toBuckets = new TreeMap()
    final radix = 2**radixExponent
    final mask = radix - 1
    final radixDigitSize = (int)Math.ceil(64/radixExponent)
    final digitWidth = radixExponent
    (0..<radixDigitSize).each { radixDigit ->
        fromBuckets.values().findAll { it != null }.flatten().each {
            print '.'
            long bucketNumber = (long)((((long)it) >>> digitWidth*radixDigit) & mask)
            toBuckets[bucketNumber] = toBuckets[bucketNumber] ?: []
            toBuckets[bucketNumber] << it
        }
        (fromBuckets, toBuckets) = [toBuckets, fromBuckets]
        toBuckets.clear()
    }
    final overflow = 2**(63 % radixExponent)
    final pos = {it < overflow}
    final neg = {it >= overflow}
    final keys = fromBuckets.keySet()
    final twosComplIndx = [] + (keys.findAll(neg)) + (keys.findAll(pos))
    twosComplIndx.collect { fromBuckets[it] }.findAll { it != null }.flatten()
}

// Test:
println (radixSort(3, [23,76,99,58,97,57,35,89,51,38,95,92,24,46,31,24,14,12,57,78,4]))
println (radixSort(3, [88,18,31,44,4,0,8,81,14,78,20,76,84,33,73,75,82,5,62,70,12,7,1]))
println (radixSort(3, [23,-76,-990,580,97,57,350000,Long.MAX_VALUE,89,Long.MIN_VALUE,51,38,95*2**48,92,-24*2**48,46,31*2**32,24,14,12,57,78,4]))
println ()
println (radixSort(8, [23,76,99,58,97,57,35,89,51,38,95,92,24,46,31,24,14,12,57,78,4]))
println (radixSort(8, [88,18,31,44,4,0,8,81,14,78,20,76,84,33,73,75,82,5,62,70,12,7,1]))
println (radixSort(8, [23,-76,-990,580,97,57,350000,Long.MAX_VALUE,89,Long.MIN_VALUE,51,38,95*2**48,92,-24*2**48,46,31*2**32,24,14,12,57,78,4]))
println ()
println (radixSort(11, [23,76,99,58,97,57,35,89,51,38,95,92,24,46,31,24,14,12,57,78,4]))
println (radixSort(11, [88,18,31,44,4,0,8,81,14,78,20,76,84,33,73,75,82,5,62,70,12,7,1]))
println (radixSort(11, [23,-76,-990,580,97,57,350000,Long.MAX_VALUE,89,Long.MIN_VALUE,51,38,95*2**48,92,-24*2**48,46,31*2**32,24,14,12,57,78,4]))
println ()
println (radixSort(16, [23,76,99,58,97,57,35,89,51,38,95,92,24,46,31,24,14,12,57,78,4]))
println (radixSort(16, [88,18,31,44,4,0,8,81,14,78,20,76,84,33,73,75,82,5,62,70,12,7,1]))
println (radixSort(16, [23,-76,-990,580,97,57,350000,Long.MAX_VALUE,89,Long.MIN_VALUE,51,38,95*2**48,92,-24*2**48,46,31*2**32,24,14,12,57,78,4]))
println ()
println (radixSort(32, [23,76,99,58,97,57,35,89,51,38,95,92,24,46,31,24,14,12,57,78,4]))
println (radixSort(32, [88,18,31,44,4,0,8,81,14,78,20,76,84,33,73,75,82,5,62,70,12,7,1]))
println (radixSort(32, [23,-76,-990,580,97,57,350000,Long.MAX_VALUE,89,Long.MIN_VALUE,51,38,95*2**48,92,-24*2**48,46,31*2**32,24,14,12,57,78,4]))
