set -x
mvn exec:java -Dexec.mainClass="bootcamp.App" "-Dexec.arguments=\'$@\'"