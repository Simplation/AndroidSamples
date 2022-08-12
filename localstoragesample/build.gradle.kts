plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        applicationId = "com.simplation.localstorage"
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

    buildFeatures {
        viewBinding = true
    }

    lint {
        isIgnoreTestSources = true
    }
}

dependencies {
    implementation(DependenciesConfig.CoreKtx)
    implementation(DependenciesConfig.AppCompact)
    implementation(DependenciesConfig.ConstraintLayout)
    implementation(DependenciesConfig.Material)
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")

    testImplementation(DependenciesConfig.Junit)
    androidTestImplementation(DependenciesConfig.ExtJunit)
    androidTestImplementation(DependenciesConfig.EspressoCore)
}
