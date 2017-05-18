#!/bin/bash

# enable the bluetooth interface
sudo hciconfig hci0 up
sudo hciconfig hci0 piscan

# The device will be discoverable for 60 sec
sleep 60

# disable the bluetooth interface
sudo hciconfig hci0 noscan
sudo hciconfig hci0 down
