plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.google.ksp)
}

android {
    namespace = "com.sachosaeng.app.feature.mypage"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        kotlinCompilerExtensionVersion = libs.versions.kotlinComposeCompiler.get()
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
    implementation(project(":feature:webview"))
    implementation(project(":core:usecase"))
    implementation(libs.androidx.paging.common.android)

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

    //paging
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.paging.common)

    // Hilt
    ksp(libs.hilt.compiler)
    ksp(libs.androidx.hilt.complier)
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)

    //orbit
    implementation(libs.orbit.compose)
    implementation(libs.orbit.viewmodel)
    implementation(libs.orbit.core)

    //coil
    implementation(libs.coil)

    //ga
    implementation(libs.analytics)
    implementation(platform(libs.firebase.bom))

    implementation(project(":core:ui"))
    implementation(project(":core:util"))
    implementation(project(":core:model"))
}
