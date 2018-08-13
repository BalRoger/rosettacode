// Solution #1: Nested GStrings
// Brute force solution using nested GStrings. It solves both the basic and extra credit tasks.

def formatCell = { cell ->
    "<td>${cell.replaceAll('&','&amp;').replaceAll('<','&lt;')}</td>"
}
 
def formatRow = { row ->
    """<tr>${row.split(',').collect { cell -> formatCell(cell) }.join('')}</tr>
"""
}
 
def formatTable = { csv, header=false ->
    def rows = csv.split('\n').collect { row -> formatRow(row) }
    header \
        ? """
<table>
<thead>
${rows[0]}</thead>
<tbody>
${rows[1..-1].join('')}</tbody>
</table>
""" \
        : """
<table>
${rows.join('')}</table>
"""
}
 
def formatPage = { title, csv, header=false ->
"""<html>
<head>
<title>${title}</title>
<style type="text/css">
td {background-color:#ddddff; }
thead td {background-color:#ddffdd; text-align:center; }
</style>
</head>
<body>${formatTable(csv, header)}</body>
</html>"""
}

// Test:

def csv = '''Character,Speech
The multitude,The messiah! Show us the messiah!
Brians mother,<angry>Now you listen here! He's not the messiah; he's a very naughty boy! Now go away!</angry>
The multitude,Who are you?
Brians mother,I'm his mother; that's who!
The multitude,Behold his mother! Behold his mother!'''
 
println 'Basic:'
println '-----------------------------------------'
println (formatPage('Basic', csv))
println '-----------------------------------------'
println()
println()
println 'Extra Credit:'
println '-----------------------------------------'
println (formatPage('Extra Credit', csv, true))
println '-----------------------------------------'

//Basic output:
//<html>
//<head>
//<title>Basic</title>
//<style type="text/css">
//td {background-color:#ddddff; }
//thead td {background-color:#ddffdd; text-align:center; }
//</style>
//</head>
//<body>
//<table>
//<tr><td>Character</td><td>Speech</td></tr>
//<tr><td>The multitude</td><td>The messiah! Show us the messiah!</td></tr>
//<tr><td>Brians mother</td><td>&lt;angry>Now you listen here! He's not the messiah; he's a very naughty boy! Now go away!&lt;/angry></td></tr>
//<tr><td>The multitude</td><td>Who are you?</td></tr>
//<tr><td>Brians mother</td><td>I'm his mother; that's who!</td></tr>
//<tr><td>The multitude</td><td>Behold his mother! Behold his mother!</td></tr>
//</table>
//</body>
//</html>
//Groovy-csv-to-html-basic.jpg 
//Appearance as rendered in Google Chrome.

//Extra Credit output:
//<html>
//<head>
//<title>Extra Credit</title>
//<style type="text/css">
//td {background-color:#ddddff; }
//thead td {background-color:#ddffdd; text-align:center; }
//</style>
//</head>
//<body>
//<table>
//<thead>
//<tr><td>Character</td><td>Speech</td></tr>
//</thead>
//<tbody>
//<tr><td>The multitude</td><td>The messiah! Show us the messiah!</td></tr>
//<tr><td>Brians mother</td><td>&lt;angry>Now you listen here! He's not the messiah; he's a very naughty boy! Now go away!&lt;/angry></td></tr>
//<tr><td>The multitude</td><td>Who are you?</td></tr>
//<tr><td>Brians mother</td><td>I'm his mother; that's who!</td></tr>
//<tr><td>The multitude</td><td>Behold his mother! Behold his mother!</td></tr>
//</tbody>
//</table>
//</body>
//</html>
//Groovy-csv-to-html-extra.jpg 
//Appearance as rendered in Google Chrome.

// Solution #2: MarkupBuilder
// A much cleaner solution using the Groovy XML MarkupBuilder class. It solves both the basic and
// extra credit tasks.

import groovy.xml.MarkupBuilder
 
def formatRow = { doc, row ->
    doc.tr { row.each { cell -> td { mkp.yield(cell) } } }
}
 
def formatPage = { titleString, csv, header=false ->
    def writer = new StringWriter()
    def doc = new MarkupBuilder(writer)
    def rows = csv.split('\n').collect { row -> row.split(',') }
    doc.html {
        head {
            title (titleString)
            style (type:"text/css") { 
                mkp.yield('''
                    td {background-color:#ddddff; }
                    thead td {background-color:#ddffdd; text-align:center; }
                ''')
            }
        }
        body {
            table {
                header && thead { formatRow(doc, rows[0]) }
                header && tbody { rows[1..-1].each { formatRow(doc, it) } }
                header || rows.each { formatRow(doc, it) }
            }
        }
    }
    writer.toString()
}
// Test:
// The interface is the same for both solutions, so we just reuse the same test as before.

//Basic output:
//<html>
//  <head>
//    <title>Basic</title>
//    <style type='text/css'>
//                    td {background-color:#ddddff; }
//                    thead td {background-color:#ddffdd; text-align:center; }
//                </style>
//  </head>
//  <body>
//    <table>
//      <tr>
//        <td>Character</td>
//        <td>Speech</td>
//      </tr>
//      <tr>
//        <td>The multitude</td>
//        <td>The messiah! Show us the messiah!</td>
//      </tr>
//      <tr>
//        <td>Brians mother</td>
//        <td>&lt;angry&gt;Now you listen here! He's not the messiah; he's a very naughty boy! Now go away!&lt;/angry&gt;</td>
//      </tr>
//      <tr>
//        <td>The multitude</td>
//        <td>Who are you?</td>
//      </tr>
//      <tr>
//        <td>Brians mother</td>
//        <td>I'm his mother; that's who!</td>
//      </tr>
//      <tr>
//        <td>The multitude</td>
//        <td>Behold his mother! Behold his mother!</td>
//      </tr>
//    </table>
//  </body>
//</html>
//The HTML for this solution looks superficially different than that from the GString solution, but
//the appearance as rendered in Google Chrome is identical.

//Extra Credit output:
//
//<html>
//  <head>
//    <title>Extra Credit</title>
//    <style type='text/css'>
//                    td {background-color:#ddddff; }
//                    thead td {background-color:#ddffdd; text-align:center; }
//                </style>
//  </head>
//  <body>
//    <table>
//      <thead>
//        <tr>
//          <td>Character</td>
//          <td>Speech</td>
//        </tr>
//      </thead>
//      <tbody>
//        <tr>
//          <td>The multitude</td>
//          <td>The messiah! Show us the messiah!</td>
//        </tr>
//        <tr>
//          <td>Brians mother</td>
//          <td>&lt;angry&gt;Now you listen here! He's not the messiah; he's a very naughty boy! Now go away!&lt;/angry&gt;</td>
//        </tr>
//        <tr>
//          <td>The multitude</td>
//          <td>Who are you?</td>
//        </tr>
//        <tr>
//          <td>Brians mother</td>
//          <td>I'm his mother; that's who!</td>
//        </tr>
//        <tr>
//          <td>The multitude</td>
//          <td>Behold his mother! Behold his mother!</td>
//        </tr>
//      </tbody>
//    </table>
//  </body>
//</html>
//The HTML for this solution looks superficially different than that from the GString solution, but
//the appearance as rendered in Google Chrome is identical.
