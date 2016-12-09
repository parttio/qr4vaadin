# QRCode addon for Vaadin 7

QR codes were born in Japan but are becoming more and more popular around 
the world and you pretty much can find them everywhere on the web, in magazines, 
on the streets and even Android nowadays ships with a native QR code reader. 

The information encoded in a QR code can be text, URL or other data and can be read 
by most bar code readers and mobile phones.


Building the addon
-------------------
To build the addon you only need to run ```./gradlew :addon:jar``` and the addon will 
be created under the addon/build/libs folder

Running the demo application
-----------------------------
To run the demo application you can run ```./gradlew :demo:vaadinRun``` which will
start a embedded jetty server. The application will be available at http://localhost:8888.

Developing the addon
---------------------
To start working on the addon in Eclipse you should do the following:
1. Run ```./gradlew eclipse``` in the root folder. It will generate the needed eclipse files.
2. In eclipse create a new workspace (for instance named qrcode)
3. In eclipse select import->Existing projects into workspace and select the root folder
4. Ensure both the addon and the demo project gets imported
5. Start working with the code

Contributing to the addon
--------------------------
If you want to contribute fixes or features to the project then you should fork the project 
and create a pull request in Github. 

Some guidelines to creating the pull request:
* The title of the pull request should describe what it fixes or adds to the addon (eg. Fixes NPE in initialization)
* The title of the pull request should end with the issue number (eg. #1)
* A pull request should only fix one issue. Create several if you are fixing several issues.
* A pull request should only contain one commit fixing the issue. 
