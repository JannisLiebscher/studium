import cv2
import numpy as np
path_ = "D:\\Studium\\SSS\\Labor_2\\Weissbilder\\"
fileNameFormat = "Saved Pictures1.png"
images = []
schwarz = cv2.imread("D:\\Studium\\SSS\\Labor_2\\DarkAverage.png", cv2.IMREAD_GRAYSCALE)
#alle Weißbilder einlesen
for n in range(1,11):
    path = path_ + fileNameFormat
    images.append(cv2.imread(path, cv2.IMREAD_GRAYSCALE))
    fileNameFormat = fileNameFormat.replace(str(n), str(n + 1))

#für jeden pixel alle 10 Bilder addieren und mittelwert bilden
mean = 0
average = np.zeros((480, 640))
for y in range(0, 480):
    for z in range(0, 640):
        for pic in range(0, 10):
            mean += images[pic][y,z]
        average[y, z] = mean/10
        mean = 0
#Schwarz- von Weißbild abziehen und speichern
WhiteAverage = np.subtract(average, schwarz)
cv2.imwrite("WhiteAverage.png", WhiteAverage)
WhiteAverage = WhiteAverage.astype(float)
image_contrast = np.copy(WhiteAverage)
for c in range(0, 10):
    max = np.amax(WhiteAverage)
    for y in range(0, 480):
        for z in range(0, 640):
            image_contrast[y, z] = WhiteAverage[y, z] / max * 255
            
cv2.imwrite("WhiteAverageContrast.png", image_contrast.astype(int))


