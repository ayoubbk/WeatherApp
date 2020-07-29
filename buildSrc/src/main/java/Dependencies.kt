
object App {
    val id = "com.bks.weatherapp"
}

object Modules {
    val weather = ":weather"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0"
}

object DefaultConfig {
    val minSdk = 23
    val targetSdk = 29
    val compileSdk = 29
}

object Build {
    val build_tools = "com.android.tools.build:gradle:${Versions.gradle}"
    val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object Versions {
    val gradle = "4.0.0"
    val kotlin = "1.3.72"
    val ktx = "1.2.0"
    val nav_components = "2.3.0"
    val room = "2.1.0"
    val appcompat = "1.1.0"
    val constraintlayout = "1.1.3"
    val material_design = "1.1.0"
    val swipe_refresh_layout = "1.1.0"
    val recycler_view_version = "1.1.0"
    val card_view_version = "1.0.0"
    val coroutines_version = "1.3.0"
    val lifecycle_version = "2.2.0"
    val retrofit2_version = "2.9.0"
    val junit_4_version = "4.12"
}


object Dependencies {
    val kotlin_standard_library = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    val kotlin_coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"
    val kotlin_coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"
    val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.nav_components}"
    val navigation_runtime = "androidx.navigation:navigation-runtime:${Versions.nav_components}"
    val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.nav_components}"
    val room_runtime = "androidx.room:room-runtime:${Versions.room}"
    val room_ktx = "androidx.room:room-ktx:${Versions.room}"
    val lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle_version}"
    val lifecycle_coroutines = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit2_version}"
    val retrofit_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2_version}"

    val recycler_view = "androidx.recyclerview:recyclerview:${Versions.recycler_view_version}"
    val card_view = "androidx.cardview:cardview:${Versions.card_view_version}"
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    val material_design = "com.google.android.material:material:${Versions.material_design}"
    val swipe_refresh_layout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipe_refresh_layout}"
}


object AnnotationProcessing {
    val room_compiler = "androidx.room:room-compiler:${Versions.room}"
    val lifecycle_compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle_version}"
}

object TestDependencies {
   //Add mockito lib
    val junit4 = "junit:junit:${Versions.junit_4_version}"
}
