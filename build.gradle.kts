
plugins {
    kotlin("jvm") version "1.9.23"
}

group = "cn.trollaura"
version = "1.0"
repositories {
    mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }


}

dependencies {
    implementation("com.github.SNWCreations:JKook:0.52.1")
    implementation("com.github.plexpt:chatgpt:5.1.0")
}

kotlin {
    jvmToolchain(8)
}

tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    dependsOn(configurations.runtimeClasspath)
}


