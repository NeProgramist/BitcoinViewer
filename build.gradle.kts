buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Deps.Kotlin.gradle)
        classpath(Deps.Android.gradle)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}