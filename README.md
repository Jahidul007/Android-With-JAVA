# Android-With-JAVA
**What is Android?**</br>
![who_i_am](https://user-images.githubusercontent.com/26745548/40565733-7d8cdf32-608f-11e8-94d8-626f6be5a160.jpg)</br>
Android is an open source and Linux-based Operating System for mobile devices such as smartphones and tablet computers. Android was developed by the Open Handset Alliance, led by Google, and other companies......

**Why Android?**</br>
![why_android](https://user-images.githubusercontent.com/26745548/40565682-4b3b888a-608f-11e8-864c-aeb5826efecb.jpg)

**Retrofit**</br>
Retrofit is a type-safe REST client for Android, Java and Kotlin developed by Square. The library provides a powerful framework for authenticating and interacting with APIs and sending network requests with OkHttp. See this guide to understand how OkHttp works.

**Broadcast Receiver**</br>
A broadcast receiver (receiver) is an Android component which allows you to register for system or application events. All registered  receivers for an event are notified by the Android runtime once this event happens.</br>
The following example demonstrates the registration for the BOOT_COMPLETED event in the Android manifest file.</br>

                <action android:name="android.intent.action.BOOT_COMPLETED"/>
                <action android:name="android.net.conn.CONNECTIVITY_CHANGE"/>


**ProGruad to prevent Reverse Engineering**</br>

 buildTypes {
        debug {
            shrinkResources true
            minifyEnabled true
            useProguard true
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
        release {
            shrinkResources true
            minifyEnabled true
            useProguard true
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
