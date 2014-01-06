cd $1
mvn -Dwtp.version=3.5.1 eclipse:clean eclipse:eclipse
mvn clean install -Dmaven.test.skip=true
mvn dependency:sources
