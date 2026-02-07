import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.gradle.api.file.DuplicatesStrategy
import org.gradle.jvm.tasks.Jar
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.api.tasks.testing.Test

allprojects {
    group = "dev.lu15"
    version = "1.10-SNAPSHOT"
}

subprojects {

    apply(plugin = "java")
    apply(plugin = "idea")
    apply(plugin = "java-library")

    extra.apply {
        set("baseVersion", "1.10.165")
        set("pluginVersion", "${extra["baseVersion"]}-SNAPSHOT")
        set("pluginDescription", "spark is a performance profiling plugin/mod for Minecraft clients, servers and proxies.")
    }

    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.release.set(25)
    }

    tasks.withType<Test>().configureEach {
        testLogging {
            events = setOf(
                TestLogEvent.PASSED,
                TestLogEvent.FAILED,
                TestLogEvent.SKIPPED
            )
            exceptionFormat = TestExceptionFormat.FULL
            showExceptions = true
            showCauses = true
            showStackTraces = true
        }
    }

    tasks.named<ProcessResources>("processResources") {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }

    repositories {
        mavenCentral()
        maven("https://repo.lucko.me/")
        maven("https://jitpack.io")
    }
}
