def π = Math.PI
def radians = π/4
def degrees = 45
 
def d2r = { it*π/180 }
def r2d = { it*180/π }
 
println "sin(π/4) = ${Math.sin(radians)}  == sin(45°) = ${Math.sin(d2r(degrees))}"
println "cos(π/4) = ${Math.cos(radians)}  == cos(45°) = ${Math.cos(d2r(degrees))}"
println "tan(π/4) = ${Math.tan(radians)}  == tan(45°) = ${Math.tan(d2r(degrees))}"
println "asin(√2/2) = ${Math.asin(2**(-0.5))} == asin(√2/2)° = ${r2d(Math.asin(2**(-0.5)))}°"
println "acos(√2/2) = ${Math.acos(2**(-0.5))} == acos(√2/2)° = ${r2d(Math.acos(2**(-0.5)))}°"
println "atan(1) = ${Math.atan(1)} == atan(1)° = ${r2d(Math.atan(1))}°"