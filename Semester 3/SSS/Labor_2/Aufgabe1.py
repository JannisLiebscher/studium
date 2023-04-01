
import cv2
import numpy as np
path = "D:\\Studium\\SSS\\Labor_2\\Grauwerte\\grauwert1.png"
#Grauwertkeil einlesen und weiße Ränder abschneiden
img = cv2.imread(path, cv2.IMREAD_GRAYSCALE)
#manuell die einzelnen grauwerte in eigene bilder übertragen
images = []
images.append(img[:,:100])
images.append(img[:,120:240])
images.append(img[:,255:380])
images.append(img[:,400:520])
images.append(img[:,533:])
std = []
mean = []
cv2.imshow('t', images[4])
cv2.waitKey(0)
cv2.destroyAllWindows()
for x in range(1,6):
    cv2.imwrite("grauwert" + str(x) + ".png", images[x-1])
    mean.append(round(np.mean(images[x-1]), 4))
    std.append(round(np.std(images[x-1]), 4))
for x in range(0,5):
    print("Grwauert " + str(x+1) + " | Median: " + str(mean[x]) + " | STD: " + str(std[x]))
