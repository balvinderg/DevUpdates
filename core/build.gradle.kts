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
    api(Dependencies.AndroidX.Material)
    api(Dependencies.AndroidX.AppCompat)
    api(Dependencies.AndroidX.ConstraintLayout)
    api(Dependencies.AndroidX.Browser)
    api(Dependencies.Helpers.Shimmer)
    api(Dependencies.JetBrains.Coroutines)
    api(Dependencies.JetBrains.CoroutinesAndroid)
    api(Dependencies.Ktx.Core)
    api(Dependencies.LifeCycle.LifecycleExtensions)
    api(Dependencies.LifeCycle.LifecycleRuntime)
    api(Dependencies.LifeCycle.LifecycleCommonJava)
    api(Dependencies.LifeCycle.LifeCycleOnly)
    api(Dependencies.DevOnly.Timber)

    implementation(Dependencies.Di.Hilt)
    kapt(Dependencies.Compilers.Hilt)

    testImplementation(Dependencies.AndroidTest.Junit)
    androidTestImplementation(Dependencies.AndroidTest.TestRunner)
    androidTestImplementation(Dependencies.AndroidTest.TestExt)
    androidTestImplementation(Dependencies.AndroidTest.EspressoCore)
}
