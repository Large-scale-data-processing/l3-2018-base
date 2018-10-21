#!/bin/bash

runuser -l vagrant -c 'yay --noconfirm --answeredit none -S jdk8 --noconfirm'

archlinux-java set java-8-jdk

cd /opt
wget http://ftp.man.poznan.pl/apache/spark/spark-2.3.2/spark-2.3.2-bin-hadoop2.7.tgz
tar xzvf ./spark-2.3.2-bin-hadoop2.7.tgz
ln -s /opt/spark-2.3.2-bin-hadoop2.7 /opt/apache-spark
echo 'alias spark-submit="/opt/apache-spark/bin/spark-submit"' > /etc/profile.d/spark.sh
echo 'alias spark-shell="/opt/apache-spark/bin/spark-shell"' >> /etc/profile.d/spark.sh


