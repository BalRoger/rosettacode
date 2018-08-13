// Based, necessarily, on Java solution:

def rng = new java.security.SecureRandom()


// Test:

(0..4).each { println rng.nextInt() }