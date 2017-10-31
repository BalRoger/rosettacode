// Solution:

def isoFormat = { date -> date.format("yyyy-MM-dd") }
def longFormat = { date -> date.format("EEEE, MMMM dd, yyyy") }

//////////////////////////////

// Test Program:

def now = new Date()
println isoFormat(now)
println longFormat(now)