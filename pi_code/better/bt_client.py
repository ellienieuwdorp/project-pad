from bluetooth import *

class BtClient(object):

    def __init__(self , piano):
        self.server_sock = BluetoothSocket( RFCOMM )
        self.server_sock.bind(("",PORT_ANY))
        self.server_sock.listen(1)

        self.port = self.server_sock.getsockname()[1]

        self.uuid = "94f39d29-7d6d-437d-973b-fba39e49d4ee"

        advertise_service( self.server_sock, "PianoServer",
                           service_id = self.uuid,
                           service_classes = [ self.uuid, SERIAL_PORT_CLASS ],
                           profiles = [ SERIAL_PORT_PROFILE ],
        #                   protocols = [ OBEX_UUID ]
                            )

        self.piano = piano

    # the most important function
    def recieving(self):

        while True:
            print("Waiting for connection on RFCOMM channel %d" % self.port)
            self.client_sock, self.client_info = self.server_sock.accept()
            print("Accepted connection from ", self.client_info)

            try:
                while True:
                    data = self.client_sock.recv(1024)
                    if len(data) == 0: break
                    # converting from bytes to string
                    data = data.decode("utf-8")
                    print("received [%s]" % data)

                    data_arr = data.rsplit(None, 1)
                    print(data_arr)
                    if data_arr[0].strip() == 'collection:':
                        print("new collection: "+ data_arr[1])
                        self.piano.set_collection(data_arr[1])
                    elif data_arr[0].strip() == 'volume:':
                        self.piano.set_volume(data_arr[1])

            except IOError:
                pass

            print("disconnected")


    def close_conn(self):
        self.client_sock.close()
        self.server_sock.close()
        print("Bluetooth connection is closed")
