# import RPi.GPIO as GPIO
from gpiozero import Button
from time import sleep
import os
import piano

buttons = [Button(5), Button(6), Button(13), Button(19), Button(26)]

#collection = "Farts"
#sounds = os.listdir("collections/{:s}".format(collections))

#piano.start()
while True:
    for button in buttons:
        if button.is_pressed:
            # piano.fadeout(2000);
            # playMusic(sounds[buttons.index(button)])
            # time.sleep(3)
	    print(buttons.index(button))
	    sleep(0.5)
	    button.wait_for_release()
