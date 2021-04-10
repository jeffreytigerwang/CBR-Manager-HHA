The application seeks to assist with coordinating the Community based rehabilitation (CBR) work in BidiBidi and Palorinya refugee settlements, northern Uganda making it much more efficient to maximise the impact on beneficiaries.

The purpose of the platform is to:
1. Assist the CBR workers in their work as they visit people with disabilities, refer them to services and gradually support their needs and monitor progress
2. Assist HHA in tracking, coordinating and managing this process by generating measurable data which will be used to improve the existing CBR programme, measure impact and inform future programme design

For our project we have implemented a CBR worker Android App and a supporting web-based Database API for sending and receiving data, such as client info, visits info and storing usernames and passwords.

For future iterations we hope to include a referrals and survey page, and implement a web-based admin app for accessing statistics, setting disability priorities and authenticating new cbr workers.


**Project File Structure (app):**
- build: contains build outputs
- libs: contains private libraries
- src: contains all code and resource files for the module in the subdirectories
    - androidTest: contains code for instrumentation tests that run on an Android device
    - main: contains the "main" sourceset files
        - java: contains Java code sources, separated by package names.
        - res: contains application resources, such as drawable files, layout files, and UI string.
        - AndroidManifest.xml: describes essential information about the app to the Android build tools and the Android operating system.
- test: contains code for local tests that run on host JVM
- build.gradle: defines the module-specific build configurations.


**Database API File Structure:**
- backend/api holds database code, README.md holds API build instructions, and where to see the data. package.json shows dependencies, easily installed via the README.md instructions
- backend/api/app holds the database models, controllers and routes. A description of these components are as follows:
- models and model properties describe how the mysql database tables are created, and what columns, and datatypes are set
- controllers hold the logic for each model, describing the functions taken on requests made from android app, and what to respond with. Controllers also describe what parameters might be sent through a URL routes, and how to input parameters into database queries
- routes describe what controllers (ie. functions) are activated when an android app (and web-based admin) reach corresponding URL links. More information about all the API routes are found in the api README.


**Dependencies:**
- Following dependencies should be included when building the application:
    - implementation fileTree(dir: "libs", include: ["*.jar"])
    - implementation 'androidx.appcompat:appcompat:1.2.0'
    - implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
    - implementation "androidx.cardview:cardview:1.0.0"
    - implementation 'com.google.android.material:material:1.3.0'
    - implementation 'androidx.navigation:navigation-fragment:2.2.2'
    - implementation 'androidx.navigation:navigation-ui:2.2.2'
    - implementation 'androidx.recyclerview:recyclerview:1.1.0'
    - implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    - implementation 'com.google.android.material:material:1.2.1'
    - testImplementation 'junit:junit:4.13.1'
    - androidTestImplementation 'androidx.test.ext:junit:1.1.2'
    - androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'
    - implementation 'io.reactivex.rxjava2:rxandroid:2.0.1'
    - implementation 'io.reactivex.rxjava2:rxjava:2.1.7'
    - implementation 'com.squareup.retrofit2:retrofit:2.4.0'
    - implementation 'com.squareup.retrofit2:converter-gson:2.4.0'
    - implementation 'com.rengwuxian.materialedittext:library:2.1.4'
    - implementation 'com.github.javiersantos:MaterialStyledDialogs:2.1’
- Detailed dependencies can be found in the module-level build.gradle file under app package.

**Map**
Maps SDK for Android requires a Google billing account and a API Key.
Please follow the guide on: https://developers.google.com/maps/documentation/android-sdk/start to
replace the current API key with the organization's API key in future for long-term use.