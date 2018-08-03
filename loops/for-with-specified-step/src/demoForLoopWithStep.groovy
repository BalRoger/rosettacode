// "for" loop:
for(i in (2..9).step(2)) {
    print "${i} "
}
println "Who do we appreciate?"

// "each() method:
// Though technically not a loop, most Groovy programmers would use the slightly more terse "each()" method on the collection itself, instead of a "for" loop.
(2..9).step(2).each {
    print "${it} "
}
println "Who do we appreciate?"