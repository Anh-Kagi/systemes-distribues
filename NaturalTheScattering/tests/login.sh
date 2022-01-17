#!/bin/sh
echo LOGIN
curl -v -c cookies localhost:8080/api/auth/login --data "username=test&password=test"
