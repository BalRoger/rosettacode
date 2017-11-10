// Solution:
def assertBounds = { List bounds, int nRect ->
    assert (bounds.size() == 2) && (bounds[0] instanceof Double) && (bounds[1] instanceof Double) && (nRect > 0)
}
 
def integral = { List bounds, int nRectangles, Closure f, List pointGuide, Closure integralCalculator->
    double a = bounds[0], b = bounds[1], h = (b - a)/nRectangles
    def xPoints = pointGuide.collect { double it -> a + it*h }
    def fPoints = xPoints.collect { x -> f(x) }
    integralCalculator(h, fPoints)
}
 
def leftRectIntegral = { List bounds, int nRect, Closure f ->
    assertBounds(bounds, nRect)
    integral(bounds, nRect, f, (0..<nRect)) { h, fPoints -> h*fPoints.sum() }
}
 
def rightRectIntegral = { List bounds, int nRect, Closure f ->
    assertBounds(bounds, nRect)
    integral(bounds, nRect, f, (1..nRect)) { h, fPoints -> h*fPoints.sum() }
}
 
def midRectIntegral = { List bounds, int nRect, Closure f ->
    assertBounds(bounds, nRect)
    integral(bounds, nRect, f, ((0.5d)..nRect)) { h, fPoints -> h*fPoints.sum() }
}
 
def trapezoidIntegral = { List bounds, int nRect, Closure f ->
    assertBounds(bounds, nRect)
    integral(bounds, nRect, f, (0..nRect)) { h, fPoints ->
        def fLeft  = fPoints[0..<nRect]
        def fRight = fPoints[1..nRect]
        h/2*(fLeft + fRight).sum()
    }
}
 
def simpsonsIntegral = { List bounds, int nSimpRect, Closure f ->
    assertBounds(bounds, nSimpRect)
    integral(bounds, nSimpRect*2, f, (0..(nSimpRect*2))) { h, fPoints ->
        def fLeft  = fPoints[(0..<nSimpRect*2).step(2)]
        def fMid   = fPoints[(1..<nSimpRect*2).step(2)]
        def fRight = fPoints[(2..nSimpRect*2).step(2)]
        h/3*((fLeft + fRight).sum() + 4*(fMid.sum()))
    }
}


// Test:
// Each "nRect" (number of rectangles) value given below is the minimum value that meets the
// tolerance condition for the given circumstances (function-to-integrate, integral-type and
// integral-bounds).
double ε = 0.0001 // tolerance. allowable "wrongness", ensures accuracy to 1 in 10,000
double π = Math.PI
double e = Math.E
 
double sinIntegralCalculated = -(Math.cos(π) - Math.cos(0d))
assert  (leftRectIntegral([0d, π], 129, Math.&sin) - sinIntegralCalculated).abs() < ε
assert (rightRectIntegral([0d, π], 129, Math.&sin) - sinIntegralCalculated).abs() < ε
assert   (midRectIntegral([0d, π],  91, Math.&sin) - sinIntegralCalculated).abs() < ε
assert (trapezoidIntegral([0d, π], 129, Math.&sin) - sinIntegralCalculated).abs() < ε
assert  (simpsonsIntegral([0d, π],   6, Math.&sin) - sinIntegralCalculated).abs() < ε
 
double cubeIntegralCalculated = 1d/4d *(10d**4 - 0d**4)
assert  ((leftRectIntegral([0d, 10d], 20000) { it**3 } - cubeIntegralCalculated)/cubeIntegralCalculated).abs() < ε
assert ((rightRectIntegral([0d, 10d], 20001) { it**3 } - cubeIntegralCalculated)/cubeIntegralCalculated).abs() < ε
assert   ((midRectIntegral([0d, 10d],    71) { it**3 } - cubeIntegralCalculated)/cubeIntegralCalculated).abs() < ε
assert ((trapezoidIntegral([0d, 10d],   101) { it**3 } - cubeIntegralCalculated)/cubeIntegralCalculated).abs() < ε
// I can name that tune in one note!
assert  (simpsonsIntegral([0d,   10d], 1) { it**3 } == cubeIntegralCalculated)
assert  (simpsonsIntegral([0d,     π], 1) { it**3 } == (1d/4d *(π**4 - 0d**4)))
assert  (simpsonsIntegral([-7.23d, π], 1) { it**3 } == (1d/4d *(π**4 - (-7.23d)**4)))
 
double quarticIntegralCalculated = 1d/5d *(10d**5 - 0d**5)
assert  ((leftRectIntegral([0d, 10d], 25000) { it**4 } - quarticIntegralCalculated)/quarticIntegralCalculated).abs() < ε
assert ((rightRectIntegral([0d, 10d], 25001) { it**4 } - quarticIntegralCalculated)/quarticIntegralCalculated).abs() < ε
assert   ((midRectIntegral([0d, 10d],    92) { it**4 } - quarticIntegralCalculated)/quarticIntegralCalculated).abs() < ε
assert ((trapezoidIntegral([0d, 10d],   130) { it**4 } - quarticIntegralCalculated)/quarticIntegralCalculated).abs() < ε
assert  ((simpsonsIntegral([0d, 10d],     5) { it**4 } - quarticIntegralCalculated)/quarticIntegralCalculated).abs() < ε
 
