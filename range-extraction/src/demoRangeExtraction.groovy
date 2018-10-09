// Ad Hoc Solution:

def range = { s, e -> s == e ? "${s}," : s == e - 1 ? "${s},${e}," : "${s}-${e}," }

def compressList = { list ->
    def sb, start, end
    (sb, start, end) = [''<<'', list[0], list[0]]
    for (i in list[1..-1]) {
        (sb, start, end) = i == end + 1 ? [sb, start, i] : [sb << range(start, end), i, i]
    }
    (sb << range(start, end))[0..-2].toString()
}

def compressRanges = { expanded -> compressList(Eval.me('[' + expanded + ']')) }


// Test:

def s = '''
    0,  1,  2,  4,  6,  7,  8, 11, 12, 14,
   15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
   25, 27, 28, 29, 30, 31, 32, 33, 35, 36,
   37, 38, 39
'''
println (compressRanges(s))
