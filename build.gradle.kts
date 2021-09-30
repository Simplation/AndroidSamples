// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven {
            setUrl("https://maven.aliyun.com/repository/public/")
        }
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0")
        // classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
        // 和上面的写法作用一样
        // classpath("", DependenciesVersion.Gradle)
        // classpath(kotlin("kotlin-gradle", DependenciesConfig.Kotlin))
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}
