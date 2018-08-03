// Any Groovy class that implements "Object get(String)" and "void set(String, Object)" will have
// the apparent capability to add new properties. However, this capability will only work as expected
// with an appropriate implementation, backed by a Map object or something very much like a Map.

class A {
    final x = { it + 25 }
    private map = new HashMap()
    Object get(String key) { map[key] }
    void set(String key, Object value) { map[key] = value }
}

// Test:
def a = new A()
a.y = 55
a.z = { println (new Date()); Thread.sleep 5000 }
 
println a.x(25)
println a.y
(0..2).each(a.z)
 
println a.q