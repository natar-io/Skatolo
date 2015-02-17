#!/bin/bash 

TMP=tmp
NAME=controlP5

mkdir $TMP
mkdir $TMP/$NAME 
mkdir $TMP/$NAME/library


echo "Copy Library"
# Library
cp target/$NAME-*.jar $TMP/$NAME/library/$NAME.jar


echo "Copy the sources" 
# copy the source also
cp -R src $TMP/$NAME/

cp -R test $TMP/$NAME/
cp -R data $TMP/$NAME/
cp -R lib $TMP/$NAME/

# Not really an external library. It is here until
# Processing is in Maven Central. 
rm $TMP/$NAME/core.jar

echo "Copy the examples" 
cp -R examples $TMP/$NAME/

cp -R pom.xml $TMP/$NAME/

echo "Copy the JavaDoc" 
cp -R target/site/apidocs $TMP/$NAME/

echo "Create the archive..." 
cd $TMP

tar -zcf $NAME.tgz $NAME

mv $NAME.tgz  .. 
cd .. 

echo "Clean " 
rm -rf $TMP


echo "Creation OK" 
