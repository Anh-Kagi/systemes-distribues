#!/bin/sh
echo LOGOUT
curl -v -b cookies -c cookies -X POST localhost:8080/api/auth/logout
