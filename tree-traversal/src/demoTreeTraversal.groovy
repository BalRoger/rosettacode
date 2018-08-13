// Uses Groovy Node and NodeBuilder classes

def preorder;
preorder = { Node node ->
    ([node] + node.children().collect { preorder(it) }).flatten()
}
 
def postorder;
postorder = { Node node ->
    (node.children().collect { postorder(it) } + [node]).flatten()
}
 
def inorder;
inorder = { Node node ->
    def kids = node.children()
    if (kids.empty) [node]
    else if (kids.size() == 1 &&  kids[0].'@right') [node] + inorder(kids[0])
    else inorder(kids[0]) + [node] + (kids.size()>1 ? inorder(kids[1]) : [])
}
 
def levelorder = { Node node ->
    def nodeList = []
    def level = [node]
    while (!level.empty) {
        nodeList += level
        def nextLevel = level.collect { it.children() }.flatten()
        level = nextLevel
    }
    nodeList
}
 
class BinaryNodeBuilder extends NodeBuilder {
    protected Object postNodeCompletion(Object parent, Object node) {
        assert node.children().size() < 3
        node
    }
}


// Verify that BinaryNodeBuilder will not allow a node to have more than 2 children

try {
    new BinaryNodeBuilder().'1' {
        a {}
        b {}
        c {}
    }
    println 'not limited to binary tree\r\n'
} catch (org.codehaus.groovy.transform.powerassert.PowerAssertionError e) {
    println 'limited to binary tree\r\n'
}


// Test case #1 (from the task definition)
//
//               1
//           /       \
//       2               3
//     /   \           /
//   4       5       6
//  /               / \
// 7               8   9

def tree1 = new BinaryNodeBuilder().
'1' {
    '2' {
        '4' { '7' {} }
        '5' {}
    }
    '3' {
        '6' { '8' {}; '9' {} }
    }
}


// Test case #2 (tests single right child)
//
//               1
//           /       \
//       2               3
//     /   \           /
//   4       5       6
//    \             / \
//     7           8   9

def tree2 = new BinaryNodeBuilder().
'1' {
    '2' {
        '4' { '7'(right:true) {} }
        '5' {}
    }
    '3' {
        '6' { '8' {}; '9' {} }
    }
}


// Run tests:

def test = { tree ->
    println "preorder:    ${preorder(tree).collect{it.name()}}"
    println "preorder:    ${tree.depthFirst().collect{it.name()}}"
 
    println "postorder:   ${postorder(tree).collect{it.name()}}"
 
    println "inorder:     ${inorder(tree).collect{it.name()}}"
 
    println "level-order: ${levelorder(tree).collect{it.name()}}"
    println "level-order: ${tree.breadthFirst().collect{it.name()}}"
 
    println()
}
test(tree1)
test(tree2)

//Output:
//
//limited to binary tree
//
//preorder:    [1, 2, 4, 7, 5, 3, 6, 8, 9]
//preorder:    [1, 2, 4, 7, 5, 3, 6, 8, 9]
//postorder:   [7, 4, 5, 2, 8, 9, 6, 3, 1]
//inorder:     [7, 4, 2, 5, 1, 8, 6, 9, 3]
//level-order: [1, 2, 3, 4, 5, 6, 7, 8, 9]
//level-order: [1, 2, 3, 4, 5, 6, 7, 8, 9]
//
//preorder:    [1, 2, 4, 7, 5, 3, 6, 8, 9]
//preorder:    [1, 2, 4, 7, 5, 3, 6, 8, 9]
//postorder:   [7, 4, 5, 2, 8, 9, 6, 3, 1]
//inorder:     [4, 7, 2, 5, 1, 8, 6, 9, 3]
//level-order: [1, 2, 3, 4, 5, 6, 7, 8, 9]
//level-order: [1, 2, 3, 4, 5, 6, 7, 8, 9]
