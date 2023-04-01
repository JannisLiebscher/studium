import cv2
import numpy as np

schwarzbild = "DarkAverage.png"
eingabebild = "testbild.png"
weißbild = "WhiteAverage.png"
schwarz = cv2.imread(schwarzbild, cv2.IMREAD_GRAYSCALE)
eingabe = cv2.imread(eingabebild, cv2.IMREAD_GRAYSCALE)
weiß = cv2.imread(weißbild, cv2.IMREAD_GRAYSCALE)
pixelfehler = []
for y in range(0, 480):
    for z in range(0, 640):
        if(schwarz[y, z] > 200):
            pixelfehler.append((y, z))
        if(weiß[y, z] < 120):
            pixelfehler.append((y, z))
            
for p in pixelfehler:
    if(p[0] <= 1 and p[1] <= 1):
        eingabe[p[0], p[1]] = eingabe[p[0] + 1, p[1] + 1]
    else: 
        eingabe[p[0], p[1]] = eingabe[p[0] - 1, p[1] - 1]

cv2.imwrite("Pixelfehler.png", eingabe)