plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("com.google.protobuf") version "0.9.4"
}

android {
    namespace = "com.example.mybestnews"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.mybestnews"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.24.3"
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                create("java") {
                    option("lite") // usa versão leve (ideal para Android)
                }
            }
        }
    }
}



dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    //hilt
    implementation("com.google.dagger:hilt-android:2.56.2")
    ksp("com.google.dagger:hilt-android-compiler:2.56.2")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.56.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.3.0")
    //datastore
    implementation("androidx.datastore:datastore-core:1.1.7")
    implementation("androidx.datastore:datastore-preferences-core:1.1.7")
    implementation ("androidx.datastore:datastore:1.1.1")
    implementation ("com.google.protobuf:protobuf-javalite:3.24.3")

    val nav_version = "2.9.4"
    val work_version = "2.10.5"

    // Jetpack Compose integration
    implementation("androidx.navigation:navigation-compose:$nav_version")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.3")

    //Room
    implementation("androidx.room:room-runtime:2.8.0")
    ksp("androidx.room:room-compiler:2.8.0")
    implementation("androidx.room:room-ktx:2.8.0")

    //WorkerManager
    // Kotlin + coroutines
    implementation("androidx.work:work-runtime-ktx:$work_version")

    implementation ("com.google.code.gson:gson:2.11.0")

}