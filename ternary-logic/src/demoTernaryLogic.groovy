// Solution:

enum Trit {
    TRUE, MAYBE, FALSE
 
    private Trit nand(Trit that) {
        switch ([this,that]) {
            case { FALSE in it }: return TRUE
            case { MAYBE in it }: return MAYBE
            default             : return FALSE
        }
    }
    private Trit nor(Trit that) { this.or(that).not() }
 
    Trit and(Trit that)   { this.nand(that).not() }
    Trit or(Trit that)    { this.not().nand(that.not()) }
    Trit not()            { this.nand(this) }
    Trit imply(Trit that) { this.nand(that.not()) }
    Trit equiv(Trit that) { this.and(that).or(this.nor(that)) }
}


// Test:

printf 'AND\n         '
Trit.values().each { b -> printf ('%6s', b) }
println '\n          ----- ----- -----'
Trit.values().each { a ->
    printf ('%6s | ', a)
    Trit.values().each { b -> printf ('%6s', a.and(b)) }
    println()
}
 
printf '\nOR\n         '
Trit.values().each { b -> printf ('%6s', b) }
println '\n          ----- ----- -----'
Trit.values().each { a ->
    printf ('%6s | ', a)
    Trit.values().each { b -> printf ('%6s', a.or(b)) }
    println()
}
 
println '\nNOT'
Trit.values().each {
    printf ('%6s | %6s\n', it, it.not())
}
 
printf '\nIMPLY\n         '
Trit.values().each { b -> printf ('%6s', b) }
println '\n          ----- ----- -----'
Trit.values().each { a ->
    printf ('%6s | ', a)
    Trit.values().each { b -> printf ('%6s', a.imply(b)) }
    println()
}
 
printf '\nEQUIV\n         '
Trit.values().each { b -> printf ('%6s', b) }
println '\n          ----- ----- -----'
Trit.values().each { a ->
    printf ('%6s | ', a)
    Trit.values().each { b -> printf ('%6s', a.equiv(b)) }
    println()
}