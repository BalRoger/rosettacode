// Groovy-provided

println ([1,2,3,4,5].sum())

// General purpose

println([1,2,3,4,5].inject(0) { sum, val -> sum + val })
println([1,2,3,4,5].inject(1) { prod, val -> prod * val })

// Single result

println ([1,2,3,4,5].inject([sum: 0, product: 1]) { result, value ->
    [sum: result.sum + value, product: result.product * value]})