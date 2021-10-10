object Deps {
    const val junit = "junit:junit:4.13.2"
    const val result = "com.michael-bull.kotlin-result:kotlin-result:1.1.11"

    object Plugins {
        const val application = "com.android.application"
        const val kotlinAndroid = "kotlin-android"
        const val kotlinxSerialization = "kotlinx-serialization"
    }

    object JetBrains {
        const val version = "1.5.31"

        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:$version"
        const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0"
        const val serializationPlugin = "org.jetbrains.kotlin:kotlin-serialization:$version"
    }

    object Android {
        const val androidPlugin = "com.android.tools.build:gradle:4.1.3"
        const val material = "com.google.android.material:material:1.4.0"
    }

    object AndroidX {
        const val core = "androidx.core:core-ktx:1.6.0"
        const val appcompat = "androidx.appcompat:appcompat:1.3.1"
        const val constraint = "androidx.constraintlayout:constraintlayout:2.1.1"
        const val junit = "androidx.test.ext:junit:1.1.3"
        const val espresso = "androidx.test.espresso:espresso-core:3.4.0"

        private const val lifecycleVersion = "2.3.1"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"

        const val paging = "androidx.paging:paging-runtime:3.0.1"

        private const val roomVersion = "2.3.0"
        const val room = "androidx.room:room-runtime:$roomVersion"
        const val roomCoroutines = "androidx.room:room-ktx:$roomVersion"
        const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
    }

    object Koin {
        private const val koinVersion = "2.2.0"

        const val koinPlugin = "org.koin:koin-gradle-plugin:$koinVersion"
        const val koinAndroid =  "org.koin:koin-android:$koinVersion"
    }

    object Squareup {
        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
        const val serializationFactory = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
    }
}
