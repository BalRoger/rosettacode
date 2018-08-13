// Groovy does not explicitly support anonymous recursion. This solution is a kludgy trick that
// takes advantage of the "owner" scoping variable (reserved word) for closures.

def fib = {
    assert it > -1
    {i -> i < 2 ? i : {j -> owner.call(j)}(i-1) + {k -> owner.call(k)}(i-2)}(it)
}


// Test:

def fib0to20 = (0..20).collect(fib)
println fib0to20
 
try {
    println fib(-25)
} catch (Throwable e) {
    println "KABOOM!!"
    println e.message
}


//Output:
//[0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765]
//KABOOM!!
//assert it > -1
//       |  |
//       |  false
//       -25
