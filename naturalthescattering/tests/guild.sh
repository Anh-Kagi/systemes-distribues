#!/bin/sh
echo GUILDE
curl -v -b cookies localhost:8080/api/guild --data "name=test"
