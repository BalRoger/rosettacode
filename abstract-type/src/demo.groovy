// As in Java, methods that are declared but not implemented are called "abstract" methods.
// An interface is a class-level typing construct that can only contain abstract method declarations
// (well, and constants, but pay no attention to those).
interface Interface {
    int method1(double value)
    int method2(String name)
    int add(int a, int b)
}

// An abstract class may implement some of its methods and leave others unimplemented.
// The unimplemented methods and the class itself must be declared "abstract".
abstract class Abstract1 {
    abstract public int methodA(Date value)
    abstract protected int methodB(String name)
    int add(int a, int b) { a + b }
}

// An abstract class may also be used to partially implement an interface. Here class "Abstract2"
// implements the "add" method from the inherited "Interface", but leaves the other two methods,
// "method1" and "method2", unimplemented. Abstract methods that an abstract class inherits from an
// interface or another abstract class do not have to be redeclared.
abstract class Abstract2 implements Interface {
    int add(int a, int b) { a + b }
}

// Interfaces and abstract classes cannot be instantiated directly. There must be a "concrete subclass"
// that contains a complete implementation in order to instantiate an object.
class Concrete1 implements Interface {
    int method1(double value) { value as int }
    int method2(String name) { (! name) ? 0 : name.toList().collect { it as char }.sum() }
    int add(int a, int b) { a + b }
}
 
class Concrete2 extends Abstract1 {
    int methodA(Date value) { value.toCalendar()[Calendar.DAY_OF_YEAR] }
    protected int methodB(String name) { (! name) ? 0 : name.toList().collect { it as char }.sum() }
}
 
class Concrete3 extends Abstract2 {
    int method1(double value) { value as int }
    int method2(String name) { (! name) ? 0 : name.toList().collect { it as char }.sum() }
}

// Notice that there are no extra descriptive keywords on the interface method declarations.
// Interface methods are assumed to be both abstract and public.

// Obligatory test:
def c1 = new Concrete1()
assert c1 instanceof Interface
println (new Concrete1().method2("Superman"))
 
def c2 = new Concrete2()
assert c2 instanceof Abstract1
println (new Concrete2().methodB("Spiderman"))
 
def c3 = new Concrete3()
assert c3 instanceof Interface
assert c3 instanceof Abstract2
println (new Concrete3().method2("Hellboy"))