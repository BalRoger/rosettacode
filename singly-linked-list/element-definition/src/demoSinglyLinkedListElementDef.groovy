// Solution:

class ListNode {
    Object payload
    ListNode next
    String toString() { "${payload} -> ${next}" }
}


// Test:

def n1 = new ListNode(payload:25)
n1.next = new ListNode(payload:88)

println n1