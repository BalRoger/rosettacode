// Solution (uses ListNode from Singly-Linked List (element)#Groovy):

class ListNode {
    Object payload
    ListNode next
    String toString() { "${payload} -> ${next}" }
}

class NodeList {
    private enum Flag { FRONT }
    private ListNode head
    void insert(value, insertionPoint=Flag.FRONT) {
        if (insertionPoint == Flag.FRONT) {
            head = new ListNode(payload: value, next: head)
        } else {
            def node = head
            while (node.payload != insertionPoint) {
                node = node.next
                if (node == null) {
                    throw new IllegalArgumentException(
                        "Insertion point ${afterValue} not already contained in list")
                }
            }
            node.next = new ListNode(payload:value, next:node.next)
        }
    }
    String toString() { "${head}" }
}


// Test:

def list = new NodeList()
list.insert('B')
list.insert('A')
println list

list.insert('C', 'A')
println list
