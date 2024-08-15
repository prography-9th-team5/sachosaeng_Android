import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    kotlin("kapt")
}

fun Project.gradleLocalProperties(providers: ProviderFactory, rootDir: File): Properties {
    val properties = Properties()
    val localPropertiesFile = File(rootDir, "local.properties")
    if (localPropertiesFile.exists()) {
        properties.load(localPropertiesFile.inputStream())
    }
    return properties
}

fun Project.getApiKey(propertyKey: String): String {
    val localProperties = gradleLocalProperties(providers, rootDir)
    return localProperties.getProperty(propertyKey, "")
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
    implementation(libs.core.ktx.v1120)

    //compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)


    //orbit
    implementation(libs.orbit.compose)
    implementation(libs.orbit.viewmodel)
    implementation(libs.orbit.core)

    //navigation
    implementation(libs.navigation.compose)
    implementation(libs.hilt.navigation.compose)

    //hilt
    implementation(libs.hilt.android)
    implementation(project(":feature:home"))
    implementation(project(":feature:mypage"))
    implementation(project(":feature:webview"))
    implementation(project(":feature:signup"))
    implementation(project(":feature:splash"))
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
    debugImplementation(libs.ui.tooling)

    //module
    implementation(project(":feature"))
    implementation(project(":core:ui"))
}
