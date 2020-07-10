import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {

    val kotlin_version by extra("1.3.72")
    repositories {
        google()
        mavenCentral()
        jcenter()
        // Hilt Jetpack integrations
        maven { url = uri("https://androidx.dev/snapshots/builds/6515566/artifacts/repository") }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.ANDROID_GRADLE_PLUGIN}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}")
        classpath("com.google.gms:google-services:${Versions.GOOGLE_SERVICES}")
        classpath("androidx.benchmark:benchmark-gradle-plugin:${Versions.BENCHMARK}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.NAVIGATION}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT}")
    }
}

/*plugins {
    id("com.diffplug.gradle.spotless") version "3.27.1"
}*/

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
        // Hilt Jetpack integrations
        maven { url = uri("https://androidx.dev/snapshots/builds/6515566/artifacts/repository") }
        maven { url = uri("https://jitpack.io") }

        flatDir {
            dirs = setOf(file("libs"))
        }
    }
}

subprojects {

    // TODO: Remove when the Coroutine and Flow APIs leave experimental/internal/preview.
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions.freeCompilerArgs +=
            "-Xuse-experimental=" +
                    "kotlin.Experimental," +
                    "kotlinx.coroutines.ExperimentalCoroutinesApi," +
                    "kotlinx.coroutines.InternalCoroutinesApi," +
                    "kotlinx.coroutines.FlowPreview"
    }
}
