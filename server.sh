#!/bin/sh

#./server.sh로 서버 실행

DIRECTORY=".server"

# .server 디렉토리 생성 및 이동
mkdir "$DIRECTORY" && cd "$DIRECTORY" || exit

# paper.jar 파일 다운
wget https://api.papermc.io/v2/projects/paper/versions/1.20.1/builds/196/downloads/paper-1.20.1-196.jar -O paper.jar

# 서버 실행
java -jar paper.jar

# TODO : level-type를 평지로 바꾸기
# eula=true로 변경 및 그 외 부가 설정
sed -i 's/eula=false/eula=true/' eula.txt
sed -i 's/gamemode=survival/gamemode=creative/' server.properties
sed -i 's/enable-command-block=false/enable-command-block=true/' server.properties
sed -i 's/white-list=false/white-list=true/' server.properties

# 서버 다시 실행
java -jar paper.jar

# .server 디렉토리 삭제
cd ..
rm -rf "$DIRECTORY"