# SaveState [![Build Status](https://travis-ci.org/dkajiwara-asnet/SaveState.svg?branch=master)](https://travis-ci.org/dkajiwara-asnet/SaveState)  

Easy save the instance state

# Usage

```java
public class MainActivity extends Activity {
    /** TAG.*/
    private static final String TAG = MainActivity.class.getSimpleName();

    @SaveState
    private int age;
    @SaveState
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectSave.restoreInstanceState(this, savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        InjectSave.saveInstanceState(this, outState);
    }
}
```


# Download
```groovy
repositories {
    maven {
        url 'http://dkajiwara-asnet.github.io/SaveState/repository'
    }
}
dependencies {
    compile 'com.dkajiwara.savestate:savestate:0.0.1'
}
```
# LICENSE
```
Copyright 2015 dkajiwara

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
