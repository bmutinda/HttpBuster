# HttpBuster

A very simple way to do http requests in **Android** using **okHttp**
***

### How to use 
---
- Create an instance of **HttpBuster** with an api endpoint
```java
Api api = new Api();
api.setEndpoint("https://example.com/api/");
HttpBuster httpBuster = HttpBuster.withApi(api).build();
```
Thats it. Now you are ready to make http requests to your api endpoint.
#### Make Get request
a.) Without any request parameters 
```java
httpBuster.makeGetRequest("jokes/random", null, new ApiCallback() {
    @Override
    public void done(JSONObject jsonObject, Exception exception) {
        Log.e(TAG, "GET without params done");
    }
});
```

b.) With request parameters 
```java
HashMap<String, Object> map = new HashMap<>();
map.put("firstName", "Mutinda");
map.put("lastName", "Boniface");
httpBuster.makeGetRequest("jokes/random", map, new ApiCallback() {
    @Override
    public void done(JSONObject jsonObject, Exception exception) {
        Log.e(TAG, "GET with params done");
    }
});
```

#### NB:
1. **The same applies for `POST`, `PUT`, `DELETE` requests**
2. We recommend using a single **HttpBuster** instance for the entire application- You can do this by intializing your **HttpBuster** instance via the Application class

Have a look at the demo app for a complete app using the Library [Demo app](https://github.com/bmutinda/HttpBuster/tree/master/demo/src/main/java/com/bmutinda/httpbuster/demo)

---
#### Latest Version: [![Release](https://jitpack.io/v/bmutinda/httpbuster.svg)](https://jitpack.io/#bmutinda/HttpBuster)

---
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
