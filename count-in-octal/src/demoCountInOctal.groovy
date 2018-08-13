// Size-limited solution:

println 'decimal  octal'
for (def i = 0; i <= Integer.MAX_VALUE; i++) {
    printf ('%7d  %#5o\n', i, i)
}


// Unbounded solution:

println 'decimal  octal'
for (def i = 0g; true; i += 1g) {
    printf ('%7d  %#5o\n', i, i)
}