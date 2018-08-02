// Solution:
def beadSort = { list ->
    final nPoles = list.max()
    list.collect {
        print "."
        ([true] * it) + ([false] * (nPoles - it))
    }.transpose().collect { pole ->
        print "."
        pole.findAll { ! it } + pole.findAll { it }
    }.transpose().collect{ beadTally ->
        beadTally.findAll{ it }.size()
    }
}

// Annotated Solution (same solution really):
def beadSortVerbose = { list ->
    final nPoles = list.max()
    // each row is a number tally-arrayed across the abacus
    def beadTallies = list.collect { number ->
        print "."
        // true == bead, false == no bead
        ([true] * number) + ([false] * (nPoles - number))
    }
    // each row is an abacus pole
    def abacusPoles = beadTallies.transpose()
    def abacusPolesDrop = abacusPoles.collect { pole ->
        print "."
        // beads drop to the BOTTOM of the pole
        pole.findAll { ! it } + pole.findAll { it }
    }
    // each row is a number again
    def beadTalliesDrop = abacusPolesDrop.transpose()
    beadTalliesDrop.collect{ beadTally -> beadTally.findAll{ it }.size() }
}

// Test:
println beadSort([23,76,99,58,97,57,35,89,51,38,95,92,24,46,31,24,14,12,57,78,4])
println beadSort([88,18,31,44,4,0,8,81,14,78,20,76,84,33,73,75,82,5,62,70,12,7,1])
