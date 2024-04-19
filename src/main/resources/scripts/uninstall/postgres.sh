#!/bin/bash

sudo systemctl stop postgresql
sudo apt --purge remove postgresql -y
sudo apt autoremove
psql --version