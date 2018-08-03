// Solution:
def alignColumns = { align, rawText ->
    def lines = rawText.tokenize('\n')
    def words = lines.collect { it.tokenize(/\$/) }
    def maxLineWords = words.collect {it.size()}.max()
    words = words.collect { line -> line + [''] * (maxLineWords - line.size()) }
    def columnWidths = words.transpose().collect{ column -> column.collect { it.size() }.max() }
 
    def justify = [   Right  : { width, string -> string.padLeft(width) },
                            Left   : { width, string -> string.padRight(width) },
                            Center : { width, string -> string.center(width) }      ]
    def padAll = { pad, colWidths, lineWords -> [colWidths, lineWords].transpose().collect { pad(it) + ' ' } }
 
    words.each { padAll(justify[align], columnWidths, it).each { print it }; println() }
}

// Test Program:
def rawTextInput = '''Given$a$text$file$of$many$lines,$where$fields$within$a$line$
are$delineated$by$a$single$'dollar'$character,$write$a$program
that$aligns$each$column$of$fields$by$ensuring$that$words$in$each$
column$are$separated$by$at$least$one$space.
Further,$allow$for$each$word$in$a$column$to$be$either$left$
justified,$right$justified,$or$center$justified$within$its$column.'''
 
['Left', 'Center', 'Right'].each { align ->
    println "${align} Justified:"
    alignColumns(align, rawTextInput)
    println()
}