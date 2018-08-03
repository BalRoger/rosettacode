// Solution

def isNumeric = {
    def formatter = java.text.NumberFormat.instance
    def pos = [0] as java.text.ParsePosition
    formatter.parse(it, pos)
 
    // if parse position index has moved to end of string
    // then the whole string was numeric
    pos.index == it.size()
}

// Test

println isNumeric('1')
println isNumeric('-.555')
println isNumeric('1,000,000')
println isNumeric(' 1 1 1 1 ')
println isNumeric('abcdef')

println ('1'.isNumber())
println ('-.555'.isNumber())
println ('1,000,000'.isNumber())
println (' 1 1 1 1 '.isNumber())
println ('abcdef'.isNumber())