import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    // https://kotlinlang.org/docs/multiplatform-hierarchy.html#creating-additional-source-sets
    applyDefaultHierarchyTemplate()

    androidTarget()

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    @OptIn(ExperimentalWasmDsl::class)
    listOf(
        js(),
        wasmJs(),
    ).forEach {
        it.apply {
            moduleName = "composeApp"
            browser {
                commonWebpackConfig {
                    outputFileName = "composeApp.js"
                }
            }
            binaries.executable()
        }
    }

    sourceSets {
        val desktopMain by getting
        val wasmJsMain by getting

        commonMain.dependencies {
            // Compose
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)

            // Shared
            implementation(projects.samples.sampleCore.shared)

            // FileKit
            implementation(projects.filekitCoil)

            // ViewModel Compose
            implementation(libs.androidx.lifecycle.viewmodel.compose)

            // Coil3
            implementation(libs.coil.compose)
        }

        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
        }

        desktopMain.dependencies {
            // Compose
            implementation(compose.desktop.currentOs)

            // Coroutines
            implementation(libs.kotlinx.coroutines.swing)
        }

        val nonWebMain by creating { dependsOn(commonMain.get()) }
        androidMain.get().dependsOn(nonWebMain)
        desktopMain.dependsOn(nonWebMain)
        nativeMain.get().dependsOn(nonWebMain)

        val webMain by creating { dependsOn(commonMain.get()) }
        jsMain.get().dependsOn(webMain)
        wasmJsMain.dependsOn(webMain)
    }
}

android {
    namespace = "io.github.vinceglb.sample.core.compose"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "io.github.vinceglb.sample.core.compose"
        minSdk = libs.versions.android.minSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

compose.desktop {
    application {
        mainClass = "io.github.vinceglb.sample.core.compose.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "io.github.vinceglb.sample.core.compose"
            packageVersion = "1.0.0"
        }
    }
}
