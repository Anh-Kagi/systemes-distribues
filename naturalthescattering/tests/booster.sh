#!/bin/sh
echo BOOSTER
curl -v -X POST -b cookies localhost:8080/api/booster/open
