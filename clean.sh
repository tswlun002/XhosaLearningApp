#!/bin/bash
rm /home/lunga/.android/avd/Pixel_3a_API_30_x86.avd/*.lock
sudo sh -c "/usr/bin/echo 3 > /proc/sys/vm/drop_caches"