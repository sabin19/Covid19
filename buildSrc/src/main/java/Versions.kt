object Versions {
    val versionName = "1.0.0" // X.Y.Z; X = Major, Y = minor, Z = Patch level
    private val versionCodeBase = 1 // XYYZZM; M = Module (tv, mobile)
    val versionCodeMobile = versionCodeBase + 1

    const val COMPILE_SDK = 29
    const val TARGET_SDK = 29
    const val MIN_SDK = 21

    const val ANDROID_GRADLE_PLUGIN = "4.0.0"
    const val BENCHMARK = "1.0.0"
    const val GOOGLE_SERVICES = "4.3.3"
    const val KOTLIN = "1.3.72"
    const val NAVIGATION = "2.2.2"
    const val HILT = "2.28-alpha"

}
