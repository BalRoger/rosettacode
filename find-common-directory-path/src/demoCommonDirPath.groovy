// Solution:
def commonPath = { delim, Object[] paths ->
    def pathParts = paths.collect { it.split(delim) }
    pathParts.transpose().inject([match:true, commonParts:[]]) { aggregator, part ->
        aggregator.match = aggregator.match && part.every { it == part [0] }
        if (aggregator.match) { aggregator.commonParts << part[0] }
        aggregator
    }.commonParts.join(delim)
}

// Test:
println commonPath('/',
    '/home/user1/tmp/coverage/test',
    '/home/user1/tmp/covert/operator',
    '/home/user1/tmp/coven/members')
 
println commonPath('/',
    '/home/user1/tmp/coverage/test',
    '/home/user1/tmp/covert/test',
    '/home/user1/tmp/coven/test',
    '/home/user1/tmp/covers/test')
