import pygame
from gpiozero import Button
import os
from subprocess import Popen

class Piano(object):
    """docstring for Piano."""

    #the constructor of the piano class
    def __init__(self):
    	#the pygame mixer is initialized
        pygame.mixer.init()
        #the volume of the mixer is set to 1
        pygame.mixer.music.set_volume(1.0)
        vol = os.system("amixer sset 'PCM' 80%")
        #the gpio pins of the buttons are defined
        self.buttons = [Button(5), Button(6), Button(13), Button(19), Button(26)]

        self.disc_btn = Button(22)
        #the name of the collection used
        self.collection = "Piano"

        self.dir = "collections/{:s}/".format(self.collection)

        self.sounds = sorted(os.listdir(self.dir))

    #this method plays a sound
    def play_sound(self, name):
        pygame.mixer.music.load(name)
        pygame.mixer.music.play()

    #this method fades out the sound over a specified amount of time
    def fadeout(self, num):
        pygame.mixer.music.fadeout(num)

    #this method checks if there is any sound playing
    def is_playing(self):
        return pygame.mixer.music.get_busy()

    #this method sets a new collection
    def set_collection(self, name):
        self.collection = name
        self.dir = "collections/{:s}/".format(self.collection)
        self.sounds = sorted(os.listdir(self.dir))

    def set_volume(self,num):
        num = int(num)
        cmd = "amixer sset 'PCM' {}%".format(num)
        vol = os.system(cmd)

        # pygame.mixer.music.set_volume(num)

    def make_discoverable(self):
        process = Popen(['./discoverable.sh'])
        print("the pi is made discoverable")

    #this method wait for the keys to be pressed and then plays
    #the sound associated with key and the collection used
    def keys(self):
        while True:
            if self.disc_btn.is_pressed:
                self.make_discoverable()
                self.disc_btn.wait_for_release()
            # foreach-loop
            for btn in self.buttons:
                if btn.is_pressed:
                    print(self.buttons.index(btn))
                # Sound will fade out in 2 sec, if something is being played.
                    if self.is_playing():
                        self.fadeout(2000);
                    # play the sound that belongs to the button, using button index.
                    self.play_sound(self.dir + self.sounds[self.buttons.index(btn)])
                    # stops the program until button release
                    btn.wait_for_release()
