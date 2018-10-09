// Translation of: Java
// (more or less)
// Solution:

class T implements Cloneable {
    String property
    String name() { 'T' }
    T copy() {
        try { super.clone() }
        catch(CloneNotSupportedException e) { null }
    }
    @Override
    boolean equals(that) { this.name() == that?.name() && this.property == that?.property }
}

class S extends T {
    @Override String name() { 'S' }
}


// Test:

T obj1 = new T(property: 'whatever')
S obj2 = new S(property: 'meh')

def objA = obj1.copy()
def objB = obj2.copy()

assert objA.class == T
assert objA == obj1 && ! objA.is(obj1) // same values, not same instance

assert objB.class == S
assert objB == obj2 && ! objB.is(obj2) // same values, not same instance

println "objA:: name: ${objA.name()}, property: ${objA.property}"
println "objB:: name: ${objB.name()}, property: ${objB.property}"
