plugins {
    id(Deps.Plugins.application)
    id(Deps.Plugins.kotlinAndroid)
}

android {
    compileSdkVersion(Conf.Sdk.compile)
    buildToolsVersion = Conf.App.buildToolsVersion

    defaultConfig {
        applicationId = Conf.App.id
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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(Deps.JetBrains.kotlin)
    implementation(Deps.Android.material)
    implementation(Deps.AndroidX.core)
    implementation(Deps.AndroidX.appcompat)
    implementation(Deps.AndroidX.fragmentx)
    implementation(Deps.AndroidX.constraint)
    implementation(Deps.AndroidX.paging)

    implementation(Deps.AndroidX.viewModel)
    implementation(Deps.AndroidX.liveData)

    implementation(Deps.Koin.koinAndroid)
    implementation(Deps.Koin.koinPlugin)
    implementation(Deps.Koin.koinViewModel)

    implementation(Deps.result)

    testImplementation(Deps.junit)
    androidTestImplementation(Deps.AndroidX.junit)
    androidTestImplementation(Deps.AndroidX.espresso)

    implementation(project(Modules.data))
    implementation(project(Modules.core))
}