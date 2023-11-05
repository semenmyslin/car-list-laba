plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-parcelize")
    id("kotlin-kapt")
}


android {
    namespace = "com.example.car_list_laba"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.car_list_laba"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        debug {
            versionNameSuffix = "-debug"
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

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    val moxy_version = "2.2.2" // версия библиотеки moxy
    kapt("com.github.moxy-community:moxy-compiler:$moxy_version")

    implementation("com.github.moxy-community:moxy:$moxy_version")
    implementation("com.github.moxy-community:moxy-androidx:$moxy_version")
    implementation("com.github.moxy-community:moxy-ktx:$moxy_version")

    // ViewBinding helper
    implementation("com.github.kirich1409:viewbindingpropertydelegate:1.4.7")

    //load image
    implementation("com.github.bumptech.glide:glide:4.16.0")

}
