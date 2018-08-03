// Groovy does not have a bottom-checking loop construct!
// So use an "infinite" while loop with a conditional break as the last statement
def i = 0
while (true) {
    i++
    println i
    if ( i % 6 == 0) break
}