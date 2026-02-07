import org.apache.tools.ant.filters.ReplaceTokens
import org.gradle.api.tasks.Sync
import org.gradle.api.tasks.compile.JavaCompile

plugins {
    id("java-library")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

val minestom = "11d7530dfc"

dependencies {
    api("me.lucko:spark-common:1.10.165-SNAPSHOT")

    compileOnly("net.minestom:minestom-snapshots:$minestom")
    implementation("com.google.guava:guava:33.3.1-jre")

    testImplementation("net.minestom:minestom-snapshots:$minestom")
    testImplementation("ch.qos.logback:logback-classic:1.5.12")
}

java {
    withJavadocJar()
    withSourcesJar()
}

// test を disable する
tasks.test {
    enabled = false
}
