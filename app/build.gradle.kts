plugins {
    // Application Specific Plugins
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.androidHilt)
    id(BuildPlugins.kotlinParcelize)

    // Internal Script plugins
    id(ScriptPlugins.variants)
    id(ScriptPlugins.quality)
    id(ScriptPlugins.compilation)
}

android {
    compileSdk = AndroidSdk.compile

    defaultConfig {
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target

        applicationId = AndroidClient.appId
        versionCode = AndroidClient.versionCode
        versionName = AndroidClient.versionName
        testInstrumentationRunner = AndroidClient.testRunner

        buildConfigField("String", "SPOTIFY_BASE_URL", "\"https://api.spotify.com/\"")

        javaCompileOptions {
            annotationProcessorOptions {
                argument("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }

    sourceSets {
        map { it.java.srcDir("src/${it.name}/java") }
    }

    buildFeatures {
        viewBinding = true
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    testOptions.unitTests.isIncludeAndroidResources = true
    testOptions.unitTests.isReturnDefaultValues = true
}

dependencies {
    // Modules and Projects
    implementation(project(":common:reactive"))
    implementation(project(":common:extension"))
    implementation(project(":common:platform"))
    implementation(project(":common:extension"))
    implementation(project(":authenticator"))

    // Compile time dependencies
    kapt(Libraries.lifecycleCompiler)
    kapt(Libraries.hiltCompiler)
    kaptTest(Libraries.hiltCompiler)
    kaptAndroidTest(Libraries.hiltCompiler)
    kapt(Libraries.roomCompiler)

    // Application dependencies
    implementation(Libraries.roomRuntime)
    implementation(Libraries.roomRxAdapter)
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.kotlinCoroutines)
    implementation(Libraries.kotlinCoroutinesAndroid)
    implementation(Libraries.swipeRefreshLayout)
    implementation(Libraries.appCompat)
    implementation(Libraries.ktxCore)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.viewModel)
    implementation(Libraries.liveData)
    implementation(Libraries.lifecycleExtensions)
    implementation(Libraries.cardView)
    implementation(Libraries.recyclerView)
    implementation(Libraries.material)
    implementation(Libraries.androidAnnotations)
    implementation(Libraries.picasso)
    implementation(Libraries.hilt)
    implementation(Libraries.spotify)
    implementation(Libraries.retrofit)
    implementation(Libraries.rxRetrofitAdapter)
    implementation(Libraries.moshiAdapter)
    implementation(Libraries.moshiKotlin)
    implementation(Libraries.okHttp)
    implementation(Libraries.okHttpLoggingInterceptor)
    implementation(Libraries.rxAndroid)
    implementation(Libraries.rxJava)
    implementation(Libraries.lottie)
    implementation(Libraries.fragmentKtx)
    implementation(Libraries.activityKtx)

    // Unit/Android tests dependencies
    testImplementation(TestLibraries.junit4)
    testImplementation(TestLibraries.kluent)
    testImplementation(TestLibraries.okHttpMockWebServer)
    testImplementation(TestLibraries.mockitoCore)
    testImplementation(TestLibraries.mockitoInline)
    testImplementation(TestLibraries.mockitoKotlin)
    testImplementation(TestLibraries.mockitoJUnitJupyter)
    testImplementation(TestLibraries.robolectric)
    testImplementation(TestLibraries.coreTesting)
    testImplementation(Libraries.hilt)
    testImplementation(TestLibraries.hiltTesting)
    testImplementation(TestLibraries.coreTestKtx)

    // Test Runtime
    testRuntimeOnly(TestRuntimeLibraries.jUnitJupyter)

    // Acceptance tests dependencies
    androidTestImplementation(TestLibraries.testRunner)
    androidTestImplementation(TestLibraries.espressoCore)
    androidTestImplementation(TestLibraries.testExtJunit)
    androidTestImplementation(TestLibraries.testRules)
    androidTestImplementation(TestLibraries.espressoIntents)
    androidTestImplementation(TestLibraries.hiltTesting)
    androidTestImplementation(Libraries.hilt)

    // Development dependencies
    debugImplementation(TestLibraries.fragmentTesting)
    debugImplementation(DevLibraries.leakCanary)
}
