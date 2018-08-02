// "Matching" Solution (it's complicated):

import java.util.regex.*

def woodchuck = "How much wood would a woodchuck chuck if a woodchuck could chuck wood?"
def pepper = "Peter Piper picked a peck of pickled peppers"


println "=== Regular-expression String syntax (/string/) ==="
def woodRE = /[Ww]o\w+d/
def piperRE = /[Pp]\w+r/
assert woodRE instanceof String && piperRE instanceof String
assert (/[Ww]o\w+d/ == "[Ww]o\\w+d") && (/[Pp]\w+r/ == "[Pp]\\w+r")
println ([woodRE: woodRE, piperRE: piperRE])
println ()


println "=== Pattern (~) operator ==="
def woodPat = ~/[Ww]o\w+d/
def piperPat = ~piperRE
assert woodPat instanceof Pattern && piperPat instanceof Pattern

def woodList = woodchuck.split().grep(woodPat)
println ([exactTokenMatches: woodList])
println ([exactTokenMatches: pepper.split().grep(piperPat)])
println ()


println "=== Matcher (=~) operator ==="
def wwMatcher = (woodchuck =~ woodRE)
def ppMatcher = (pepper =~ /[Pp]\w+r/)
def wpMatcher = (woodchuck =~ /[Pp]\w+r/)
assert wwMatcher instanceof Matcher && ppMatcher instanceof Matcher
assert wwMatcher.toString() == woodPat.matcher(woodchuck).toString()
assert ppMatcher.toString() == piperPat.matcher(pepper).toString()
assert wpMatcher.toString() == piperPat.matcher(woodchuck).toString()

println ([ substringMatches: wwMatcher.collect { it }])
println ([ substringMatches: ppMatcher.collect { it }])
println ([ substringMatches: wpMatcher.collect { it }])
println ()


println "=== Exact Match (==~) operator ==="
def containsWoodRE = /.*/ + woodRE + /.*/
def containsPiperRE = /.*/ + piperRE + /.*/
def wwMatches = (woodchuck ==~ containsWoodRE)
assert wwMatches instanceof Boolean
def wwNotMatches = ! (woodchuck ==~ woodRE)
def ppMatches = (pepper ==~ containsPiperRE)
def pwNotMatches = ! (pepper ==~ containsWoodRE)
def wpNotMatches = ! (woodchuck ==~ containsPiperRE)
assert wwMatches && wwNotMatches && ppMatches && pwNotMatches && pwNotMatches

println ("'${woodchuck}' ${wwNotMatches ? 'does not' : 'does'} match '${woodRE}' exactly")
println ("'${woodchuck}' ${wwMatches ? 'does' : 'does not'} match '${containsWoodRE}' exactly")

//Output:
//=== Regular-expression String syntax (/string/)===
//[woodRE:[Ww]o\w+d, piperRE:[Pp]\w+r]
//
//=== Pattern (~) operator ===
//[exactTokenMatches:[wood, would]]
//[exactTokenMatches:[Peter, Piper]]
//
//=== Matcher (=~) operator ===
//[substringMatches:[wood, would, wood, wood, wood]]
//[substringMatches:[Peter, Piper, pepper]]
//[substringMatches:[]]
//
//=== Exact Match (==~) operator ===
//'How much wood would a woodchuck chuck if a woodchuck could chuck wood?' does not match '[Ww]o\w+d' exactly
//'How much wood would a woodchuck chuck if a woodchuck could chuck wood?' does match '.*[Ww]o\w+d.*' exactly

// Replacement Solution (String.replaceAll()):

println woodchuck.replaceAll(/c\w+k/, "CHUCK")

//Output:
//How much wood would a woodCHUCK CHUCK if a woodCHUCK could CHUCK wood?

// Reusable Replacement Solution (Matcher.replaceAll()):
def ck = (woodchuck =~ /c\w+k/)
println (ck.replaceAll("CHUCK"))
println (ck.replaceAll("wind"))
println (ck.replaceAll("pile"))
println (ck.replaceAll("craft"))
println (ck.replaceAll("block"))
println (ck.replaceAll("row"))
println (ck.replaceAll("shed"))
println (ck.replaceAll("man"))
println (ck.replaceAll("work"))
println (ck.replaceAll("pickle"))

//Output:
//How much wood would a woodCHUCK CHUCK if a woodCHUCK could CHUCK wood?
//How much wood would a woodwind wind if a woodwind could wind wood?
//How much wood would a woodpile pile if a woodpile could pile wood?
//How much wood would a woodcraft craft if a woodcraft could craft wood?
//How much wood would a woodblock block if a woodblock could block wood?
//How much wood would a woodrow row if a woodrow could row wood?
//How much wood would a woodshed shed if a woodshed could shed wood?
//How much wood would a woodman man if a woodman could man wood?
//How much wood would a woodwork work if a woodwork could work wood?
//How much wood would a woodpickle pickle if a woodpickle could pickle wood?
