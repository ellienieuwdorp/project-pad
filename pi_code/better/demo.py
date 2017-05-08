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

#an instance of the piano class is made
piano = Piano()
#an instance of the BtClient class is made
bt_c = BtClient(piano)

#this method closed the connection
def exit_handler():
    print('The application is ending!')
    bt_c.close_conn()

atexit.register(exit_handler)

#new threads are started
try:
	#a thread for listening to the piano keys is started
   _thread.start_new_thread( piano.keys ,())
   #a thread for the bluetooth connection is started
   _thread.start_new_thread( bt_c.recieving ,())

#if the treads cannot be started a error message is displayed
except:
   print ("Error: unable to start thread")

while 1:
    pass
