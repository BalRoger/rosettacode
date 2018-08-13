// Unlike most solutions here this solution manipulates more-or-less actual stacks of more-or-less
// actual rings.

def tail = { list, n ->  def m = list.size(); list.subList(([m - n, 0].max()),m) }
 
final STACK = [A:[],B:[],C:[]]
 
def report = { it -> }
def check = { it -> }
 
def moveRing = { from, to ->  to << from.pop(); report(); check(to) }
 
def moveStack
moveStack = { from, to, using = STACK.values().find { !(it.is(from) || it.is(to)) } ->
    if (!from) return
    def n = from.size()
    moveStack(tail(from, n-1), using, to)
    moveRing(from, to)
    moveStack(tail(using, n-1), to, from)
}


// Test program:

enum Ring {
    S('°'), M('o'), L('O'), XL('( )');
    private sym
    private Ring(sym) { this.sym=sym }
    String toString() { sym }
}
 
report = { STACK.each { k, v ->  println "${k}: ${v}" }; println() }
check = { to -> assert to == ([] + to).sort().reverse() }
 
Ring.values().reverseEach { STACK.A << it }
report()
check(STACK.A)
moveStack(STACK.A, STACK.C)