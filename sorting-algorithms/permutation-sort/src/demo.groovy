// Permutation sort is an astonishingly inefficient sort algorithm. To even begin to make it
// tractable, we need to be able to create enumerated permutations on the fly, rather than relying
// on Groovy's List.permutations() method. For a list of length N there are N! permutations. In
// this solution, makePermutation creates the Ith permutation to order based on a recursive
// construction of a unique indexed permutation. The sort method then checks to see if that
// permutation is sorted, and stops when it is.
// 
// I believe that this method of constructing permutations results in a stable sort, but I have not
// actually proven that assertion.

def factorial = { (it > 1) ? (2..it).inject(1) { i, j -> i*j } : 1 }
 
def makePermutation;
makePermutation = { list, i ->
    def n = list.size()
    if (n < 2) return list
    def fact = factorial(n-1)
    assert i < fact*n
 
    def index = i.intdiv(fact)
    [list[index]] + makePermutation(list[0..<index] + list[(index+1)..<n], i % fact)
}
 
def sorted = { a -> (1..<(a.size())).every { a[it-1] <= a[it] } }
 
def permutationSort = { a ->
    def n = a.size()
    def fact = factorial(n)
    def permuteA = makePermutation.curry(a)
    def pIndex = (0..<fact).find { print "."; sorted(permuteA(it)) }
    permuteA(pIndex)
}

// Test:
println permutationSort([7,0,12,-45,-1])
println ()
println permutationSort([10, 10.0, 10.00, 1])
println permutationSort([10, 10.00, 10.0, 1])
println permutationSort([10.0, 10, 10.00, 1])
println permutationSort([10.0, 10.00, 10, 1])
println permutationSort([10.00, 10, 10.0, 1])
println permutationSort([10.00, 10.0, 10, 1])

// The examples with distinct integer and decimal values that compare as equal are there to
// demonstrate, but not to prove, that the sort is stable.
