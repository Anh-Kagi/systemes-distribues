#!/bin/sh
echo REGISTER
curl -v -c cookies -X POST localhost:8080/api/auth/register --data "username=test&password=test"
