# HttpBuster

A very simple way to do http requests in **Android** using **okHttp**



### Installation 

In your app `build.gradle` under repositories, include `jitpack.io` like below
```
allprojects{
  repositories {
    maven { url "https://jitpack.io" }
  }
}
```
Then in your dependecies add this line replacing **{latest_version}** with the latest version under releases 
```
  compile 'com.github.bmutinda:httpbuster:{latest_version}'
```
Example:
```
  compile 'com.github.bmutinda:httpbuster:1.0'
```

