plugins {
    id("java-library")
    id("kotlin")
}

dependencies {
    implementation(Deps.JetBrains.kotlin)
    implementation(Deps.result)

    implementation(Deps.Koin.koinAndroid)
    implementation(Deps.Koin.koinPlugin)

    implementation(Deps.JetBrains.coroutinesCore)
}
