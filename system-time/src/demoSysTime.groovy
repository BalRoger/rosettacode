def nowMillis = new Date().time
println 'Milliseconds since the start of the UNIX Epoch (Jan 1, 1970) == ' + nowMillis

// OR

def systemMillis = System.currentTimeMillis()
println 'Milliseconds since the start of the UNIX Epoch (Jan 1, 1970) == ' + systemMillis