package wtf.trackingcommits.fodder

import java.net.URLClassLoader
import java.util.regex.Pattern

data class Jar(val group: String,
               val artifact: String,
               val version: String,
               val name: String)


class JarLister {
    private val onSlash = Pattern.compile("/")
    private val isHash = Pattern.compile("^[0-9a-f]{40}$")

    fun list(): List<Jar> {
        val classLoader = javaClass.classLoader
        return if (classLoader is URLClassLoader) {
            classLoader.urLs
                    .toList()
                    .map { it.toExternalForm() }
                    .map { it.split(onSlash).toList().asReversed() }
                    .filter { isHash.matcher(it[1]).matches() }
                    .map { Jar(name = it[0], version = it[2], artifact = it[3], group = it[4]) }
        } else {
            listOf()
        }
    }
}
