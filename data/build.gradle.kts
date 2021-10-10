plugins {
    id(Deps.Plugins.library)
    id(Deps.Plugins.kotlinAndroid)
    id(Deps.Plugins.kotlinxSerialization)
}

android {
    compileSdkVersion(Conf.Sdk.compile)
    buildToolsVersion = Conf.App.buildToolsVersion

    defaultConfig {
        minSdkVersion(Conf.Sdk.min)
        targetSdkVersion(Conf.Sdk.target)
        versionCode = Conf.App.versionCode
        versionName = Conf.App.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(Deps.JetBrains.kotlin)
    implementation(Deps.JetBrains.serialization)

    implementation(Deps.AndroidX.core)
    implementation(Deps.AndroidX.appcompat)
    implementation(Deps.AndroidX.constraint)
    implementation(Deps.Android.material)
    implementation(Deps.AndroidX.pref)

    implementation(Deps.Squareup.retrofit)
    implementation(Deps.Squareup.serializationFactory)

    implementation(Deps.Koin.koinAndroid)
    implementation(Deps.Koin.koinPlugin)

    implementation(Deps.result)

    testImplementation(Deps.junit)
    androidTestImplementation(Deps.AndroidX.junit)
    androidTestImplementation(Deps.AndroidX.espresso)

    implementation(project(Modules.core))
}
