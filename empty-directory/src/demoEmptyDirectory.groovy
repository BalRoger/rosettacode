// Solution:

def isDirEmpty = { dirName ->
    def dir = new File(dirName)
    dir.exists() && dir.directory && (dir.list() as List).empty
}


// Test:

def currentDir = new File('.')
def random = new Random()
def subDirName = "dir${random.nextInt(100000)}"
def subDir = new File(subDirName)
subDir.mkdir()
subDir.deleteOnExit()
 
assert ! isDirEmpty('.')
assert isDirEmpty(subDirName)