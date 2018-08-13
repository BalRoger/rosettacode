// Declaration
class Point {
    int x
    int y
 
    // Default values make this a 0-, 1-, and 2-argument constructor
    Point(int x = 0, int y = 0) { this.x = x; this.y = y }
    String toString() { "{x:${x}, y:${y}}" } 
}


// Instantiation


// - Direct

// Default Construction with explicit property setting:
def p0 = new Point()
assert 0 == p0.x
assert 0 == p0.y
p0.x = 36
p0.y = -2
assert 36 == p0.x
assert -2 == p0.y
 
// Direct Construction:
def p1 = new Point(36, -2)
assert 36 == p1.x
assert -2 == p1.y
 
def p2 = new Point(36)
assert 36 == p2.x
assert 0 == p2.y


// - List-to-argument Substitution
//   There are several ways that a List can be substituted for constructor arguments via "type
//   coercion" (casting).

// Explicit coersion from list with "as" keyword
def p4 = [36, -2] as Point
assert 36 == p4.x
assert -2 == p4.y
 
// Explicit coersion from list with Java/C-style casting
p4 = (Point) [36, -2]
println p4
assert 36 == p4.x
assert -2 == p4.y
 
// Implicit coercion from list (by type of variable)
Point p6 = [36, -2]
assert 36 == p6.x
assert -2 == p6.y
 
Point p8 = [36]
assert 36 == p8.x
assert 0 == p8.y


// - Map-to-property Substitution
//   There are several ways to construct an object using a map (or a comma-separated list of map
//   entries) that substitutes entries for class properties. The process is properly:
//     (A) instantiation, followed by
//     (B) property mapping.
//   Because the instantiation is not tied to the mapping, it requires the existence of a
//   no-argument constructor.

// Direct map-based construction
def p3 = new Point([x: 36, y: -2])
assert 36 == p3.x
assert -2 == p3.y
 
// Direct map-entry-based construction
p3 = new Point(x: 36, y: -2)
assert 36 == p3.x
assert -2 == p3.y
 
p3 = new Point(x: 36)
assert 36 == p3.x
assert 0 == p3.y
 
p3 = new Point(y: -2)
assert 0 == p3.x
assert -2 == p3.y
 
// Explicit coercion from map with "as" keyword
def p5 = [x: 36, y: -2] as Point
assert 36 == p5.x
assert -2 == p5.y
 
// Implicit coercion from map (by type of variable)
Point p7 = [x: 36, y: -2]
assert 36 == p7.x
assert -2 == p7.y
 
Point p9 = [y:-2]
assert 0 == p9.x
assert -2 == p9.y