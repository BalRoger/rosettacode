// Solution:
def countingSort = { array ->
    def max = array.max()
    def min = array.min()
    // this list size allows use of Groovy's natural negative indexing
    def count = [0] * (max + 1 + [0, -min].max())
    array.each { count[it] ++ }
    (min..max).findAll{ count[it] }.collect{ [it]*count[it] }.flatten()
}

// Test:
println countingSort([23,76,99,58,97,57,35,89,51,38,95,92,24,46,31,24,14,12,57,78,4])
println countingSort([88,18,31,44,4,0,8,81,14,78,20,76,84,33,73,75,82,5,62,70,12,7,1])
 
println countingSort([15,-3,0,-1,5,4,5,20,-8])
println countingSort([34,6,8,7,4,3,56,7,8,4,3,5,7,8,6,4,4,67,9,0,0,76,467,453,34,435,37,4,34,234,435,3,2,7,4,634,534,-735,5,4,6,78,4])
// slo-o-o-o-ow due to unnecessarily large counting array
println countingSort([10000033,10000006,10000008,10000009,10000013,10000031,10000013,10000032,10000023,10000023,10000011,10000012,10000021])
