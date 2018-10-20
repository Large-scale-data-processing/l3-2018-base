#!/bin/bash

runuser -l vagrant -c 'yay --noconfirm --answeredit none -S jdk8 --noconfirm'

archlinux-java set java-8-jdk

runuser -l vagrant -c 'yay --noconfirm --answeredit none -S apache-spark'

runuser -l vagrant -c 'yay --noconfirm --answeredit none -S lightbend-activator'

runuser -l vagrant -c 'yay --noconfirm --answeredit none -S sbt'


