// Solution:
def bigNumber = 5G ** (4 ** (3 ** 2))

// Test:
def bigString = bigNumber.toString()
 
assert bigString[0..<20] == "62060698786608744707"
assert bigString[-20..-1] == "92256259918212890625"
 
println bigString.size()