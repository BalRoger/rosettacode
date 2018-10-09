// Solution:

def stripChars = { string, stripChars ->
    def list = string as List
    list.removeAll(stripChars as List)
    list.join()
}


// Test:

println (stripChars('She was a soul stripper. She took my heart!', 'aei'))