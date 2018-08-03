// Lists As Arrays

def aa = [ 1, 25, 31, -3 ]           // List literal
def a = [0] * 100                    // List "multiplcation": 100 zeroes
def b = 1..9                         // range literal (type IntRange implements List)
def c = (1..10).collect { 2.0**it }  // List.collect() maps list elements into a new list: each output element is 2**(corresponding invoking list element)
 
// There are no true "multi-dimensional" lists or arrays in Groovy (as in most C-derived languages).
// Use lists of lists in natural ("row major") order as a stand in.
def d = (0..1).collect { i -> (1..5).collect { j -> 2**(5*i+j) as double } }
def e = [ [  1.0,  2.0,  3.0,  4.0 ],
          [  5.0,  6.0,  7.0,  8.0 ],
          [  9.0, 10.0, 11.0, 12.0 ],
          [ 13.0, 14.0, 15.0, 16.0 ] ]

//////////////////

// Demo

println aa
println b
println c
println()
d.each { print "["; it.each { elt -> printf "%7.1f ", elt }; println "]" }
println()
e.each { print "["; it.each { elt -> printf "%7.1f ", elt }; println "]" }

//////////////////////////////////////////////////////////////////////////////////////////

// Identity Matrix

def identity = { n ->
   (1..n).collect { i -> (1..n).collect { j -> i==j ? 1.0 : 0.0 } }
}

//////////////////

// Demo

println()
def i2 = identity(2)
def i15 = identity(15)
 
 
i2.each { print "["; it.each { elt -> printf "%4.1f ", elt }; println "]" }
println()
i15.each { print "["; it.each { elt -> printf "%4.1f ", elt }; println "]" }

//////////////////////////////////////////////////////////////////////////////////////////

// Zero-based Indexing

def strings = ['Mary', 'had', 'a', 'little', 'lamb', ". It's", 'fleece', 'was', 'white', 'as', 'snow']
 
println()
println strings
 
strings[0] = 'Arthur'
strings[4] = 'towel'
strings[6] = 'stain'
strings[8] = 'ripe'
strings[10] = 'strawberries'
 
println strings

// Negative Indexing

println()
println strings[-1]

// Tuple And Range Indexing

println()
println strings[0, 7, 2, 3, 8]
println strings[0..4]
println strings[0..3, -5]
