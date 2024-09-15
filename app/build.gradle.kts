import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.google.dagger.hilt.android)
    alias(libs.plugins.google.gms)
    alias(libs.plugins.google.ksp)
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinComposeCompiler.get()
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

    implementation(libs.javax.inject)
    implementation(project(":feature:bookmark"))

    // Hilt
    ksp(libs.hilt.compiler)
    ksp(libs.androidx.hilt.complier)
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)

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
    implementation(project(":core:ui"))
    implementation(project(":feature:home"))
    implementation(project(":feature:mypage"))
    implementation(project(":feature:webview"))
    implementation(project(":feature:signup"))
    implementation(project(":feature:splash"))
    implementation(project(":feature:vote"))
}
