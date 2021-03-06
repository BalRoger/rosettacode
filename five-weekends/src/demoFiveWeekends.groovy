// Solution:

enum Day {
    Sun, Mon, Tue, Wed, Thu, Fri, Sat
    static Day valueOf(Date d) { Day.valueOf(d.format('EEE')) }
}

def date = Date.&parse.curry('yyyy-M-dd')
def isLongMonth = { firstDay -> (firstDay + 31).format('dd') == '01'}

def fiveWeekends = { years ->
    years.collect { year ->
        (1..12).collect { month ->
            date("${year}-${month}-01")
        }.findAll { firstDay ->
            isLongMonth(firstDay) && Day.valueOf(firstDay) == Day.Fri
        }
    }.flatten()
}


// Test:

def ym = { it.format('yyyy-MM') }
def years = 1900..2100
def fiveWeekendMonths = fiveWeekends(years)
println "Number of five weekend months: ${fiveWeekendMonths.size()}"
fiveWeekendMonths.each { println (ym(it)) }
