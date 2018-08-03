// In Groovy, the float and double types follow IEEE numeric formats and rules. Here is a solution for double:
def dividesByZero = { double n, double d ->
    assert ! n.infinite : 'Algorithm fails if the numerator is already infinite.'
    (n/d).infinite || (n/d).naN
}

// Test program:
((3d)..(0d)).each { i ->
    ((2d)..(0d)).each { j ->
        println "${i}/${j} divides by zero? " + dividesByZero(i,j)
    }
}