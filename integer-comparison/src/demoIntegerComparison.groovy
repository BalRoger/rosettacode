// via Relational Operators

def comparisonRelational = { a, b ->
    println "a ? b    = ${a.toString().padLeft(7)} ? ${b.toString().padLeft(7)} = a ${a < b ? '<' : a > b ? '>' : a == b ? '==' : '?'} b"
}

// via "Spaceship" (compareTo) Operator

final rels = [ (-1) : '<', 0 : '==', 1 : '>' ].asImmutable()
def comparisonSpaceship = { a, b ->
    println "a ? b    = ${a.toString().padLeft(7)} ? ${b.toString().padLeft(7)} = a ${rels[a <=> b]} b"
}

[Relational:comparisonRelational, Spaceship:comparisonSpaceship].each { name, comparison ->
    println name
    comparison(2000,3)
    comparison(2000,300000)
    comparison(2000,2000)
    println()
}