./gradlew bootJar
cp build/libs/wormhole-0.0.1-SNAPSHOT.jar ~/Desktop/docker/wormhole/ser.jar
cp src/main/resources/application-uat.yml ~/Desktop/docker/wormhole/config/
cp src/main/resources/log4j2.xml ~/Desktop/docker/wormhole/config/
cd ~/Desktop/docker/wormhole
docker stop wormhole
docker run --rm -it -d --name wormhole --network net -p 60000:8906 --link mysql:mysql -v /Users/chengweiou/Desktop/docker/wormhole:/proj/ -w /proj/ openjdk java -jar ser.jar