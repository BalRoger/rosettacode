// Translation of: Java

// CPU Timing

import java.lang.management.ManagementFactory
import java.lang.management.ThreadMXBean
 
def threadMX = ManagementFactory.threadMXBean
assert threadMX.currentThreadCpuTimeSupported
threadMX.threadCpuTimeEnabled = true
 
def clockCpuTime = { Closure c ->
    def start = threadMX.currentThreadCpuTime
    c.call()
    (threadMX.currentThreadCpuTime - start)/1000000
}


// Wall Clock Timing
def clockRealTime = { Closure c ->
    def start = System.currentTimeMillis()
    c.call()
    System.currentTimeMillis() - start
}


// Test:

def countTo = { Long n ->
    long i = 0; while(i < n) { i += 1L }
}
 
["CPU time":clockCpuTime, "wall clock time":clockRealTime].each { measurementType, timer ->
    println '\n'
    [100000000L, 1000000000L].each { testSize ->
        def measuredTime = timer(countTo.curry(testSize))
        println "Counting to ${testSize} takes ${measuredTime}ms of ${measurementType}"
    }
}