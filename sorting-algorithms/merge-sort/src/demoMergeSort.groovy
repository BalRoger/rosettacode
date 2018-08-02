// This is the standard algorithm, except that in the looping phase of the merge we work backwards
// through the left and right lists to construct the merged list, to take advantage of the Groovy
// List.pop() method. However, this results in a partially merged list in reverse sort order; so we
// then reverse it to put in back into correct order. This could play havoc with the sort stability,
// but we compensate by picking aggressively from the right list (ties go to the right), rather than
// aggressively from the left as is done in the standard algorithm.
def merge = { List left, List right ->
    List mergeList = []
    while (left && right) {
        print "."
        mergeList << ((left[-1] > right[-1]) ? left.pop() : right.pop())
    }
    mergeList = mergeList.reverse()
    mergeList = left + right + mergeList
}
 
def mergeSort;
mergeSort = { List list ->
 
    def n = list.size()
    if (n < 2) return list
 
    def middle = n.intdiv(2)
    def left = [] + list[0..<middle]
    def right = [] + list[middle..<n]
    left = mergeSort(left)
    right = mergeSort(right)
 
    if (left[-1] <= right[0]) return left + right
 
    merge(left, right)
}

// Test:
println (mergeSort([23,76,99,58,97,57,35,89,51,38,95,92,24,46,31,24,14,12,57,78,4]))
println (mergeSort([88,18,31,44,4,0,8,81,14,78,20,76,84,33,73,75,82,5,62,70,12,7,1]))
println ()
println (mergeSort([10, 10.0, 10.00, 1]))
println (mergeSort([10, 10.00, 10.0, 1]))
println (mergeSort([10.0, 10, 10.00, 1]))
println (mergeSort([10.0, 10.00, 10, 1]))
println (mergeSort([10.00, 10, 10.0, 1]))
println (mergeSort([10.00, 10.0, 10, 1]))

// The presence of decimal and integer versions of the same numbers, demonstrates, but of course
// does not prove, that the sort remains stable.
