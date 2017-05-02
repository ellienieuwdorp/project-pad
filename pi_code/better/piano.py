#
# Copyright @ 2017 Hamam Hadib
# This program uses Python 3 exclusively
#

import pygame
from gpiozero import Button
import os
class Piano(object):
    """docstring for Piano."""

    def __init__(self):
        pygame.mixer.init()
        pygame.mixer.music.set_volume(1.0)

        self.buttons = [Button(5), Button(6), Button(13), Button(19), Button(26)]
        self.collection = "Farts"
        self.dir = "collections/{:s}/".format(self.collection)
        self.sounds = sorted(os.listdir(self.dir))
    def play_sound(self, name):
        pygame.mixer.music.load(name)
        pygame.mixer.music.play()

    def fadeout(self, num):
        pygame.mixer.music.fadeout(num)

    def is_playing(self):
        return pygame.mixer.music.get_busy()

    def set_collection(self, name):
        self.collection = name
        self.dir = "collections/{:s}/".format(self.collection)
        self.sounds = sorted(os.listdir(self.dir))

    # The most important function
    def keys(self):
        while True:
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
