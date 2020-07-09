plugins {
    id("java-library")
    kotlin("jvm")
}

dependencies {
    api(platform(project(":depconstraints")))

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    // ThreeTenBP for the shared module only. Date and time API for Java.
    //api("org.threeten:threetenbp:${Versions.THREETENBP}:no-tzdb")
    implementation(Libs.CORE_KTX)

    implementation(Libs.KOTLIN_STDLIB)

    implementation(Libs.GSON)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}