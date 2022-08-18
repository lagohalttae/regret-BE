# regret-BE

실행 방법 docker-compose:
1. build해서 jar 파일을 만든다.
2. local에서 `docker-compose -f docker-compose-dev.yml up`을 입력한다.

실행 방법 docker:
1. `docker buil -t mysql -f ./mysql/Dockerfile.dev`로 mysql을 빌드한다.
2. `dockr run mysql`로 mysql을 실행시킨다.