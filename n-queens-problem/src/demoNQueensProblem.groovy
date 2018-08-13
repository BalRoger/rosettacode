// Distinct Solutions
// This solver starts with the N! distinct solutions to the N-Rooks problem and then keeps only
// the candidates in which all Queens are mutually diagonal-safe.

def listOrder = { a, b ->
    def k = [a.size(), b.size()].min()
    def i = (0..<k).find { a[it] != b[it] }
    (i != null) ? a[i] <=> b[i] : a.size() <=> b.size()
}
 
def orderedPermutations = { list ->
    def n = list.size()
    (0..<n).permutations().sort(listOrder)
}
 
def diagonalSafe = { list ->
    def n = list.size()
    n == 1 || (0..<(n-1)).every{ i ->
        ((i+1)..<n).every{ j ->
            !([list[i]+j-i, list[i]+i-j].contains(list[j]))
        }
    }
}
 
def queensDistinctSolutions = { n ->
    // each permutation is an N-Rooks solution
    orderedPermutations((0..<n)).findAll (diagonalSafe)
}


// Unique Solutions
// Unique solutions are equivalence classes of distinct solutions, factoring out all reflections
// and rotations of a given solution. See the Wikipedia page for more details.

class Reflect {
    public static final diag = { list ->
        final n = list.size()
        def tList = [0] * n
        (0..<n).each { tList[list[it]] = it }
        tList
    }
 
    public static final vert = { list ->
        list.reverse()
    }
 
    public static final horiz = { list ->
        final n = list.size()
        list.collect { n - it - 1 }
    }
}
 
enum Rotations {
    r0([]),
    r90([Reflect.vert, Reflect.diag]),
    r180([Reflect.vert, Reflect.diag, Reflect.vert, Reflect.diag]),
    r270([Reflect.diag, Reflect.vert]);
 
    private final List operations
 
    private Rotations(List ops) {
        operations = ops ?: []
    }
 
    public static void eliminateDups(primary, solutions) {
        (r0..r270).each { rot -> rot.eliminateDuplicates(primary, solutions) }
    }
 
    private void eliminateDuplicates(primary, solutions) {
        def rotated = [] + primary
        operations.each { rotated = it(rotated) }
        solutions.removeAll([rotated, Reflect.vert(rotated)])
    }
}
 
def queensUniqueSolutions = { start ->
    assert start instanceof Number || start instanceof List
    def qus = (start instanceof Number) \
                ? queensDistinctSolutions(start) \
                : [] + start
    for (def i = 0; i < qus.size()-1; i++) {
        Rotations.eliminateDups(qus[i], qus[(i+1)..<(qus.size())])
    }
    qus
}


// Test and Results
// This script tests both distinct and unique solution lists.

(1..9).each { n ->
    def qds = queensDistinctSolutions(n)
    def qus = queensUniqueSolutions(qds)
    println ([boardSize:n, "number of distinct solutions":qds.size(), "number of unique solutions":qus.size()])
    if(n < 9) { qus.each { println it } }
    else { println "first:${qus[0]}"; println "last:${qus[-1]}" }
    println()
}


// Interpreting the Results:
//
// Each individual result is given as a list of N numbers. Each number represents a column number
// within the list-indexed row. So, the following 4-queens solution:
//
//    [1, 3, 0, 2]
//
// should be interpreted as follows:
//
//    row 0 has a queen in column 1
//    row 1 has a queen in column 3
//    row 2 has a queen in column 0
//    row 3 has a queen in column 2
//
// In other words, this:
//
//    |///| Q |///|   |
//     --- --- --- --- 
//    |   |///|   |/Q/|
//     --- --- --- --- 
//    |/Q/|   |///|   |
//     --- --- --- --- 
//    |   |///| Q |///|
//
// Results:
//
// [boardSize:1, number of distinct solutions:1, number of unique solutions:1]
// [0]
//
// [boardSize:2, number of distinct solutions:0, number of unique solutions:0]
//
// [boardSize:3, number of distinct solutions:0, number of unique solutions:0]
//
// [boardSize:4, number of distinct solutions:2, number of unique solutions:1]
// [1, 3, 0, 2]
//
// [boardSize:5, number of distinct solutions:10, number of unique solutions:2]
// [0, 2, 4, 1, 3]
// [1, 4, 2, 0, 3]
//
// [boardSize:6, number of distinct solutions:4, number of unique solutions:1]
// [1, 3, 5, 0, 2, 4]
//
// [boardSize:7, number of distinct solutions:40, number of unique solutions:6]
// [0, 2, 4, 6, 1, 3, 5]
// [0, 3, 6, 2, 5, 1, 4]
// [1, 3, 0, 6, 4, 2, 5]
// [1, 4, 0, 3, 6, 2, 5]
// [1, 4, 6, 3, 0, 2, 5]
// [1, 5, 2, 6, 3, 0, 4]
//
// [boardSize:8, number of distinct solutions:92, number of unique solutions:12]
// [0, 4, 7, 5, 2, 6, 1, 3]
// [0, 5, 7, 2, 6, 3, 1, 4]
// [1, 3, 5, 7, 2, 0, 6, 4]
// [1, 4, 6, 0, 2, 7, 5, 3]
// [1, 4, 6, 3, 0, 7, 5, 2]
// [1, 5, 0, 6, 3, 7, 2, 4]
// [1, 5, 7, 2, 0, 3, 6, 4]
// [1, 6, 2, 5, 7, 4, 0, 3]
// [1, 6, 4, 7, 0, 3, 5, 2]
// [2, 4, 1, 7, 0, 6, 3, 5]
// [2, 4, 7, 3, 0, 6, 1, 5]
// [2, 5, 1, 4, 7, 0, 6, 3]
//
// [boardSize:9, number of distinct solutions:352, number of unique solutions:46]
// first:[0, 2, 5, 7, 1, 3, 8, 6, 4]
// last:[3, 1, 6, 8, 0, 7, 4, 2, 5]
