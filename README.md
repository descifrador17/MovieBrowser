# Initial Commit

This is an app that allows the user to browse through the latest movies
[This shows the basic layout of the app](https://drive.google.com/file/d/13eHdPcUXsZMYgjBXlk9NMByhjUTZS6Au/view?usp=sharing)

[This is The Movie Details Layout](https://drive.google.com/file/d/1PU_a8_s-M_7UzPANAsudOV2rwZWdvI-t/view?usp=sharing)

[The MovieDB](https://www.themoviedb.org/) API was used to gather all the information
# Prerequisites
1. Android Studio
2.  [TheMovieDB](https://www.themoviedb.org/) API key 
3. Frameworks - Retrofit, RxJava(Kotlin)

## [Retrofit](https://github.com/square/retrofit) 

**Retrofit** is a REST Client for Java and Android. It makes it relatively easy to retrieve and upload JSON (or other structured data) via a REST based webservice. In **Retrofit** you configure which converter is used for the data serialization.

## [RxJava](https://github.com/ReactiveX/RxJava)

**RxJava** is a Java VM implementation of [ReactiveX](http://reactivex.io/) a library for composing asynchronous and event-based programs by using observable sequences. The building blocks of **RxJava** are Observables and Subscribers. ... For example, Observables often don't start emitting items until someone subscribes to them.

## [OkHttp](https://github.com/square/okhttp)
HTTP is the way modern applications network. It’s how we exchange data & media. Doing HTTP efficiently makes your stuff load faster and saves bandwidth.

## [Glide](https://github.com/bumptech/glide)
**Glide** is an Image Loader Library for **Android** developed by bumptech and is a library that is recommended by Google. It has been used in many Google open source projects including Google I/O 2014 official application. It provides animated GIF support and handles image loading/caching.

# Getting Started

## Adding Dependencies

    //Glide  
	implementation 'com.github.bumptech.glide:glide:4.10.0'  
	
	//Retrofit  
	implementation 'com.squareup.retrofit2:adapter-rxjava2:2.6.0'    
	implementation 'com.squareup.retrofit2:retrofit:2.7.0'    
	
	//Gson  
	implementation 'com.squareup.retrofit2:converter-gson:2.7.0'  
	implementation 'com.google.code.gson:gson:2.8.5'  
	
	//OkHttp  
	implementation "com.squareup.okhttp3:okhttp:4.3.1"  
	
	//RxJava and RxAndroid
	implementation 'io.reactivex.rxjava2:rxjava:2.2.12'    
	implementation 'io.reactivex.rxjava2:rxandroid:2.1.1'

1. [Glide](https://github.com/bumptech/glide) to load the images from a URL
2. [OkHttp](https://square.github.io/okhttp/)
3. GSON to parse data received from the [website](https://www.themoviedb.org/)

## Setting Up the App
 [Create a folder named util and Kotlin file Constants.kt](https://imgur.com/hFKtw80)
 It will keep your API Key and Base URL for the App
 
 [Enter your generated API Key at the place shown in the picture](https://imgur.com/T11DTuV)
 
 Run...

