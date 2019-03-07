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
**Can multiple android application access same firebase database?**</br>

1. Yes, it is possible. Go to your firebase console. Select home tab. Click on Add App. Select Add firebase to your Android App. Provide the necessary details for the package name. Download the latest config file and add it to all the android apps connected to this firebase project.</br>
    
2. With Android Studio 2.2 and upwards you can easily create and add apps to you Firebase project without leaving your work environment and no need to download google-services.json</br>

From Android Studio go to Tools->Firebase and create, add apps to project of choice :)</br>
![kbqcc](https://user-images.githubusercontent.com/26745548/53359981-8efa7b00-395e-11e9-9f1a-7d9fdaeeb2cf.png) </br>

**Build unsigned APK file with Android Studio**</br>
I would recommend you to build your apk file with gradle:</br>

1. Click the dropdown menu in the toolbar at the top</br>
2. Select "Edit Configurations"</br>
3. Click the "+"</br>
4. Select "Gradle"</br>
4. Choose your module as Gradle project</br>
6. In Tasks: enter assemble</br>
7. Press Run</br>
Your unsigned apk is now located in</br>
ProjectName\app\build\outputs\apk</br>

**WebView is not loading page in Android 9.0?**</br>
1. Add @xml/network_security_config into your resources:</br>

<?xml version="1.0" encoding="utf-8"?></br>
<network-security-config></br>
    <domain-config cleartextTrafficPermitted="true"></br>
        <domain includeSubdomains="true">www.google.com</domain></br>
    </domain-config></br>
</network-security-config></br>
2. Add this security config to your Manifest like this:</br></br>

<application</br>
    ...</br>
    android:networkSecurityConfig="@xml/network_security_config"</br>
    ...></br>

    ...</br>
</application></br>
3. Now you allowed using HTTP connection on www.google.com subdomains.</br>


