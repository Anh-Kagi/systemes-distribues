#!/bin/sh
echo SET MDP1
curl -v -b cookies -X PUT localhost:8080/api/self/password --data "old=test&new=$PASSWD"
echo SET MDP 2
curl -v -b cookies -X PUT localhost:8080/api/self/password --data "old=$PASSWD&new=test"
