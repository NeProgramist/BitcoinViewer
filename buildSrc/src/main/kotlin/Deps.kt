object Deps {
    const val junit = "junit:junit:4.13.2"

    object Plugins {
        const val application = "com.android.application"
        const val kotlinAndroid = "kotlin-android"
    }

    object Kotlin {
        const val version = "1.5.31"

        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
        const val gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    }

    object Android {
        const val gradle = "com.android.tools.build:gradle:4.1.3"
        const val material = "com.google.android.material:material:1.4.0"
    }

    object AndroidX {
        const val core = "androidx.core:core-ktx:1.6.0"
        const val appcompat = "androidx.appcompat:appcompat:1.3.1"
        const val constraint = "androidx.constraintlayout:constraintlayout:2.1.1"
        const val junit = "androidx.test.ext:junit:1.1.3"
        const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
    }
}
