plugins {
    java
}

group = project.group
version = project.version

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://repo.aikar.co/content/groups/aikar/")
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.22")
    implementation("com.github.spotbugs:spotbugs-annotations:4.6.0")
    implementation("org.spongepowered:configurate-yaml:4.1.2")
    implementation(project(":avs-core"))
}