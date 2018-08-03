// base collection
def beatles = ["John", "Paul", "George", "Ringo"]

// "for" loop:
for(name in beatles) {
    println name
}

// "each()" method:
// Though technically not a loop, most Groovy programmers would use the somewhat more terse "each()" method on the list itself in preference to the "for" loop construct.
beatles.each {
    println it
}