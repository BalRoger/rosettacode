def recurse = {
    try {
        call(it + 1)
    } catch (StackOverflowError e) {
        return it
    }
}
 
recurse(0)

// 387