// Correct Solution:

def createFoos1 = { n -> (0..<n).collect { new Foo() } }


// Incorrect Solution:

// Following fails, creates n references to same object
def createFoos2 = {n -> [new Foo()] * n }


// Test:

[createFoos1, createFoos2].each { createFoos ->
    print "Objects distinct for n = "
    (2..<20).each { n ->
        def foos = createFoos(n)
        foos.eachWithIndex { here, i ->
            foos.eachWithIndex { there, j ->
                assert (here == there) == (i == j)
            }
        }
        print "${n} "
    }
    println()
}
