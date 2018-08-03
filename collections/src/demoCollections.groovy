// List

def emptyList = []
assert emptyList.isEmpty() : "These are not the items you're looking for"
assert emptyList.size() == 0 : "Empty list has size 0"
assert ! emptyList : "Empty list evaluates as boolean 'false'"
 
def initializedList = [ 1, "b", java.awt.Color.BLUE ]
assert initializedList.size() == 3
assert initializedList : "Non-empty list evaluates as boolean 'true'"
assert initializedList[2] == java.awt.Color.BLUE : "referencing a single element (zero-based indexing)"
assert initializedList[-1] == java.awt.Color.BLUE : "referencing a single element (reverse indexing of last element)"
 
def combinedList = initializedList + [ "more stuff", "even more stuff" ]
assert combinedList.size() == 5
assert combinedList[1..3] == ["b", java.awt.Color.BLUE, "more stuff"] : "referencing a range of elements"
 
combinedList << "even more stuff"
assert combinedList.size() == 6
assert combinedList[-1..-3] == \
        ["even more stuff", "even more stuff", "more stuff"] \
                : "reverse referencing last 3 elements"
println ([combinedList: combinedList])


// Map

def emptyMap = [:]
assert emptyMap.isEmpty() : "These are not the items you're looking for"
assert emptyMap.size() == 0 : "Empty map has size 0"
assert ! emptyMap : "Empty map evaluates as boolean 'false'"
 
def initializedMap = [ count: 1, initial: "B", eyes: java.awt.Color.BLUE ]
assert initializedMap.size() == 3
assert initializedMap : "Non-empty map evaluates as boolean 'true'"
assert initializedMap["eyes"] == java.awt.Color.BLUE : "referencing a single element (array syntax)"
assert initializedMap.eyes == java.awt.Color.BLUE : "referencing a single element (member syntax)"
assert initializedMap.height == null : \
        "references to non-existant keys generally evaluate to null (implementation dependent)"
 
def combinedMap = initializedMap \
        + [hair: java.awt.Color.BLACK, birthdate: Date.parse("yyyy-MM-dd", "1960-05-17") ]
assert combinedMap.size() == 5
 
combinedMap["weight"] = 185        // array syntax
combinedMap.lastName = "Smith"     // member syntax
combinedMap << [firstName: "Joe"]  // entry syntax
assert combinedMap.size() == 8
assert combinedMap.keySet().containsAll(
        ["lastName", "count", "eyes", "hair", "weight", "initial", "firstName", "birthdate"])
println ([combinedMap: combinedMap])


// Set

def emptySet = new HashSet()
assert emptySet.isEmpty() : "These are not the items you're looking for"
assert emptySet.size() == 0 : "Empty set has size 0"
assert ! emptySet : "Empty set evaluates as boolean 'false'"
 
def initializedSet = new HashSet([ 1, "b", java.awt.Color.BLUE ])
assert initializedSet.size() == 3
assert initializedSet : "Non-empty list evaluates as boolean 'true'"
//assert initializedSet[2] == java.awt.Color.BLUE  // SYNTAX ERROR!!! No indexing of set elements!
 
def combinedSet = initializedSet + new HashSet([ "more stuff", "even more stuff" ])
assert combinedSet.size() == 5
 
combinedSet << "even more stuff"
assert combinedSet.size() == 5 : "No duplicate elements allowed!"
 
combinedSet << "even even more stuff"
assert combinedSet.size() == 6 : "New, unique elements are allowed!"
println ([combinedSet: combinedSet])
