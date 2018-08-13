// Solution:

class Queue {
    private List buffer
 
    public Queue(List buffer =  new LinkedList()) {
        assert buffer != null
        assert buffer.empty
        this.buffer = buffer
    }
 
    def push (def item) { buffer << item }
    final enqueue = this.&push
 
    def pop() {
        if (this.empty) throw new NoSuchElementException('Empty Queue')
        buffer.remove(0)
    }
    final dequeue = this.&pop
 
    def getEmpty() { buffer.empty }
 
    String toString() { "Queue:${buffer}" }
}


// Test:

def q = new Queue()
assert q.empty
 
['Crosby', 'Stills'].each { q.push(it) }
assert !q.empty
['Nash', 'Young'].each { q.enqueue(it) }
println q
assert !q.empty
assert q.pop() == 'Crosby'
println q
assert !q.empty
assert q.dequeue() == 'Stills'
println q
assert !q.empty
assert q.pop() == 'Nash'
println q
assert !q.empty
q.push('Crazy Horse')
println q
assert q.dequeue() == 'Young'
println q
assert !q.empty
assert q.pop() == 'Crazy Horse'
println q
assert q.empty
try { q.pop() } catch (NoSuchElementException e) { println e }
try { q.dequeue() } catch (NoSuchElementException e) { println e }