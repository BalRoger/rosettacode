def strings = "Here are some sample strings to be sorted".split()
def sortedStrings = strings.sort { x, y ->
    y.length() <=> x.length() ?: x.compareToIgnoreCase(y)
}
println sortedStrings