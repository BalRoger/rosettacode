// Solution

def checkTheAnswer = {
    assert it == 42 : "This: " + it + " is not the answer!"
}

/////////////////////////////////////

// Demo

println "before 42..."
checkTheAnswer(42)
println "before 'Hello Universe'..."
checkTheAnswer("Hello Universe")