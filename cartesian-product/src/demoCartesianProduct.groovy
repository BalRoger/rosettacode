// Solution:
// The following ''CartesianCategory'' class allows for modification of regular ''Iterable'' interface behavior, overloading ''Iterable'''s ''multiply'' (*) operator to perform a Cartesian Product when the second operand is also an ''Iterable''.
class CartesianCategory {
    static Iterable multiply(Iterable a, Iterable b) {
        assert [a,b].every { it != null }
        def (m,n) = [a.size(),b.size()]
        (0..<(m*n)).inject([]) { prod, i -> prod << [a[i.intdiv(n)], b[i%n]].flatten() }
    }
}


// Test:
// The ''mixin'' method call is necessary to make the multiply (*) operator work.
Iterable.metaClass.mixin CartesianCategory

println "\nCore Solution:"
println "[1, 2] × [3, 4] = ${[1, 2] * [3, 4]}"
println "[3, 4] × [1, 2] = ${[3, 4] * [1, 2]}"
println "[1, 2] × [] = ${[1, 2] * []}"
println "[] × [1, 2] = ${[] * [1, 2]}"

println "\nExtra Credit:"
println "[1776, 1789] × [7, 12] × [4, 14, 23] × [0, 1] = ${[1776, 1789] * [7, 12] * [4, 14, 23] * [0, 1]}"
println "[1, 2, 3] × [30] × [500, 100] = ${[1, 2, 3] * [30] * [500, 100]}"
println "[1, 2, 3] × [] × [500, 100] = ${[1, 2, 3] * [] * [500, 100]}"

println "\nNon-Numeric Example:"
println "[John,Paul,George,Ringo] × [Emerson,Lake,Palmer] × [Simon,Garfunkle] = ["
( ["John","Paul","George","Ringo"] * ["Emerson","Lake","Palmer"] * ["Simon","Garfunkle"] ).each { println "\t${it}," }
println "]"


// Output:

//[1, 2] × [3, 4] = [[1, 3], [1, 4], [2, 3], [2, 4]]
//[3, 4] × [1, 2] = [[3, 1], [3, 2], [4, 1], [4, 2]]
//[1, 2] × [] = []
//[] × [1, 2] = []
//[1776, 1789] × [7, 12] × [4, 14, 23] × [0, 1] = [[1776, 7, 4, 0], [1776, 7, 4, 1], [1776, 7, 14, 0], [1776, 7, 14, 1], [1776, 7, 23, 0], [1776, 7, 23, 1], [1776, 12, 4, 0], [1776, 12, 4, 1], [1776, 12, 14, 0], [1776, 12, 14, 1], [1776, 12, 23, 0], [1776, 12, 23, 1], [1789, 7, 4, 0], [1789, 7, 4, 1], [1789, 7, 14, 0], [1789, 7, 14, 1], [1789, 7, 23, 0], [1789, 7, 23, 1], [1789, 12, 4, 0], [1789, 12, 4, 1], [1789, 12, 14, 0], [1789, 12, 14, 1], [1789, 12, 23, 0], [1789, 12, 23, 1]]
//[1, 2, 3] × [30] × [500, 100] = [[1, 30, 500], [1, 30, 100], [2, 30, 500], [2, 30, 100], [3, 30, 500], [3, 30, 100]]
//[1, 2, 3] × [] × [500, 100] = []
//[John,Paul,George,Ringo] × [Emerson,Lake,Palmer] × [Simon,Garfunkle] = [
//    [John, Emerson, Simon],
//    [John, Emerson, Garfunkle],
//    [John, Lake, Simon],
//    [John, Lake, Garfunkle],
//    [John, Palmer, Simon],
//    [John, Palmer, Garfunkle],
//    [Paul, Emerson, Simon],
//    [Paul, Emerson, Garfunkle],
//    [Paul, Lake, Simon],
//    [Paul, Lake, Garfunkle],
//    [Paul, Palmer, Simon],
//    [Paul, Palmer, Garfunkle],
//    [George, Emerson, Simon],
//    [George, Emerson, Garfunkle],
//    [George, Lake, Simon],
//    [George, Lake, Garfunkle],
//    [George, Palmer, Simon],
//    [George, Palmer, Garfunkle],
//    [Ringo, Emerson, Simon],
//    [Ringo, Emerson, Garfunkle],
//    [Ringo, Lake, Simon],
//    [Ringo, Lake, Garfunkle],
//    [Ringo, Palmer, Simon],
//    [Ringo, Palmer, Garfunkle],
//]

