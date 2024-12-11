import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrainsKotlinAndroid)
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
    namespace = "com.sachosaeng.app.core.util"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "APP_URL", getApiKey("app.url"))
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
}

dependencies {
    implementation(libs.core.ktx.v1120)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //iavax
    implementation(libs.javax.inject)

    // Hilt
    ksp(libs.hilt.compiler)
    ksp(libs.androidx.hilt.complier)
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)

    //ga
    implementation(libs.firebase.messaging)
    implementation(libs.analytics)
    implementation(platform(libs.firebase.bom))
}