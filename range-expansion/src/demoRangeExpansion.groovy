// Ad Hoc Solution:
// - translate the task's range syntax into Groovy range syntax
// - wrap with list delimiters
// - evaluate the script expression
// - flatten the nested lists
// - express as a string
// - unwrap the list delimiters
def expandRanges = { compressed ->
    Eval.me('['+compressed.replaceAll(~/(\d)-/, '$1..')+']').flatten().toString()[1..-2]
}


// Test:

def s = '-6,-3--1,3-5,7-11,14,15,17-20'
println (expandRanges(s))
