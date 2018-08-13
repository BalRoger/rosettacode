// Point class:

class Point {
    final Number x, y
    Point(Number x = 0, Number y = 0) { this.x = x; this.y = y }
    Number distance(Point that) { ((this.x - that.x)**2 + (this.y - that.y)**2)**0.5 }
    String toString() { "{x:${x}, y:${y}}" } 
}


// Brute force solution. Incorporates X-only and Y-only pre-checks in two places to cut down on
// the square root calculations:

def bruteClosest(Collection pointCol) {
    assert pointCol
    List l = pointCol
    int n = l.size()
    assert n > 1
    if (n == 2) return [distance:l[0].distance(l[1]), points:[l[0],l[1]]]
    def answer = [distance: Double.POSITIVE_INFINITY]
    (0..<(n-1)).each { i ->
        ((i+1)..<n).findAll { j ->
            (l[i].x - l[j].x).abs() < answer.distance &&
            (l[i].y - l[j].y).abs() < answer.distance 
        }.each { j ->
            if ((l[i].x - l[j].x).abs() < answer.distance &&
                (l[i].y - l[j].y).abs() < answer.distance) {
                def dist = l[i].distance(l[j])
                if (dist < answer.distance) {
                    answer = [distance:dist, points:[l[i],l[j]]]
                }
            }
        }
    }
    answer
}


// Elegant (divide-and-conquer reduction) solution. Incorporates X-only and Y-only pre-checks in
// two places (four if you count the inclusion of the brute force solution) to cut down on the
// square root calculations:

def elegantClosest(Collection pointCol) {
    assert pointCol
    List xList = (pointCol as List).sort { it.x }
    List yList = xList.clone().sort { it.y }
    reductionClosest(xList, xList)
}
 
def reductionClosest(List xPoints, List yPoints) {
//    assert xPoints && yPoints
//    assert (xPoints as Set) == (yPoints as Set)
    int n = xPoints.size()
    if (n < 10) return bruteClosest(xPoints)
 
    int nMid = Math.ceil(n/2)
    List xLeft = xPoints[0..<nMid]
    List xRight = xPoints[nMid..<n]
    Number xMid = xLeft[-1].x
    List yLeft = yPoints.findAll { it.x <= xMid }
    List yRight = yPoints.findAll { it.x > xMid }
    if (xRight[0].x == xMid) {
        yLeft = xLeft.collect{ it }.sort { it.y }
        yRight = xRight.collect{ it }.sort { it.y }
    }
 
    Map aLeft = reductionClosest(xLeft, yLeft)
    Map aRight = reductionClosest(xRight, yRight)
    Map aMin = aRight.distance < aLeft.distance ? aRight : aLeft
    List yMid = yPoints.findAll { (xMid - it.x).abs() < aMin.distance }
    int nyMid = yMid.size()
    if (nyMid < 2) return aMin
 
    Map answer = aMin
    (0..<(nyMid-1)).each { i ->
        ((i+1)..<nyMid).findAll { j ->
            (yMid[j].x - yMid[i].x).abs() < aMin.distance &&
            (yMid[j].y - yMid[i].y).abs() < aMin.distance &&
            yMid[j].distance(yMid[i]) < aMin.distance
        }.each { k ->
            if ((yMid[k].x - yMid[i].x).abs() < answer.distance && (yMid[k].y - yMid[i].y).abs() < answer.distance) {
                def ikDist = yMid[i].distance(yMid[k])
                if ( ikDist < answer.distance) {
                    answer = [distance:ikDist, points:[yMid[i],yMid[k]]]
                }
            }
        }
    }
    answer
}


// Benchmark/Test:

def random = new Random()
 
(1..4).each {
def point10 = (0..<(10**it)).collect { new Point(random.nextInt(1000001) - 500000,random.nextInt(1000001) - 500000) }
 
def startE = System.currentTimeMillis()
def closestE = elegantClosest(point10)
def elapsedE = System.currentTimeMillis() - startE
println """
${10**it} POINTS
-----------------------------------------
Elegant reduction:
elapsed: ${elapsedE/1000} s
closest: ${closestE}
"""
 
 
def startB = System.currentTimeMillis()
def closestB = bruteClosest(point10)
def elapsedB = System.currentTimeMillis() - startB
println """Brute force:
elapsed: ${elapsedB/1000} s
closest: ${closestB}
 
Speedup ratio (B/E): ${elapsedB/elapsedE}
=========================================
"""
}