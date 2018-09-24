// Sample Serializable Classes (borrowed from Java example. Sorta.):

class Entity implements Serializable {
    static final serialVersionUID = 3504465751164822571L
    String name = 'Thingamabob'
    public String toString() { return name }
}
 
class Person extends Entity implements Serializable {
    static final serialVersionUID = -9170445713373959735L
    Person() { name = 'Clement' }
    Person(name) { this.name = name }
}


// Writing objects:

File objectStore = new File('objectStore.ser')
if (objectStore.exists()) { objectStore.delete() }
assert ! objectStore.exists()
def os
try {
    os = objectStore.newObjectOutputStream()
    os << new Person()
    os << 10.5
    os << new Person('Cletus')
    os << new Date()
    os << new Person('Pious')
    os << java.awt.Color.RED
    os << new Person('Linus')
    os << 'just random garbage'
    os << new Person('Lucy')
    os << ['lists', 'are', 'serializable']
    os << new Person('Schroeder')
} catch (e) { throw new Exception(e) } finally { os?.close() }
assert objectStore.exists()


// Reading objects:

def is
try {
    is = objectStore.newObjectInputStream(this.class.classLoader)
    is.eachObject { println it }
} catch (e) { throw new Exception(e) } finally { is?.close() }
 
objectStore.delete()
assert ! objectStore.exists()