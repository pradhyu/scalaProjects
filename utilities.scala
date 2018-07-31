 def getListOfSubDirectories(directoryName: String): Array[String] = {
    (new File(directoryName))
        .listFiles
        .filter(_.isDirectory)
        .map(_.getName)
}

  def getListOfFiles(dir: String):List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
        d.listFiles.filter(_.isFile).toList
    } else {
        List[File]()
    }
}

def isLispFile (fileName: String): Boolean = {
fileName.contains(".lisp")
}

def getRecursiveMapOfDirectory (dir: String, filter: String => Boolean = isLispFile) = {
getListOfSubDirectories(dir).map(folderName => Map(folderName -> getListOfFiles(s"$dir/${folderName}")))
}

println("testing")
