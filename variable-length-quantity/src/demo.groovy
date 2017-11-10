// Solution:
final RADIX = 7
final MASK = 2**RADIX - 1
 
def octetify = { n ->
    def octets = []
    for (def i = n; i != 0; i >>>= RADIX) {
        octets << ((byte)((i & MASK) + (octets.empty ? 0 : MASK + 1)))
    }
    octets.reverse()
}
 
def deoctetify = { octets ->
    octets.inject(0) { long n, octet ->
        (n << RADIX) + ((int)(octet) & MASK)
    }
}

// Test (samples borrowed from Java example):
def testNumbers = [ 0x200000, 0x1fffff, 1, 127, 128, 589723405834L ]
 
testNumbers.each { a ->
    def octets = octetify(a)
    octets.each { printf "0x%02x ", it }; println ()
    def a1 = deoctetify(octets)
    assert a1 == a
}
