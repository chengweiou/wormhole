./gradlew bootJar
cp build/libs/wormhole-0.0.1-SNAPSHOT.jar ~/Desktop/docker/universe/wormhole/ser.jar
#cp src/main/resources/application.yml ~/Desktop/docker/universe/wormhole/config/
cp src/main/resources/application-uat.yml ~/Desktop/docker/universe/wormhole/config/
cp src/main/resources/log4j2.xml ~/Desktop/docker/universe/wormhole/config/
cp src/main/resources/jwt-public.pem ~/Desktop/docker/universe/carina/config/
cp src/main/resources/jwt-private.pem ~/Desktop/docker/universe/carina/config/
cp docker-compose.yml ~/Desktop/docker/universe/wormhole/docker-compose.yml
cd ~/Desktop/docker/universe/wormhole

docker compose down
docker compose rm -f
docker compose up -d
