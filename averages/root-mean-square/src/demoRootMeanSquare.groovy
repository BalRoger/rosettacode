// Solution:

def quadMean = { list ->
    list == null \
        ? null \
        : list.empty \
            ? 0 \
            : ((list.collect { it*it }.sum()) / list.size()) ** 0.5
}


// Test:

def list = 1..10
def Q = quadMean(list)
println """
list: ${list}
   Q: ${Q}
"""