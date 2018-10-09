// curry()
// This method can be applied to any Groovy closure or method reference (demonstrated here with
// closures). The arguments given to the curry() method are applied to the original (invoking)
// method/closure. The "curry()" method returns a closure as it's result. The arguments on the
// "curry()" method are passed, in their specified order, as the first (left-most) arguments of the
// original method/closure. The remaining, as yet unspecified arguments of the original
// method/closure, form the argument list of the resulting closure.

// Example:

def divide = { Number x, Number y ->
  x / y
}

def partsOf120 = divide.curry(120)

println "120: half: ${partsOf120(2)}, third: ${partsOf120(3)}, quarter: ${partsOf120(4)}"


// rcurry()
// This method can be applied to any Groovy closure or method reference. The arguments given to the
// rcurry() method are applied to the original (invoking) method/closure. The "rcurry()" method
// returns a closure as it's result. The arguments on the "rcurry()" method are passed, in their
// specified order, as the last (right-most) arguments of the original method/closure. The remaining,
// as yet unspecified arguments of the original method/closure, form the argument list of the
// resulting closure.

// Example (using the same "divide()" closure as before):

def half = divide.rcurry(2)
def third = divide.rcurry(3)
def quarter = divide.rcurry(4)

println "30: half: ${half(30)}; third: ${third(30)}, quarter: ${quarter(30)}"


// History
// I invite any expert on the history of the Groovy language to correct this if necessary. Groovy is
// a relatively recent language, with alphas and betas first appearing on the scene in 2003 and
// a 1.0 release in 2007. To the best of my understanding currying has been a part of the language
// from the outset.