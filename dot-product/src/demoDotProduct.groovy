// Solution:
def dotProduct = { x, y ->
    assert x && y && x.size() == y.size()
    [x, y].transpose().collect{ xx, yy -> xx * yy }.sum()
}

// Test:
println dotProduct([1, 3, -5], [4, -2, -1])