// Absolute Value
// In addition to the java.lang.Math.abs() method, each numeric type has an abs() method, which can be invoked directly on the number:

println ((-22).abs()) // 22

// Power
// In addition to the java.lang.Math.pow() method, each numeric type works with the power operator (**), which can be invoked as an in-fix operator between two numbers:

println 22**3.5  // 49943.547010599876

//Power results are not defined for all possible pairs of operands. Any power operation that does not have a result returns a 64-bit IEEE NaN (Not a Number) value.

println ((-22)**3.5)  // NaN