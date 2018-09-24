// Generic Euler Method Solution

// The following is a general solution for using the Euler method to produce a finite discrete
//sequence of points approximating the ODE solution for y as a function of x.


// In the eulerStep closure argument list:
//    xn and yn together are the previous point in the sequence.
//    h is the step distance to the next point's x value.
//    dydx is a closure representing the derivative of y as a function of x, itself expressed
//        (as required by the method) as a function of x and y(x).

def eulerStep = { xn, yn, h, dydx ->
    (yn + h * dydx(xn, yn)) as BigDecimal
}


// The eulerMapping closure produces an entire approximating sequence, expressed as a Map object.
// Here:
//    x0 and y0 together are the first point in the sequence, the ODE initial conditions.
//    h and dydx are again the step distance and the derivative closure.
//    stopCond is a closure representing a "stop condition" that causes the the eulerMapping
//        closure to stop after a finite number of steps; the given default value causes
//        eulerMapping to stop after 100 steps.

def eulerMapping = { x0, y0, h, dydx, stopCond = { xx, yy, hh, xx0 -> abs(xx - xx0) > (hh * 100)  }.rcurry(h, x0) ->
    Map yMap = [:]
    yMap[x0] = y0 as BigDecimal
    def x = x0
    while (!stopCond(x, yMap[x])) {
        yMap[x + h] = eulerStep(x, yMap[x], h, dydx)
        x += h
    }
    yMap
}
assert eulerMapping.maximumNumberOfParameters == 5


// Specific Euler Method Solution for the "Temperature Diffusion" Problem (with Newton's derivative
// formula and constants for environment temperature and object conductivity given)

def dtdsNewton = { s, t, tR, k ->  k * (tR - t) }
assert dtdsNewton.maximumNumberOfParameters == 4

def dtds = dtdsNewton.rcurry(20, 0.07)
assert dtds.maximumNumberOfParameters == 2

def tEulerH = eulerMapping.rcurry(dtds) { s, t -> s >= 100 }
assert tEulerH.maximumNumberOfParameters == 3


// Newton's Analytic Temperature Diffusion Solution (for comparison)

def tNewton = { s, s0, t0, tR, k ->
    tR + (t0 - tR) * Math.exp(k * (s0 - s))
}
assert tNewton.maximumNumberOfParameters == 5

def tAnalytic = tNewton.rcurry(0, 100, 20, 0.07)
assert tAnalytic.maximumNumberOfParameters == 1


// Specific solutions for 3 step sizes (and initial time and temperature)

[10, 5, 2].each { h ->
    def tEuler = tEulerH.rcurry(h)
    assert tEuler.maximumNumberOfParameters == 2
    println """
STEP SIZE == ${h}
  time   analytic   euler   relative
(seconds)  (°C)     (°C)     error
-------- -------- -------- ---------"""
    tEuler(0, 100).each { BigDecimal s, tE ->
        def tA = tAnalytic(s)
        def relError = ((tE - tA)/(tA - 20)).abs()
        printf('%5.0f    %8.4f %8.4f %9.6f\n', s, tA, tE, relError)
    }
}


// Selected output

/*
STEP SIZE == 10
  time   analytic   euler   relative
(seconds)  (°C)     (°C)     error
-------- -------- -------- ---------
    0    100.0000 100.0000  0.000000
   10     59.7268  44.0000  0.395874
   20     39.7278  27.2000  0.635032
   30     29.7965  22.1600  0.779513
   40     24.8648  20.6480  0.866798
   50     22.4158  20.1944  0.919529
   60     21.1996  20.0583  0.951386
   70     20.5957  20.0175  0.970631
   80     20.2958  20.0052  0.982257
   90     20.1469  20.0016  0.989281
  100     20.0730  20.0005  0.993524

STEP SIZE == 5
  time   analytic   euler   relative
(seconds)  (°C)     (°C)     error
-------- -------- -------- ---------
    0    100.0000 100.0000  0.000000
     ... yada, yada, yada ...
  100     20.0730  20.0145  0.801240

STEP SIZE == 2
  time   analytic   euler   relative
(seconds)  (°C)     (°C)     error
-------- -------- -------- ---------
    0    100.0000 100.0000  0.000000
     ... yada, yada, yada ...
  100     20.0730  20.0425  0.417918
*/

// Notice how the relative error in the Euler method sequences increases over time in spite of the
// fact that all three the Euler approximations and the analytic solution are approaching the same
// asymptotic limit of 20°C.
//
// Notice also how smaller step size reduces the relative error in the approximation.
