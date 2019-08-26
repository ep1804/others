#!/usr/bin/env bash

rm -f run.jar
scalac $1/*.scala -d run.jar
scala -cp run.jar $2
