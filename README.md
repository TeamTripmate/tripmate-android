# tripmate-android
[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.0-blue.svg)](https://kotlinlang.org)
[![Gradle](https://img.shields.io/badge/gradle-8.7-green.svg)](https://gradle.org/)
[![Android Studio](https://img.shields.io/badge/Android%20Studio-2023.2.1%20%28Koala%29-green)](https://developer.android.com/studio)
[![minSdkVersion](https://img.shields.io/badge/minSdkVersion-26-red)](https://developer.android.com/distribute/best-practices/develop/target-sdk)
[![targetSdkVersion](https://img.shields.io/badge/targetSdkVersion-34-orange)](https://developer.android.com/distribute/best-practices/develop/target-sdk)
<br/>

트립메이트 - 강원도 여행 정보 및 동행 찾기 [PlayStore](https://play.google.com/store/apps/details?id=com.tripmate.android)

<p align="center">
<img src="https://github.com/user-attachments/assets/8d64b11a-fc53-4362-a488-9d3231ae7f36" width="30%"/>
<img src="https://github.com/user-attachments/assets/c8d58053-29ef-4770-a7dc-b15c7c08354c" width="30%"/>
<img src="https://github.com/user-attachments/assets/0e8310c0-1d33-49d8-aa0b-c21d12730e04" width="30%"/>
</p>
<p align="center">
<img src="https://github.com/user-attachments/assets/d06a860d-503d-4dd1-b3b1-58b25dc01fd6" width="30%"/>
<img src="https://github.com/user-attachments/assets/a7bc7a9b-a02e-4d1d-b65a-d8b747475c22" width="30%"/>
<img src="https://github.com/user-attachments/assets/95b1eca6-75e6-4aa0-ba8d-aab0e7ec813d" width="30%"/>
</p>

## Features

## Development

### Required

- IDE : Android Studio Koala
- JDK : Java 17을 실행할 수 있는 JDK
- Kotlin Language : 2.0.0

### Language

- Kotlin

### Libraries

- AndroidX
  - Activity Compose
  - Core
  - Lifecycle & ViewModel Compose
  - Navigation
  - DataStore
  - Room
  - StartUp
  - Splash

- Kotlin Libraries (Coroutine, Serialization, Immutable Collection)
- Compose
  - Material3
  - Navigation

- Dagger Hilt
- Retrofit, OkHttp
- Timber
- [Compose-Stable-Marker](https://github.com/skydoves/compose-stable-marker)
- [Landscapist](https://github.com/skydoves/landscapist), Coil-Compose
- [ComposeExtensions](https://github.com/taehwandev/ComposeExtensions)
- Kakao Auth
- [KakaoMaps SDK for Android](https://apis.map.kakao.com/android_v2/docs/)
- Firebase(Analytics, Crashlytics)
- Lottie-Compose
- [Wheel-Picker-Compose](https://github.com/commandiron/WheelPickerCompose)

#### Test & Code analysis

- Ktlint
- Detekt

#### Gradle Dependency

- Gradle Version Catalog

## Architecture
Clean Architecture (but UseCase optional, repository 함수를 단순 포워딩하는 UseCase 는 지양)

## Developers

|Android|Android|Android|
|:---:|:---:|:---:|
|[이지훈](https://github.com/easyhooon)|[정상훈](https://github.com/wjdtkdgns777)|[김대유](https://github.com/kimdaeyou)|
|<img width="144" src="https://github.com/Project-Unifest/unifest-android/assets/51016231/bd9fbce9-a173-4203-b321-314d8ff8cba1?size=144">|<img width="144" src="https://github.com/Project-Unifest/unifest-android/assets/51016231/1beb01a4-6063-400b-aa67-b9f20d6c3d38">|<img width="144" src="https://github.com/user-attachments/assets/54fe7f0a-ac2c-41d9-9227-af78701c0787">|


## Module
<img width="1189" alt="image" src="https://github.com/Project-Unifest/unifest-android/assets/51016231/9b9cd9c8-cda1-46ce-b848-210114ff1d7d">

## Package Structure
```
├── app
│   └── application
├── build-logic
├── core
│   ├── common
│   ├── data
│   ├── database
│   ├── datastore
│   ├── designsystem
│   ├── domain
│   ├── network
│   └── ui
├── feature
│   ├── home
│   ├── login
│   ├── main
│   ├── map
│   ├── mate
│   ├── mate-recruit
│   ├── mate-recruit-post
│   ├── mypage
│   ├── navigator
│   ├── personalization
│   ├── splash
│   ├── trip-detail
│   ├── trip-list
│   └── trip-original
├── gradle
│   └── libs.versions.toml
└── report
    ├── compose-metrics
    └── compose-reports
```
<br/>
