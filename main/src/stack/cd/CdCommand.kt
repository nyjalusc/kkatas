package stack.cd


/**
 * Implement the 'cd' command i.e. given a function cd('a/b','c/../d/e/../f'),
 * where 1st param is current directory and 2nd param is the sequence of operations,
 * find the final directory that the user will be in when the cd command is executed
 */
class CdCommand {

    fun cd(source: String, destination: String): String {
        val stack = ArrayDeque<String>()
        val sourceDirs = source.split("/").filter { it.isNotEmpty() }
        val destinationDirs = destination.split("/").filter { it.isNotEmpty() }

        // If destination doesn't start with / then it is a relative path
        // so we'll need to process the source string to find the current position
        // otherwise we can skip it just process the destination string
        if(!destination.startsWith("/")) {
            for (sourceDir in sourceDirs) {
                if (sourceDir == "..") stack.removeFirstOrNull()
                else stack.addFirst(sourceDir)
            }
        }
        for (destinationDir in destinationDirs) {
            if (destinationDir == "..") stack.removeFirstOrNull()
            else stack.addFirst(destinationDir)
        }

        // Return absolute path,
        val rootPrefix = if (destination.startsWith("/") || source.startsWith("/")) "/" else ""
        return stack.reversed().joinToString("/", prefix = rootPrefix)
    }

}

fun main() {
    val solution = CdCommand()
    println(solution.cd("/logs", "test/nodes/../modules/logs"))
    println(solution.cd("/logs/..", "test/../modules/logs/.."))
    println(solution.cd("logs/pid", "test/modules/logs"))
}