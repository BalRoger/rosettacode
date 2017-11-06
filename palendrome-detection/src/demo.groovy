// // Trivial
// Solution:
def isPalindromeT = { String s ->
    s == s?.reverse()
}

// // Non-recursive
// Solution:
def isPalindromeI = { String s ->
    def n = s.size()
    n < 2 || s[0..<n/2] == s[-1..(-n/2)]
}

// // Recursive
// Solution follows the C palindrome_r recursive solution:
def isPalindromeR
isPalindromeR = { String s ->
    def n = s.size()
    n < 2 || (s[0] == s[n-1] && isPalindrome(s[1..<(n-1)]))
}

// Test program:
[Trivial:isPalindromeT, Nonrecursive:isPalindromeI, Recursive:isPalindromeR].each { k,isPalendrome ->
    println "$k Solution:\n"
    
    println isPalindrome("")
    println isPalindrome("a")
    println isPalindrome("abcdefgfedcba")
    println isPalindrome("abcdeffedcba")
    println isPalindrome("abcedfgfedcb")
}