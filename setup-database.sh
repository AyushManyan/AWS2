#!/bin/sh -e

mysql -uroot -p -e "DROP DATABASE IF EXISTS Cloudsmith;
CREATE DATABASE Cloudsmith;
GRANT ALL PRIVILEGES ON Cloudsmith.* TO exampCloudsmithle@localhost IDENTIFIED BY 'Cloudsmith';"
