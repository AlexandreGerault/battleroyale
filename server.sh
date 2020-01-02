#!/bin/bash

SERVER_DIR="./server"

SPONGE_TYPE="spongevanilla"
SPONGE_VERSION="1.12.2-7.1.8"
SPONGE_JAR_URL="https://repo.spongepowered.org/maven/org/spongepowered/$SPONGE_TYPE/$SPONGE_VERSION/$SPONGE_TYPE-$SPONGE_VERSION.jar"

function create_server_dir_if_it_does_not_exist {
  local SERVER_DIR=$1

  if [ -d "$SERVER_DIR" ]; then
    echo "The server directory already exists"
  else
    echo "Creating server directory..."
    mkdir -p "$SERVER_DIR"
    echo "Server directory created!"
  fi
}

function download_sponge_jar_if_not_presents {
  local SPONGE_TYPE=$1
  local SPONGE_VERSION=$2
  local SPONGE_JAR_URL=$3

  if [ -f "$SPONGE_TYPE-$SPONGE_VERSION.jar" ]; then
    echo "The sponge jar file already exists"
  else
    echo "The specified Sponge jar archive doesn't exists. Starting its download..."
    wget "$SPONGE_JAR_URL"
    echo "Sponge jar file downloaded"
  fi
}

function eula_file {
  if [ -f "eula.txt" ]; then
    echo "The eula file already exists"
  else
    echo "The eula file doesn't exist. Creating it..."
    touch "eula.txt"
    echo "Empty eula.txt file created"
    echo "eula=true" > "eula.txt"
    echo "Eula file updated to true!"
  fi
}

function start_server {
  local SPONGE_TYPE=$1
  local SPONGE_VERSION=$2
  local SPONGE_JAR_URL=$3

  java -Xms1G -Xmx2G -jar "$SPONGE_TYPE-$SPONGE_VERSION.jar"
}

create_server_dir_if_it_does_not_exist "$SERVER_DIR"
cd "$SERVER_DIR" || exit 1
download_sponge_jar_if_not_presents "$SPONGE_TYPE" "$SPONGE_VERSION" "$SPONGE_JAR_URL"
eula_file
start_server "$SPONGE_TYPE" "$SPONGE_VERSION" "$SPONGE_JAR_URL"