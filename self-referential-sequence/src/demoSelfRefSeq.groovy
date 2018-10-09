// Solution:

Number.metaClass.getSelfReferentialSequence = {
  def number = delegate as String; def sequence = []

  while (!sequence.contains(number)) {
    sequence << number
    def encoded = new StringBuilder()
    ((number as List).sort().join('').reverse() =~ /(([0-9])\2*)/).each { matcher, text, digit ->
      encoded.append(text.size()).append(digit)
    }
    number = encoded.toString()
  }
  sequence
}

def maxSeqSize = { List values ->
  values.inject([seqSize: 0, seeds: []]) { max, n ->
    if (n % 100000 == 99999) println 'HT'
    else if (n % 10000 == 9999) print '.'
    def seqSize = n.selfReferentialSequence.size()
    switch (seqSize) {
      case max.seqSize: max.seeds << n
      case { it < max.seqSize }: return max
      default: return [seqSize: seqSize, seeds: [n]]
    }
  }
}


// Test:

def max = maxSeqSize(0..<1000000)

println "\nLargest sequence size among seeds < 1,000,000\n"
println "Seeds: ${max.seeds}\n"
println "Size: ${max.seqSize}\n"
println "Sample sequence:"
max.seeds[0].selfReferentialSequence.each { println it }
