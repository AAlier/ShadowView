
# ShadowView

ShadowView is a library for adding shadow effects to views in Android apps. It provides a simple way to add depth and dimension to your UI elements, making them look more visually appealing and engaging with smooth shadow that can be customized.

<img src="https://github.com/AAlier/ShadowView/blob/master/screenshot.png" alt="screenshot" width="260" height="500">

## Installation

To use ShadowView in your Android project, add the following dependency to your app's build.gradle file:
`implementation 'com.github.AAlier:ShadowView:Tag'`

Replace `Tag` with the latest version of ShadowView available on [JitPack](https://jitpack.io/#AAlier/ShadowView).

You'll also need to add the JitPack repository to your project-level build.gradle file:
allprojects { repositories { ... maven { url 'https://jitpack.io' } } }

## Usage

Once you've added the ShadowView dependency to your project, you can use it in your layout XML files by adding the `com.jack.shadowview.ShadowView` view:
```xml
<com.jack.shadowview.ShadowView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:backgroundColor="#A0FB45"  
    app:radius="80dp"  
    android:pivotX="50"  
    android:pivotY="50"  
    app:shadowColor="#FF0A0A"  
    app:shadowWidth="10dp"  
    app:shadowAlpha="1">

    <!-- Your content here -->

</com.jack.shadowview.ShadowView>
```

You can also apply shadow effects programmatically using the `ShadowDrawable` class:

    view.background = ShadowDrawable.Builder()
                .setBackgroundColor(Color.RED)
                .setShadowColor(Color.BLUE)
                .setRadius(25f)
                .setAlpha(1f)
                .setX(10f)
                .setY(10f)
                .setShadowWidth(10f)
                .build()

You can customize the shadow color, radius, and corner radius using the `app:shadowColor`, `app:radius`, and `app:shadowWidth` attributes, respectively.

# License

```xml
Designed and developed by 2023 Alier

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

## Contribute

If you'd like to contribute to ShadowView, feel free to submit a pull request or open an issue on the [GitHub repository](https://github.com/AAlier/ShadowView). We welcome bug reports, feature requests, and code contributions. Please read our [contributing guidelines](https://chat.openai.com/CONTRIBUTING.md) before submitting any code. Thank you for your interest in contributing to ShadowView!
