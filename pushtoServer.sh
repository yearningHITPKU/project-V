#!/bin/bash 
mvn clean package
cd target/
mv food_delivery-0.0.1-SNAPSHOT.jar local_life_adpter.jar
scp local_life_adpter.jar root@47.93.86.218:/root/v/skills/local_life