def cubicPoly = { it**3 + 2*it**2 + 7*it + 12d }
def cubicPolyAntiDeriv = { 1/4*it**4 + 2/3*it**3 + 7/2*it**2 + 12*it }
double cubicPolyIntegralCalculated = (cubicPolyAntiDeriv(10d) - cubicPolyAntiDeriv(0d))
assert  ((leftRectIntegral([0d, 10d], 20000, cubicPoly) - cubicPolyIntegralCalculated)/cubicPolyIntegralCalculated).abs() < ε
assert ((rightRectIntegral([0d, 10d], 20001, cubicPoly) - cubicPolyIntegralCalculated)/cubicPolyIntegralCalculated).abs() < ε
assert   ((midRectIntegral([0d, 10d],    71, cubicPoly) - cubicPolyIntegralCalculated)/cubicPolyIntegralCalculated).abs() < ε
assert ((trapezoidIntegral([0d, 10d],   101, cubicPoly) - cubicPolyIntegralCalculated)/cubicPolyIntegralCalculated).abs() < ε
// I can name that tune in one note!
assert  ((simpsonsIntegral([0d, 10d],     1, cubicPoly) - cubicPolyIntegralCalculated)/cubicPolyIntegralCalculated).abs() < ε**2.75 // 1 in 100 billion
 
double cpIntegralCalc0ToPI = (cubicPolyAntiDeriv(π)      - cubicPolyAntiDeriv(0d))
assert  ((simpsonsIntegral([0d, π], 1, cubicPoly)        - cpIntegralCalc0ToPI)/      cpIntegralCalc0ToPI).abs() < ε**2.75 // 1 in 100 billion
double cpIntegralCalcMinusEToPI = (cubicPolyAntiDeriv(π) - cubicPolyAntiDeriv(-e))
assert  ((simpsonsIntegral([-e, π], 1, cubicPoly)        - cpIntegralCalcMinusEToPI)/ cpIntegralCalcMinusEToPI).abs() < ε**2.5  // 1 in 10 billion


// Requested Demonstrations:
println "f(x) = x**3, where x is [0,1], with 100 approximations. The exact result is 1/4, or 0.25."
println ([" LeftRect":  leftRectIntegral([0d, 1d], 100) { it**3 }])
println (["RightRect": rightRectIntegral([0d, 1d], 100) { it**3 }])
println (["  MidRect":   midRectIntegral([0d, 1d], 100) { it**3 }])
println (["Trapezoid": trapezoidIntegral([0d, 1d], 100) { it**3 }])
println ([" Simpsons":  simpsonsIntegral([0d, 1d], 100) { it**3 }])
println ()
 
println "f(x) = 1/x, where x is [1, 100], with 1,000 approximations. The exact result is the natural log of 100, or about 4.605170."
println ([" LeftRect":  leftRectIntegral([1d, 100d], 1000) { 1/it }])
println (["RightRect": rightRectIntegral([1d, 100d], 1000) { 1/it }])
println (["  MidRect":   midRectIntegral([1d, 100d], 1000) { 1/it }])
println (["Trapezoid": trapezoidIntegral([1d, 100d], 1000) { 1/it }])
println ([" Simpsons":  simpsonsIntegral([1d, 100d], 1000) { 1/it }])
println ()
 
println "f(x) = x, where x is [0,5000], with 5,000,000 approximations. The exact result is 12,500,000."
println ([" LeftRect":  leftRectIntegral([0d, 5000d], 5000000) { it }])
println (["RightRect": rightRectIntegral([0d, 5000d], 5000000) { it }])
println (["  MidRect":   midRectIntegral([0d, 5000d], 5000000) { it }])
println (["Trapezoid": trapezoidIntegral([0d, 5000d], 5000000) { it }])
println ([" Simpsons":  simpsonsIntegral([0d, 5000d], 5000000) { it }])
println ()
 
println "f(x) = x, where x is [0,6000], with 6,000,000 approximations. The exact result is 18,000,000."
println ([" LeftRect":  leftRectIntegral([0d, 6000d], 6000000) { it }])
println (["RightRect": rightRectIntegral([0d, 6000d], 6000000) { it }])
println (["  MidRect":   midRectIntegral([0d, 6000d], 6000000) { it }])
println (["Trapezoid": trapezoidIntegral([0d, 6000d], 6000000) { it }])
println ([" Simpsons":  simpsonsIntegral([0d, 6000d], 6000000) { it }])
println ()


// Output:
//
//f(x) = x**3, where x is [0,1], with 100 approximations. The exact result is 1/4, or 0.25.
//[ LeftRect:0.24502500000000002]
//[RightRect:0.255025]
//[  MidRect:0.24998750000000008]
//[Trapezoid:0.250025]
//[ Simpsons:0.25000000000000006]
//
//f(x) = 1/x, where x is [1, 100], with 1,000 approximations. The exact result is the natural log of 100, or about 4.605170.
//[ LeftRect:4.65499105751468]
//[RightRect:4.55698105751468]
//[  MidRect:4.604762548678376]
//[Trapezoid:4.605986057514673]
//[ Simpsons:4.605170384957142]
//
//f(x) = x, where x is [0,5000], with 5,000,000 approximations. The exact result is 12,500,000.
//[ LeftRect:1.24999975E7]
//[RightRect:1.25000025E7]
//[  MidRect:1.25E7]
//[Trapezoid:1.25E7]
//[ Simpsons:1.25E7]
//
//f(x) = x, where x is [0,6000], with 6,000,000 approximations. The exact result is 18,000,000.
//[ LeftRect:1.7999997000000004E7]
//[RightRect:1.8000003000000004E7]
//[  MidRect:1.7999999999999993E7]
//[Trapezoid:1.7999999999999996E7]
//[ Simpsons:1.7999999999999993E7]
