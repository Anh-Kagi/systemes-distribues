#!/bin/sh
echo THREAD
curl -v -b cookies localhost:8080/api/forum/thread --data "name=test&content=test"
echo MSG
curl -v -b cookies localhost:8080/api/forum/thread/1 --data "content=test"
