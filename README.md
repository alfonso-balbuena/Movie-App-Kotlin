# Movie App Kotlin
App for showing a list of the latest, popular and upcoming movies

## Setup

In order to use the movie database API, in the file apiKey.properties you have to add your apikey v3.


For getting the apiKey go to [Movie database](https://developers.themoviedb.org/3/getting-started/introduction)

## Test

For running the MovieDataBaseTest, you have to add your apikey v3 in the file MovieDataBaseTest.kt

``` 
class MovieDataBaseTest {

    companion object {
        const val API_KEY : String = "" //Put your api key from the movie DB
    } 
```

## Libraries

This app uses the following libraries

* Retrofit
* Moshi
* Room
* Glide
* Navigation

For testing

* Mockwebserver
