import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrainsKotlinAndroid)
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
    namespace = "com.example.sachosaeng.feature"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

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
    //android core
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)

    //test
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    debugImplementation(libs.ui.tooling)

    //compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.constraintlayout)

    //hilt
    implementation(libs.hilt.navigation.compose)
    implementation(libs.hilt.android)

    //auth
    implementation(libs.kakao.sdk)
    implementation(libs.google.gms)
    implementation(libs.firebase.auth)
    implementation(libs.gms.auth)
    implementation(platform(libs.firebase.bom))

    //orbit
    implementation(libs.orbit.compose)
    implementation(libs.orbit.viewmodel)
    implementation(libs.orbit.core)

    //coil
    implementation(libs.coil)

    implementation(project(":core:ui"))
    implementation(project(":core:util"))
    implementation(project(":core:domain"))
}