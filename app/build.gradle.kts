plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        applicationId = AndroidConfig.applicationId
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk
        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName

        testInstrumentationRunner = AndroidConfig.testInstrumentationRunner
    }

    buildTypes {
        getByName(BuildType.Release) {
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName(BuildType.Debug) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
        }

        testOptions {
            unitTests.isReturnDefaultValues = TestOptions.isReturnDefaultValues
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    lint {
        isIgnoreTestSources = true
    }
}

dependencies {
    implementation(DependenciesConfig.CoreKtx)
    implementation(DependenciesConfig.AppCompact)
    implementation(DependenciesConfig.Material)

    testImplementation(DependenciesConfig.Junit)
    androidTestImplementation(DependenciesConfig.ExtJunit)
    androidTestImplementation(DependenciesConfig.EspressoCore)
}

repositories {
    mavenCentral()
    google()
}