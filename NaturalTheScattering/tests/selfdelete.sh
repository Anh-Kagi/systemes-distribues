#!/bin/sh
echo SELF DELETE
curl -v -b cookies -c cookies -X DELETE localhost:8080/api/self --data "password=test"
