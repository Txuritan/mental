import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import net.minecraftforge.gradle.common.BaseExtension

buildscript {
    extra["kotlin-version"] = "1.1.3-2"
    repositories {
        jcenter()
        mavenCentral()
        maven {
            name = "forge"
            url = uri("http://files.minecraftforge.net/maven")
        }
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${extra["kotlin-version"]}")
        classpath("net.minecraftforge.gradle:ForgeGradle:2.2-SNAPSHOT")
        classpath("com.github.jengelman.gradle.plugins:shadow:2.0.1")
    }
}

apply {
    plugin("java")
    plugin("kotlin")
    plugin("net.minecraftforge.gradle.forge")
    plugin("com.github.johnrengelman.shadow")
}

version = "1.11.2-0.0.1.0-beta"
group = "com.github.txuritan"

configure<BaseExtension> {
    version = "1.11.2-13.20.0.2228"
    mappings = "snapshot_20170301"
}

repositories {
    mavenCentral()
    jcenter()
    maven { url = uri("http://dvs1.progwml6.com/files/maven") }
    maven { url = uri("http://tehnut.info/maven") }
    maven { url = uri("http://mvn.rx14.co.uk/list/repo/") }
    maven { url = uri("http://maven.shadowfacts.net/") }
}

dependencies {
    "compile"("net.shadowfacts:Forgelin:1.5.0")

    "compile"("org.luaj:luaj-jse:3.0.1")
    "shadow"("org.luaj:luaj-jse:3.0.1")
}

val shadowJar : ShadowJar by tasks
tasks {
    "build" {
        dependsOn(shadowJar)
    }
}
