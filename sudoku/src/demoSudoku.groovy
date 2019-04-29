// Adaptive "Non-guessing Then Guessing" Solution

// Non-guessing part is iterative. Guessing part is recursive. Implementation uses exception
// handling to back out of bad guesses.

final CELL_VALUES = ('1'..'9')
 
class GridException extends Exception {
    GridException(String message) { super(message) }
}
 
def string2grid = { string ->
    assert string.size() == 81
    (0..8).collect { i -> (0..8).collect { j -> string[9*i+j] } }
}
 
def gridRow = { grid, slot -> grid[slot.i] as Set }
 
def gridCol = { grid, slot -> grid.collect { it[slot.j] } as Set }
 
def gridBox = { grid, slot ->
    def t, l; (t, l) = [slot.i.intdiv(3)*3, slot.j.intdiv(3)*3]
    (0..2).collect { row -> (0..2).collect { col -> grid[t+row][l+col] } }.flatten() as Set
}
 
def slotList = { grid ->
    def slots = (0..8).collect { i -> (0..8).findAll { j -> grid[i][j] == '.' } \
            .collect {j -> [i: i, j: j] } }.flatten()
}
 
def assignCandidates = { grid, slots = slotList(grid) ->
    slots.each { slot ->
        def unavailable = [gridRow, gridCol, gridBox].collect { it(grid, slot) }.sum() as Set
        slot.candidates = CELL_VALUES - unavailable
    }
    slots.sort { it.candidates.size() }
    if (slots && ! slots[0].candidates) {
        throw new GridException('Invalid Sudoku Grid, overdetermined slot: ' + slots[-1])
    }
    slots
}
 
def isSolved = { grid -> ! (grid.flatten().find { it == '.' }) }
 
def solve 
solve = { grid ->
    def slots = assignCandidates(grid)
    if (! slots) { return grid }
    while (slots[0].candidates.size() == 1) {
        def slot = slots.pop()
        grid[slot.i][slot.j] = slot.candidates[0]
        if (! slots) { return grid }
        slots = assignCandidates(grid, slots)
    }
    if (! slots) { return grid } 
    def slot = slots.pop()
    slot.candidates.each {
        if (! isSolved(grid)) {
            try {
                def sGrid = grid.collect { row -> row.collect { cell -> cell } }
                sGrid[slot.i][slot.j] = it
                grid = solve(sGrid)
            } catch (GridException ge) {
                grid[slot.i][slot.j] = '.'
            }
        }
    }
    if (!isSolved(grid)) {
        slots = assignCandidates(grid)
        throw new GridException('Invalid Sudoku Grid, underdetermined slots: ' + slots)
    }
    grid
}


// Test/Benchmark Cases

// Mentions of "exceptionally difficult" example in Wikipedia refer to this (former) page:
// [Exceptionally difficult Sudokus]

def sudokus = [
  //Used in Curry solution:                             ~ 0.1 seconds
    '819..5.....2...75..371.4.6.4..59.1..7..3.8..2..3.62..7.5.7.921..64...9.....2..438',
 
  //Used in Perl and PicoLisp solutions:                ~ 0.1 seconds
    '53..247....2...8..1..7.39.2..8.72.49.2.98..7.79.....8.....3.5.696..1.3...5.69..1.',
 
  //Used in Fortran solution:                           ~ 0.1 seconds
    '..3.2.6..9..3.5..1..18.64....81.29..7.......8..67.82....26.95..8..2.3..9..5.1.3..',
 
  //Used in many other solutions, notably Algol 68:     ~ 0.1 seconds
    '394..267....3..4..5..69..2..45...9..6.......7..7...58..1..67..8..9..8....264..735',
 
  //Used in C# solution:                                ~ 0.2 seconds
    '97.3...6..6.75.........8.5.......67.....3.....539..2..7...25.....2.1...8.4...73..',
 
  //Used in Oz solution:                                ~ 0.2 seconds
    '4......6.5...8.9..3....1....2.7....1.9.....4.8....3.5....2....7..6.5...8.1......6',
 
  //Used in many other solutions, notably C++:          ~ 0.3 seconds
    '85...24..72......9..4.........1.7..23.5...9...4...........8..7..17..........36.4.',
 
  //Used in VBA solution:                               ~ 0.3 seconds
    '..1..5.7.92.6.......8...6...9..2.4.1.........3.4.8..9...7...3.......7.69.1.8..7..',
 
  //Used in Forth solution:                             ~ 0.8 seconds
    '.9...4..7.....79..8........4.58.....3.......2.....97.6........4..35.....2..6...8.',
 
  //3rd "exceptionally difficult" example in Wikipedia: ~ 2.3 seconds
    '12.3....435....1....4........54..2..6...7.........8.9...31..5.......9.7.....6...8',
 
  //Used in Curry solution:                             ~ 2.4 seconds
    '9..2..5...4..6..3...3.....6...9..2......5..8...7..4..37.....1...5..2..4...1..6..9',
 
  //"AL Escargot", so-called "hardest sudoku" (HA!):    ~ 3.0 seconds
    '1....7.9..3..2...8..96..5....53..9...1..8...26....4...3......1..4......7..7...3..',
 
  //1st "exceptionally difficult" example in Wikipedia: ~ 6.5 seconds
    '12.4..3..3...1..5...6...1..7...9.....4.6.3.....3..2...5...8.7....7.....5.......98',
 
  //Used in Bracmat, Phix, and Scala solutions:         ~ 6.7 seconds
    '..............3.85..1.2.......5.7.....4...1...9.......5......73..2.1........4...9',
 
  //2nd "exceptionally difficult" example in Wikipedia: ~ 8.8 seconds
    '.......39.....1..5..3.5.8....8.9...6.7...2...1..4.......9.8..5..2....6..4..7.....',
 
  //Used in MATLAB solution:                            ~15   seconds
    '....839..1......3...4....7..42.3....6.......4....7..1..2........8...92.....25...6',
 
  //4th "exceptionally difficult" example in Wikipedia: ~29   seconds
    '..3......4...8..36..8...1...4..6..73...9..........2..5..4.7..686........7..6..5..',

//  //Used in Phix solution (#2):                         ~98   seconds
//    '.......3684..............2....2.3....1....7.....6..4.....41..5...3...2..6........',

  //Used in Phix solution (#4):                         ~ 2.6 seconds
    '....376.....6...9...8.....4.9......16.......93......4.7.....8...1...9.....254....',

  //Used in Phix solution (#10):                        ~ 2.6 seconds
    '..4.5..6..6.1..8.93....7....8....5.....4.3.....6....7....2....61.5..4.3..2..7.1..'
]
 
sudokus.each { sudoku ->
    def grid = string2grid(sudoku)
    println '\nPUZZLE'
    grid.each { println it }
 
    println '\nSOLUTION'
    def start = System.currentTimeMillis()
    def solution = solve(grid)
    def elapsed = (System.currentTimeMillis() - start)/1000
    solution.each { println it }
    println "\nELAPSED: ${elapsed} seconds"
}