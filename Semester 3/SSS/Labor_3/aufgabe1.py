import numpy as np
import matplotlib.pyplot as plt

path = 'messungen/mundharmonika.csv'
data = np.genfromtxt(path, delimiter=";", dtype=str, max_rows=1000, 
                     skip_header=1550)
dataAll = np.genfromtxt(path, delimiter=";", dtype=str)
time = data.copy()
for i in range(len(data)):
    data[i] = data[i].replace(",", ".")
    time[i] = i * 0.005
    i+=1
for i in range(len(dataAll)):
    dataAll[i] = dataAll[i].replace(",", ".")
data = data.astype(float)
dataAll = dataAll.astype(float)
#Teilstücke um Hochpunkte zu bestimmen
hack = []
hack.append(data[:260])
hack.append(data[260:520])
hack.append(data[520:740])
hack.append(data[740:])
hackMax = []
#Relativen Index der Hochpunkte
for i in range(4):
    hackMax.append(np.argmax(hack[i]))
for i in range(4):
    for k in range(i):
        hackMax[i] += len(hack[i])
#Absoluten Index der Hochpunkte
period = 0
for i in range(3):
    period += (hackMax[i + 1] - hackMax[i]) * 0.005

    
# Werte ohne Rechnung wurden direkt aus der csv Datei abgelesen
print("Periode: " , round(period / 3, 4), "[ms]")
print("Frequenz: " , round(1000 /(period / 3), 4), "[Hz]")
print("Signaldauer: 50 [ms]")
print("Abtastfrequenz: 200.000 [Hz]")
print("Signallänge: ~ 10.000 Abtastzeitpunkte")
print("Abtastintervall: 0,005 [ms]")

plt.xlabel('Zeit [ms]')
plt.ylabel('Spannung [mV]')
plt.title('Mundharmonika')
plt.plot(time, data)
plt.xticks(np.arange(0, 1000, 100))
plt.show()

dataAll = np.abs(np.fft.fft(dataAll))[:5000]
frequency = dataAll.copy()
for i in range(len(frequency)):
    frequency[i] = i / (10000 * 0.000005)
plt.xlabel('Frequenz [Hz]')
plt.title('Amplitudenspektrum')
plt.plot(frequency, dataAll)
plt.xlim(0, 15000)
plt.show()