import cv2
import numpy as np
path_ = "D:\\Studium\\SSS\\Labor_2\\Schwarzbilder\\"
fileNameFormat = "Saved Pictures1.png"
images = []
#alle Schwarzbidler einlesen
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
cv2.imwrite("DarkAverage.png", average)
