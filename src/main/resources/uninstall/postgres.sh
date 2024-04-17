#!/bin/bash

sudo systemctl stop postgresql
sudo apt-get --purge remove postgresql -y
dpkg -l | grep postgres
sudo apt-get --purge remove postgresql-client -y
sudo rm -rf /var/lib/postgresql/
sudo rm -rf /var/log/postgresql/
sudo rm -rf /etc/postgresql/
sudo deluser postgres
psql --version