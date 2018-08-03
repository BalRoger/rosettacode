// Groovy, like Java, requires full support for IEEE 32-bit (Float) and 64-bit (Double) formats.
// So the solution function would simply return either the Float or Double constant encoded as IEEE
// infinity.
def biggest = { Double.POSITIVE_INFINITY }

// Test program:
println biggest()
printf ( "0x%xL \n", Double.doubleToLongBits(biggest()) )