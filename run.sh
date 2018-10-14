#!/bin/bash
for var in "$@"
do
	args="\"$var\" $args"
done
mvn exec:java "-Dexec.mainClass=bootcamp.App" "-Dexec.args=$args"