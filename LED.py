import RPi.GPIO as GPIO
from gpiozero import Button
import time
GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)
GPIO.setup(18,GPIO.OUT)



button1 = Button(5)
button2 = Button(6)
button3 = Button(13)
button4 = Button(19)
button5 = Button(11)

button1Boolean = False
button2Boolean = False
button3Boolean = False
button4Boolean = False
button5Boolean = False

while (1 < 10):

        if button1.is_pressed == False:
                button1Boolean = True
                print('1 pressed')

        if button2.is_pressed == False:
                button2Boolean = True
                print('2 pressed')

        if button3.is_pressed == False:
                button3Boolean = True
                print('3 pressed')

        if button4.is_pressed == False:
                button4Boolean = True
                print('4 pressed')

        if button5.is_pressed == False:
                button5Boolean = True
                print('5 pressed')

        if (button1 == True):
                print('pressed button 1')
                button1 = False

        if button2 == True:
                print('pressed button 2')
                button2 = False

        if button3 == True:
                print('pressed button 3')
                button3 = False

        if button4 == True:
                print('pressed button 4')
                button4 = False

        if button5 == True:
                print('pressed button 5')
                button5 = False
