// Solution:

def top  = { it.size() > 1 ? it[0..-2] : '' }
def tail = { it.size() > 1 ? it[1..-1] : '' }


// Test:

def testVal = 'upraisers'
println """
original: ${testVal}
top:      ${top(testVal)}
tail:     ${tail(testVal)}
top&tail: ${tail(top(testVal))}
"""