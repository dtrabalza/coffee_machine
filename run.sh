#!/bin/bash

gradle clean installDist

cd build/install/coffee_machine/bin/

./coffee_machine
