// Solution

def pascal
pascal = { n -> (n <= 1) ? [1] : [[0] + pascal(n - 1), pascal(n - 1) + [0]].transpose().collect { it.sum() } }

/////////////////////////

// Test

def count = 15
(1..count).each { n ->
    printf ("%2d:", n); (0..(count-n)).each { print "    " }; pascal(n).each{ printf("%6d  ", it) }; println ""
}