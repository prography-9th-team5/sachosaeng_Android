import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.google.dagger.hilt.android)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.serialization)
}

fun Project.gradleLocalProperties(providers: ProviderFactory, rootDir: File): Properties {
    val properties = Properties()
    val localPropertiesFile = File(rootDir, "local.properties")
    if (localPropertiesFile.exists()) {
        properties.load(localPropertiesFile.inputStream())
    }
    return properties
}

fun Project.getRemoteInfo(propertyKey: String): String {
    val localProperties = gradleLocalProperties(providers, rootDir)
    return localProperties.getProperty(propertyKey, "")
}


android {
    namespace = "com.example.sachosaeng.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "BASE_URL", getRemoteInfo("base.url"))
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
    implementation(libs.retrofit)
    implementation(libs.retrofit.logging.interceptor)
    implementation(libs.retrofit.converter.kotlinx.serialization)
    implementation(libs.kotlinx.serialization.json)

    //coroutine
    implementation(libs.coroutine.android)
    implementation(libs.coroutine)

    //iavax
    implementation(libs.javax.inject)

    // DataStore
    implementation(libs.androidx.datastore.core.android)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.datastore.preferences.core.jvm)

    // Hilt
    ksp(libs.hilt.compiler)
    ksp(libs.androidx.hilt.complier)
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)

    //test
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(project(":core:model"))
    implementation(project(":core:util"))
}