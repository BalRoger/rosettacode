// Solution:

def lineMap = [:]
System.in.eachLine { line, i ->
    lineMap[i] = line
}
lineMap.each { println it }


// Test:

//$ groovy -e 'def lineMap = [:]
//> System.in.eachLine { line, i ->
//>     lineMap[i] = line
//> }
//> lineMap.each { println it }' <<EOF
//> 
//> Whose woods these are I think I know
//> His house is in the village tho'
//> He will not see me stopping here
//> To watch his woods fill up with snow
//> EOF
