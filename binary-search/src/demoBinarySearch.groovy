// Both solutions use sublists and a tracking offset in preference to "high" and "low".


// - Recursive Solution
 
def binSearchR
//define binSearchR closure.
binSearchR = { a, key, offset=0 ->
    def n = a.size()
    def m = n.intdiv(2)
    a.empty \
        ? ["insertion point": offset] \
        : a[m] > key \
            ? binSearchR(a[0..<m],key, offset) \
            : a[m] < target \
                ? binSearchR(a[(m + 1)..<n],key, offset + m + 1) \
                : [index: offset + m]
}
 

// - Iterative Solution

def binSearchI = { aList, target ->
    def a = aList
    def offset = 0
    while (!a.empty) {
        def n = a.size()
        def m = n.intdiv(2)
        if(a[m] > target) {
            a = a[0..<m]
        } else if (a[m] < target) {
            a = a[(m + 1)..<n]
            offset += m + 1
        } else {
            return [index: offset + m]
        }
    }
    return ["insertion point": offset]
}


// Test:

def a = [] as Set
def random = new Random()
while (a.size() < 20) { a << random.nextInt(30) }
def source = a.sort()
source[0..-2].eachWithIndex { si, i -> assert si < source[i+1] }
 
println "${source}"
1.upto(5) {
    target = random.nextInt(10) + (it - 2) * 10
    print "Trial #${it}. Looking for: ${target}"
    def answers = [binSearchR, binSearchI].collect { search ->
        search(source, target)
    }
    assert answers[0] == answers[1]
    println """
    Answer: ${answers[0]}, : ${source[answers[0].values().iterator().next()]}"""
}
