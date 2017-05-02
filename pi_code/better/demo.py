#
# Copyright @ 2017 Hamam Hadib
# This program uses Python 3 exclusively
#

# NOT TESTED YET!

#Modules
import _thread
import atexit
# Classes
from bt_client import BtClient
from piano import Piano

piano = Piano()
bt_c = BtClient(piano)

def exit_handler():
    print('The application is ending!')
    bt_c.close_conn()

atexit.register(exit_handler)

try:
   _thread.start_new_thread( piano.keys ,())
   _thread.start_new_thread( bt_c.recieving ,())

except:
   print ("Error: unable to start thread")

while 1:
    pass
