# import RPi.GPIO as GPIO
from gpiozero import Button
import time
import os
import piano

buttons = [Button(5), Button(6), Button(13), Button(19), Button(11)]

collection = "Farts"
sounds = os.listdir("collections/{:s}".format(collections))

piano.start()
for button in buttons:
    if button.is_pressed:
        piano.fadeout(2000);
        playMusic(sounds[buttons.index(button)])
        # time.sleep(3)
