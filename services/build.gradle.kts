plugins {
    id(Plugins.ANDROID_LIBRARY)
    kotlin(Plugins.KOTLIN_ANDROID)
    kotlin(Plugins.KOTLIN_KAPT)
    id(Plugins.KOTLIN_PARCELIZE)
}

android {
    compileSdk = AndroidVersion.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = AndroidVersion.MIN_SDK_VERSION
        targetSdk = AndroidVersion.TARGET_SDK_VERSION
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":network"))
    implementation(Dependencies.Utils.Jsoup)

    implementation(Dependencies.AndroidX.Room)
    kapt(Dependencies.Compilers.Room)

    implementation(Dependencies.Di.Hilt)
    kapt(Dependencies.Compilers.Hilt)

    implementation(Dependencies.Network.Moshi)
    kapt(Dependencies.Compilers.Moshi)

    testImplementation(Dependencies.AndroidTest.Junit)
    androidTestImplementation(Dependencies.AndroidTest.TestExt)
    androidTestImplementation(Dependencies.AndroidTest.TestRunner)
    androidTestImplementation(Dependencies.AndroidTest.EspressoCore)
}