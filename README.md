# HttpBuster

A very simple way to do http requests in **Android** using **okHttp**
***

#### Latest Version: [![Release](https://jitpack.io/v/bmutinda/httpbuster.svg)](https://jitpack.io/#bmutinda/HttpBuster)


### Installation 
---

In your app `build.gradle` under repositories, include `jitpack.io` like below
```java
allprojects{
  repositories {
    maven { url "https://jitpack.io" }
  }
}
```
Then in your dependecies add this line replacing **{latest_version}** with the latest version under releases 
```java
  compile 'com.github.bmutinda:httpbuster:{latest_version}'
```
Example:
```java
  compile 'com.github.bmutinda:httpbuster:1.0'
```
