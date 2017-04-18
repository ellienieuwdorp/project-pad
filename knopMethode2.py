from gpiozero import Button
from signal import pause





def buttonPress1():
    print("button 1 pressed")

def buttonPress2():
    print("button 2 pressed")
    
def buttonPress3():
    print("button 3 pressed")
    
def buttonPress4():
    print("button 4 pressed")

def buttonPress5():
    print("button 5 pressed")
    
button1 = Button(5)
button2 = Button(6)
button3 = Button(13)
button4 = Button(19)
button5 = Button(11)


button1.when_pressed = buttonPress1
                 
button2.when_pressed = buttonPress2
                
button3.when_pressed = buttonPress3
               
button4.when_pressed = buttonPress4

button5.when_pressed = buttonPress5

pause()              
      
