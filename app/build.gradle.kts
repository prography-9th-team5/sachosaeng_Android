import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    kotlin("kapt")
}

fun getApiKey(propertyKey: String): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey, "")
}

android {
    namespace = "com.example.sachosaeng"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.sachosaeng"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        manifestPlaceholders["KAKAO_NATIVE_KEY"] = getApiKey("kakao.key.native")
        manifestPlaceholders["KAKAO_API_KEY"] = getApiKey("kakao.key.api")
        manifestPlaceholders["KAKAO_ADMIN_KEY"] = getApiKey("kakao.key.admin")
        buildConfigField("String", "KAKAO_NATIVE_KEY", getApiKey("kakao.key.native"))
        buildConfigField("String", "GOOGLE_OAUTH_KEY", getApiKey("google.key"))
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //compose
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.1")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2024.05.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    implementation(libs.core.ktx.v1120)

    //orbit
    implementation(libs.orbit.compose)
    implementation(libs.orbit.viewmodel)
    implementation(libs.orbit.core)

    //navigation
    implementation(libs.navigation.compose)
    implementation(libs.hilt.navigation.compose)

    //hilt
    implementation(libs.hilt.android)
    kapt (libs.hilt.compiler)

    //auth
    implementation(libs.kakao.sdk)
    implementation(libs.google.gms)
    implementation(libs.firebase.auth)
    implementation(libs.gms.auth)
    implementation(platform(libs.firebase.bom))

    //test
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.05.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //module
    implementation(project(":feature"))
    implementation(project(":core:ui"))
}
