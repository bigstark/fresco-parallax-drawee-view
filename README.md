# fresco-parallax-drawee-view
[![license](https://img.shields.io/hexpm/l/plug.svg)](LICENSE)
[![jitpack](https://img.shields.io/badge/jitpack-1.2-green.svg)](https://jitpack.io/#bigstark/fresco-parallax-drawee-view)

It is hard to use parallax effect in your app when you are using fresco image loader library.

But if you use this library, you can use parallax effect while using fresco.

![ParallaxDraweeView](https://github.com/bigstark/fresco-parallax-drawee-view/blob/master/screenshots/ParallaxDraweeView.gif)</br>
![ParallaxSample1](https://github.com/bigstark/fresco-parallax-drawee-view/blob/master/screenshots/parallax-scroll-forward.gif)
![ParallaxSample2](https://github.com/bigstark/fresco-parallax-drawee-view/blob/master/screenshots/parallax-scroll-opposite.gif)


## Include your project
add build.gradle
```
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```
```
dependencies {
    compile 'com.github.bigstark:fresco-parallax-drawee-view:1.2.1'
}
```

## Concept

- It supports only vertical parallax effect.
- It cannot support image crop.
- Actual Image's ratio must be larger than DraweeView ratio.
- Scroll offset is between 0 and 1 float value.

## Usage

```xml
<com.bigstark.fresco.parallax.library.ParallaxDraweeView
    xmlns:fresco="http://schemas.android.com/apk/res-auto"
    android:id="@+id/pdv_sample"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    fresco:viewAspectRatio="1"
    fresco:fadeDuration="300"
    fresco:actualImageScaleType="fitCenter"
    />
```
```Java
ParallaxDraweeView pdvSample = (ParallaxDraweeView) findViewById(R.id.pdv_sample);
pdvSample.setImageURI(IMAGE_URI);

float offset = 0.5f;
pdvSample.setOffset(offset); // It could be run after image loaded.
```


License
-------

    Copyright 2017 BigStarK

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
