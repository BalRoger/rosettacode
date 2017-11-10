// Groovy has support for swapping built in:
(a, b) = [b, a]

// But the task calls for a "generic swap method" to be written, so here it is:
def swap(a, b) {
    [b, a]
}

// This function doesn't mutate anything, but simply returns a new list with the order of the
// elements switched. It can be used like shown below:
def (x, y) = swap(1, 3)
assert x == 3
assert y == 1

// Some examples here show an in-place swap of indexed elements in an array or collection, so for
// completeness here is an in-place swap of arbitrary indexed elements in a list:
def listSwap = { a, i, j ->
    assert (0..<(a.size())).containsAll([i,j]);
    a[[j,i]] = a[[i,j]]
}
 
def list = [2,4,6,8]
listSwap(list, 1, 3)
assert list == [2,8,6,4]
