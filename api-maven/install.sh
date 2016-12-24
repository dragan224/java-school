mvn clean 
mvn compile
mvn test
mvn package
mvn install:install-file \
   -Dfile=target/api-maven-0.0.1.jar \
   -DgroupId=com.dragan \
   -DartifactId=api-maven \
   -Dversion=0.0.1 \
   -Dpackaging=jar \
   -DgeneratePom=true