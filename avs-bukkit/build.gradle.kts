plugins {
    java
    id("io.papermc.paperweight.userdev") version "1.5.5"
}

group = project.group
version = project.version

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://repo.karuslabs.com/repository/lingua-franca-releases/")
}

dependencies {
    paperweight.paperDevBundle("1.19.3-R0.1-SNAPSHOT")
    compileOnly("org.projectlombok:lombok:1.18.22")
    compileOnly("me.clip:placeholderapi:2.9.2")
    implementation("com.github.spotbugs:spotbugs-annotations:4.6.0")
    implementation(project(":avs-core"))
}

tasks {
    assemble {
        dependsOn(reobfJar)
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }

    named<Copy>("processResources") {
        filesMatching("plugin.yml") {
            expand("pluginVersion" to project.version)
        }
    }
}