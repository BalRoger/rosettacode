// Solution:

def a; a = { k, x1, x2, x3, x4, x5 ->
    def b; b = {
        a (--k, b, x1, x2, x3, x4)
    }
    k <= 0 ? x4() + x5() : b()
}

def x = { n -> { it -> n } }


// Test 1:

println (a(10, x(1), x(-1), x(-1), x(1), x(0)))

// This test overflowed the stack at the default stack size. On my system I required "-Xss1409k"
// or larger to run successfully.


// Test 2:

(0..20).each { k ->
    printf ("%3d: %7d\n", k, a(k, x(1), x(-1), x(-1), x(1), x(0)))
}

// This test required "-Xss345m" to avoid overflow.
