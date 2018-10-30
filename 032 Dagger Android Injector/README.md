 **Dagger Dependency**</br>
    
    implementation "com.google.dagger:dagger:2.13"
    annotationProcessor "com.google.dagger:dagger-compiler:2.13"
    compileOnly 'javax.annotation:jsr250-api:1.0'
    implementation 'javax.inject:javax.inject:1'
    implementation "com.google.dagger:dagger-android-support:2.10-rc4"

    implementation 'com.squareup.retrofit2:retrofit:2.3.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.3.0'

    //http logging
    implementation 'com.squareup.okhttp3:logging-interceptor:3.9.1'

    implementation 'com.jakewharton:butterknife:8.8.1'
    annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.1'
    compileOnly 'javax.annotation:jsr250-api:1.0'

    //RxJava
    implementation 'io.reactivex.rxjava2:rxandroid:2.0.2'
    implementation "io.reactivex.rxjava2:rxjava:2.1.12"
