

ANDROID_JAR=~/android.jar
PACKAGE_NAME=com.muzraf.wsf
PACKAGE_NAME_SLASH=com/muzraf/wsf
APK_NAME=wsf.apk

mkdir -p __build
echo "aapt2 compile"
aapt2 compile --dir res -o __build/res.zip
echo "aapt2 link"
aapt2 link __build/res.zip -I $ANDROID_JAR -o __build/a.apk --manifest AndroidManifest.xml --java src/java
echo "java"
javac -source 1.8 -target 1.8 -d __build -bootclasspath $ANDROID_JAR -classpath src/java src/java/$PACKAGE_NAME_SLASH/*.java
echo "dex"
d8 __build/$PACKAGE_NAME_SLASH/*.class --lib $ANDROID_JAR
mv classes.dex __build/
echo "zip"
zip -uj __build/a.apk __build/classes.dex
echo "zipalign"
zipalign -p -f -v 4 __build/a.apk __build/b.apk
echo "sign"
apksigner sign --ks debug.keystore --ks-pass pass:android --out  __build/$APK_NAME __build/b.apk
mv __build/$APK_NAME $APK_NAME
cp $APK_NAME ~/storage/downloads/
