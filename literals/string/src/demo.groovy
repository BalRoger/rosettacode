// In Groovy, unlike in Java, a String literal is delimited with single quotes (apostrophes(')).
def string = 'Able was I'

// There is a double quote (quotation mark(")) delimited syntax in Groovy, but it represents an
// expression construct called a GString (I know, I know). Inside of a GString, sub-expression
// substitution of the form ${subexpression} may take place. Thus the following results:
def gString = "${string} ere I saw Elba"
 
println gString
 
//Outputs:
//Able was I ere I saw Elba

// UNIX Shell command line users should recognize these forms of syntax as strong ('-delimited) and
// weak ("-delimited) quoting. And like UNIX Shell weak quoting syntax, the evaluated subexpression
// part of the GString syntax loses its special meaning when preceded by a backslash (\):
def gString2 = "1 + 1 = ${1 + 1}"
assert gString2 == '1 + 1 = 2'
 
def gString3 = "1 + 1 = \${1 + 1}"
assert gString3 == '1 + 1 = ${1 + 1}'

// Groovy also supports multi-line String literals and multi-line GString expressions.
def multiLineString = '''
A man
A plan
A canal
'''
 
def multiLineGString = """
${multiLineString.trim()}:
Panama!
"""

println multiLineGString
 
//Outputs:
//
//A man
//A plan
//A canal:
//Panama!
//

// UNIX Shell programmers should recognize these forms of syntax as similar in function to the
// strong and weak forms of Here Document syntax.
// 
// Both String literals and GString expressions support a number of special characters (usually
// non-printable characters) which consist of a single character preceded by a backslash (hence \X),
// or a 4-hexadecimal-digit UNICODE encoding preceded by a backslash and a lowercase "u" (hence \uXXXX).
// 
// One of these special characters is the backslash itself, denoted with in a String or GString
// as \\. This actually interferes with regular expression syntax in which literal backslashes play
// various important regular-expression-specific roles. Thus it can become quite onerous to write
// regular expressions using String or GString quoting syntax, since every regex backslash would
// have to be written as \\.
// 
// However, Groovy has a special GString syntax that uses slash (/) as a GString delimiter rather
// that quote ("). In this special syntax, most backslash usages that would require a double
// backslash in a regular String or GString require only a single backslash (\). This does not
// create a "regular expression object" (there is not such a thing in Groovy); however, it does
// evaluate to form a "regular expression ready" String, as demonstrated in the following:
def regexString = /(\[[Tt]itle\]|\[[Ss]ubject\])${10 * 5}/

assert regexString == '(\\[[Tt]itle\\]|\\[[Ss]ubject\\])50'

// Javascript users (and others) will recognize the roots of this "regex-ready" syntax as a feature
// in their own language.

// Since apostrophe is used to delimit String literals, that delimiter syntax is not available, as
// it is in Java, to denote single character literals (type char or Character). However, single
// character string literals can be converted to character literals by casting. Shown in the
// examples below are casting using the as operator, Java-style parenthetical casting, and forced
// coercion in the intialization of a variable of type char or Character.
assert 'a' instanceof String
assert ('a' as char) instanceof Character
assert ((char)'a') instanceof Character
 
char x = 'a'
assert x instanceof Character
Character y = 'b'
assert y instanceof Character && (x+1 == y)

// As in Java, backslash is also used to mask a string delimiter. Thus the following two assignments
// represent strings containing a single quote and a single apostrophe respectively
def quote = "\""
def apostrophe = '\''

// Of course, if you are not using GString subexpression evaluation, you can just use apostrophe
// delimiters to contain a quote, or quote delimiters to contain an apostrophe.
def quote2 = '"'
def apostrophe2 = "'"
assert quote == quote2
assert apostrophe == apostrophe2