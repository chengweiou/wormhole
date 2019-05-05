### uat 环境
redis
docker run —rm --name redis  -p 6379:6379 d redis

#### 上传
```
./uat.sh
```
first time:
```
change active profile to uat
mkdir -pv ~/Desktop/docker/wormhole/config
cp src/main/resources/application.yml ~/Desktop/docker/wormhole/config/
chmod +x uat.sh
./uat.sh
```