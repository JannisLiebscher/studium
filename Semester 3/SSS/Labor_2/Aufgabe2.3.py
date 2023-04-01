import cv2
import numpy as np
path = "D:\\Studium\\SSS\\Labor_2\\"
schwarzbild = "DarkAverage.png"
eingabebild = "testbild.png"
weißbild = "WhiteAverage.png"
schwarz = cv2.imread(path + schwarzbild, cv2.IMREAD_GRAYSCALE)
eingabe = cv2.imread(path + eingabebild, cv2.IMREAD_GRAYSCALE)
weiß = cv2.imread(path + weißbild, cv2.IMREAD_GRAYSCALE)
korrigiert = np.subtract(eingabe, schwarz)
cv2.imwrite("Korrigiert.png", korrigiert)

#manuelles subtrahieren, da np.subtract keine overflows beachtet
ausgabe = np.zeros((480, 640))
for y in range(0, 480):
    for z in range(0, 640):
        if(schwarz[y, z] > eingabe[y, z]): 
            ausgabe[y, z] = 0
        else: 
            ausgabe[y, z] = eingabe[y, z] - schwarz[y ,z]
        
cv2.imwrite("KorrigiertManuell.png", ausgabe)
#Weißbild normalisieren
norm_image = cv2.normalize(weiß, None, alpha=0, beta=1,
norm_type=cv2.NORM_MINMAX, dtype=cv2.CV_32F)
#nullen entfernen
for y in range(0, 480):
    for z in range(0, 640):
        if(norm_image[y, z] == 0):  norm_image[y, z] = 0.01

#testbild durch normiertes weißbild dividieren
ausgabe = np.divide(ausgabe, norm_image)
cv2.imwrite("KorrigiertKomplett.png", ausgabe)

cv2.imshow('t', norm_image)
cv2.waitKey(0)
cv2.destroyAllWindows()