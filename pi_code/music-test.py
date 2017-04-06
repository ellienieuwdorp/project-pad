import pygame
import time
file = "test.mp3"
pygame.mixer.init()
pygame.mixer.music.load(file)
pygame.mixer.music.play()
time.sleep(40)
pygame.mixer.music.fadeout(2000)

print(pygame.mixer.music.get_volume())
# print(pygame.mixer.music.set_volume(1.0))
print(pygame.mixer.music.get_volume())

while pygame.mixer.music.get_busy() == True:
    continue
