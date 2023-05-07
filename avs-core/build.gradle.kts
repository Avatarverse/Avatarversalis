plugins {
    java
    id("io.freefair.lombok") version "8.0.1"
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

group = project.group
version = project.version

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    maven("https://repo.karuslabs.com/repository/lingua-franca-releases/")
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.22")
    implementation("com.github.spotbugs:spotbugs-annotations:4.6.0")
    implementation("net.jodah:expiringmap:0.5.10")
    implementation("org.spongepowered:configurate-yaml:4.1.2")
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.1")
    implementation("com.karuslabs:lingua-franca:1.0.7")
    implementation("org.apache.commons:commons-collections4:4.4")
    implementation("org.reflections:reflections:0.10.2")
}