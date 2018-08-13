// Solution:

def grayEncode = { i ->
    i ^ (i >>> 1)
}
 
def grayDecode;
grayDecode = { int code ->
    if(code <= 0) return 0
    def h = grayDecode(code >>> 1)
    return (h << 1) + ((code ^ h) & 1)
}


// Test:

def binary = { i, minBits = 1 ->
    def remainder = i
    def bin = []
    while (remainder > 0 || bin.size() <= minBits) {
        bin << (remainder & 1)
        remainder >>>= 1
    }
    bin
}
 
println "number   binary   gray code   decode"
println "======   ======   =========   ======"
(0..31).each {
    def code = grayEncode(it)
    def decode = grayDecode(code)
    def iB = binary(it, 5)
    def cB = binary(code, 5)
    printf("    %2d    %1d%1d%1d%1d%1d       %1d%1d%1d%1d%1d       %2d",
        it, iB[4],iB[3],iB[2],iB[1],iB[0], cB[4],cB[3],cB[2],cB[1],cB[0], decode)
    println()
}


//Results:
//
//number   binary   gray code   decode
//======   ======   =========   ======
//     0    00000       00000        0
//     1    00001       00001        1
//     2    00010       00011        2
//     3    00011       00010        3
//     4    00100       00110        4
//     5    00101       00111        5
//     6    00110       00101        6
//     7    00111       00100        7
//     8    01000       01100        8
//     9    01001       01101        9
//    10    01010       01111       10
//    11    01011       01110       11
//    12    01100       01010       12
//    13    01101       01011       13
//    14    01110       01001       14
//    15    01111       01000       15
//    16    10000       11000       16
//    17    10001       11001       17
//    18    10010       11011       18
//    19    10011       11010       19
//    20    10100       11110       20
//    21    10101       11111       21
//    22    10110       11101       22
//    23    10111       11100       23
//    24    11000       10100       24
//    25    11001       10101       25
//    26    11010       10111       26
//    27    11011       10110       27
//    28    11100       10010       28
//    29    11101       10011       29
//    30    11110       10001       30
//    31    11111       10000       31
