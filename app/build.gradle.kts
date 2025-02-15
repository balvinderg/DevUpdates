plugins {
    id(Plugins.ANDROID_APPLICATION)
    kotlin(Plugins.KOTLIN_ANDROID)
    kotlin(Plugins.KOTLIN_KAPT)
    id(Plugins.KOTLIN_PARCELIZE)
    id(Plugins.HILT)
    id(Plugins.GOOGLE_SERVICES)
    id(Plugins.CRASHLYTICS)
}

android {
    compileSdk = AndroidVersion.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = AndroidVersion.APPLICATION_ID
        minSdk = AndroidVersion.MIN_SDK_VERSION
        targetSdk = AndroidVersion.TARGET_SDK_VERSION
        versionCode = AndroidVersion.VERSION_CODE
        versionName = AndroidVersion.VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }
    }

    signingConfigs {
        create("release") {
            keyAlias = rootProject.properties["releaseKeyStorePass"].toString()
            storeFile = rootProject.file("app/releaseKeyStore.jks")
            storePassword = rootProject.properties["releaseKeyStorePass"].toString()
            keyPassword = rootProject.properties["releaseKeyStorePass"].toString()
        }
        getByName("debug") {
            keyAlias = rootProject.properties["debugKeyStorePass"].toString()
            storeFile = rootProject.file("debugKeyStore.jks")
            storePassword = rootProject.properties["debugKeyStorePass"].toString()
            keyPassword = rootProject.properties["debugKeyStorePass"].toString()
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
            isDebuggable = false
        }
        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-dev"
        }
    }

    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":network"))
    implementation(project(":source-github"))
    implementation(project(":source-medium"))
    implementation(project(":source-androidweekly"))
    implementation(project(":source-rss"))
    implementation(project(":services"))
//    debugImplementation(project(":devik"))
//    releaseImplementation(project(":devik-noop"))
    implementation(Dependencies.AndroidX.Material)
    implementation(Dependencies.Di.Hilt)
    implementation(Dependencies.AndroidX.AppCompat)
    implementation(Dependencies.AndroidX.ConstraintLayout)
    implementation(Dependencies.AndroidX.Fragment)
    implementation(Dependencies.Ktx.Fragment)
    implementation(Dependencies.AndroidX.SwipeRefreshLayout)

    implementation(platform(Dependencies.Firebase.Bom))
    implementation(Dependencies.Firebase.AnalyticsKtx)
    implementation(Dependencies.Firebase.CrashlyticsKtx)

    implementation(Dependencies.AndroidX.WorkManager)
    implementation(Dependencies.AndroidX.AppStartup)

    implementation(Dependencies.AndroidX.Room)
    implementation(Dependencies.Ktx.Room)
    kapt(Dependencies.Compilers.Room)

    kapt(Dependencies.Compilers.Hilt)

    testImplementation(Dependencies.AndroidTest.Junit)
    androidTestImplementation(Dependencies.AndroidTest.TestExt)
    androidTestImplementation(Dependencies.AndroidTest.TestRunner)
    androidTestImplementation(Dependencies.AndroidTest.EspressoCore)
}
