/**
 * @作者: Simplation
 * @日期: 2021/8/2 9:29
 * @描述: AndroidConfig
 * @更新:
 */
object AndroidConfig {
    const val compileSdk = 30
    const val minSdk = 21
    const val targetSdk = 30
    const val versionCode = 1
    const val versionName = "1.0"

    const val applicationId = "com.simplation.samples"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

interface BuildType {
    companion object {
        const val Release = "release"
        const val Debug = "debug"
    }

    val isMinifyEnabled: Boolean
}

object BuildTypeDebug : BuildType {
    override val isMinifyEnabled: Boolean
        get() = false
}

object BuildTypeRelease : BuildType {
    override val isMinifyEnabled: Boolean
        get() = false
}

object TestOptions {
    const val isReturnDefaultValues = true
}

