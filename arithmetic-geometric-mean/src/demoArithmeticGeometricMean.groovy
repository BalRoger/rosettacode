// Translation of: Java
// Solution:

double agm (double a, double g) {
    double an = a, gn = g
    while ((an-gn).abs() >= 10.0**-14) { (an, gn) = [(an+gn)*0.5, (an*gn)**0.5] }
    an
}


// Test:

println "agm(1, 0.5**0.5) = agm(1, ${0.5**0.5}) = ${agm(1, 0.5**0.5)}"
assert (0.8472130847939792 - agm(1, 0.5**0.5)).abs() <= 10.0**-14
