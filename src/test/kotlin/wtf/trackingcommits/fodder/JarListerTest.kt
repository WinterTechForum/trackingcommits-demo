package wtf.trackingcommits.fodder

import org.junit.Test
import java.net.URLClassLoader

class JarListerTest {

    @Test
    fun list() {
        JarLister().list().forEach{println(it)}
    }

}