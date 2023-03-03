./gradlew bootJar
scp -i ~/Documents/project/universe/aws/universe-dev.pem build/libs/wormhole-0.0.1-SNAPSHOT.jar ec2-user@127.0.0.1:proj/wormhole/ser.jar
# scp -i ~/Documents/project/universe/aws/universe-dev.pem src/main/resources/application.yml ec2-user@127.0.0.1:proj/wormhole/config/
scp -i ~/Documents/project/universe/aws/universe-dev.pem src/main/resources/application-uat.yml ec2-user@127.0.0.1:proj/wormhole/config/
scp -i ~/Documents/project/universe/aws/universe-dev.pem src/main/resources/log4j2.yml ec2-user@127.0.0.1:proj/wormhole/config/
scp -i ~/Documents/project/universe/aws/universe-dev.pem src/main/resources/jwt-public.pem ec2-user@127.0.0.1:proj/wormhole/config/
scp -i ~/Documents/project/universe/aws/universe-dev.pem src/main/resources/jwt-private.pem ec2-user@127.0.0.1:proj/wormhole/config/
scp -i ~/Documents/project/universe/aws/universe-dev.pem ~/Documents/project/universe/aws/docker-compose-aws.yml ec2-user@127.0.0.1:docker-compose.yml
# scp -i ~/Documents/project/universe/aws/universe-dev.pem universe.subfolder.conf ec2-user@127.0.0.1:swag/config/universe.subfolder.conf
# scp -i ~/Documents/project/universe/aws/universe-dev.pem universe.subdomain.conf ec2-user@127.0.0.1:swag/config/universe.subdomain.conf

ssh -i ~/Documents/project/universe/aws/universe-dev.pem ec2-user@127.0.0.1 << remotessh
docker-compose down
docker-compose rm -f
docker-compose up -d
exit
remotessh
