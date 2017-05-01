#
# Copyright @ 2017 Hamam Hadib
# This program uses Python 3 exclusively
#

# NOT TESTED YET!

#Modules
import _thread
# Classes
from bt_client import BtClient
from piano import Piano

piano = Piano()
bt_c = BtClient(piano)

try:
   _thread.start_new_thread( piano.keys ,())
   _thread.start_new_thread( bt_c.recieving ,())

except:
   print ("Error: unable to start thread")

while 1:
    pass
