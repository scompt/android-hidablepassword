HidablePassword
===============

A `EditText` subclass that allows the user to toggle the visibility of the password.

Features
--------

Clicking on show/hide toggles the visibility of the password.

Usage
-----

Use it in your layout file like so:

    <com.scompt.hidablepassword.HidablePasswordEditText android:layout_height="wrap_content"
                                                        android:layout_width="match_parent"
                                                        android:inputType="textPassword" />

Including in your project
-------------------------

Either include it in your project as an android library project, or grab it via maven:

    <dependency>
        <groupId>com.scompt.hidablepassword</groupId>
        <artifactId>library</artifactId>
        <version>{latest.version}</version>
        <type>aar</type>
    </dependency>

Credits
-------

* The `TextDrawable` class is adapted from [this StackOverflow answer](http://stackoverflow.com/a/8831182/111777) 
from [plowman](http://stackoverflow.com/users/426794/plowman).

License
-------

    Copyright 2014 Edward Dale

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
