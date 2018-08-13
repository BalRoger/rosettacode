// Solution:

def isOdd = { int i -> (i & 1) as boolean }
def isEven = { int i -> ! isOdd(i) }


// Test:

1.step(20, 2) { assert isOdd(it) }
 
50.step(-50, -2) { assert isEven(it) }
