import pygame
import time

file = "test.mp3"
def start():
    pygame.mixer.init()
    pygame.mixer.music.set_volume(1.0)

def playMusic(name):
    pygame.mixer.music.load(file)
    pygame.mixer.music.play()

def fadeout():
    pygame.mixer.music.fadeout(2000)

def isPlaying():
    return pygame.mixer.music.get_busy()
