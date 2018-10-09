// Solution:

class ABCSolver {
    def blocks

    ABCSolver(blocks = []) { this.blocks = blocks }

    boolean canMakeWord(rawWord) {
        if (rawWord == '' || rawWord == null) { return true; }
        def word = rawWord.toUpperCase()
        def blocksLeft = [] + blocks
        word.every { letter -> blocksLeft.remove(blocksLeft.find { block -> block.contains(letter) }) }
    }
}


// Test:

def a = new ABCSolver(["BO", "XK", "DQ", "CP", "NA", "GT", "RE", "TG", "QD", "FS",
                      "JW", "HU", "VI", "AN", "OB", "ER", "FS", "LY", "PC", "ZM"])

['', 'A', 'BARK', 'book', 'treat', 'COMMON', 'SQuAd', 'CONFUSE'].each {
    println "'${it}': ${a.canMakeWord(it)}"
}
