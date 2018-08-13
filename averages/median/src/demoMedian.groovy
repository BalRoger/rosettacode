// Solution (brute force sorting, with arithmetic averaging of dual midpoints (even sizes)):

def median(Iterable col) {
    def s = col as SortedSet
    if (s == null) return null
    if (s.empty) return 0
    def n = s.size()
    def m = n.intdiv(2)
    def l = s.collect { it }
    n%2 == 1 ? l[m] : (l[m] + l[m-1])/2 
}


// Test:

def a = [4.4, 2.3, -1.7, 7.5, 6.6, 0.0, 1.9, 8.2, 9.3, 4.5]
def sz = a.size()
 
(0..sz).each {
    println """${median(a[0..<(sz-it)])} == median(${a[0..<(sz-it)]})
${median(a[it..<sz])} == median(${a[it..<sz]})"""
}
