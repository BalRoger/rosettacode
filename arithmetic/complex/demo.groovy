// Example and counter-example:
def string = 'Scooby-doo-bee-doo'    // assigns string object to a variable reference
def stringRef = string               // assigns another variable reference to the same object
def stringCopy = new String(string)  // copies string value into a new object, and assigns to a third variable reference

// Test Program:
assert string == stringRef           // they have equal values (like Java equals(), not like Java ==)
assert string.is(stringRef)          // they are references to the same objext (like Java ==)
assert string == stringCopy          // they have equal values
assert ! string.is(stringCopy)       // they are references to different objects (like Java !=)