
import math as m
import numpy as np
import matplotlib.pyplot as plt

path = "D:\\Studium\\SSS\\Labor_1\\10cm.csv"
abstand = 10
x = []      #abstandswerte
yAV = []    #gemessene spannung, jeweils Mittelwert aus 4000 datenpunkten
while abstand <= 70:
    #alle gemessenen spannungen für aktuellen Abstand einlesen und in data speichern
    data = np.genfromtxt(path, delimiter=";", dtype=str, max_rows=4000, 
                         skip_header=1000)   
    i = 0       #laufvariable
    av = 0.0    #durchschnitt aller gemessenen spannungen für aktuellen abstand
    while i < len(data):    #alle werte in float umwandeln und dabei mittelwert berechnen
        data[i] = data[i].replace(",", ".")
        av = av + data[i].astype(float)
        i+=1
    data = data.astype(float)
    std = np.std(data, dtype=np.float64)    #standartabweichung vom aktuellen Mittelwert berechnen
    av = av / len(data)
    yAV.append(round(av, 5))                #mittelwert für aktuellen abstand auf 5 nachkommastellen runden und in y speichern
    print(str(abstand) + "cm  ----------\n" + "av: " 
          + str(round(av, 5)) + " V\nstd: " + str(round(std, 5)))
    path = path.replace(str(abstand) + "cm", str(abstand + 3) + "cm")   #datei zum einlesen aktualisieren
    x.append(abstand)
    abstand += 3
 
safe = yAV.copy()  #y werte sichern
plt.xlabel('Abstand [cm]')
plt.ylabel('Spannung [V]')
plt.title('Übertragungsfunktion Abstandssensor')
plt.plot(x, yAV,)
plt.show()
 
#x und y werte logarithmieren
for i in range(0, len(yAV)):
    yAV[i] = np.log(yAV[i])
for i in range(0, len(x)):
    x[i] = np.log(x[i])   
plt.title('Übertragungsfunktion Abstandssensor logarithmiert')
plt.plot(x, yAV,)
plt.show()

#Lineare Regression mit den logarithmierten werten
xMean = np.mean(x)
yMean = np.mean(safe)
tmp1 = tmp2 = 0.0
for i in range(0, len(x)):
    tmp1 += (x[i] - np.abs(xMean)) * (yAV[i] - np.abs(yMean))
    tmp2 += m.pow(x[i] - np.abs(xMean), 2)    
steigung = tmp1 / tmp2
offset = yMean - steigung * xMean
print("\noffset: " + str(offset) +"\nsteigung: " + str(steigung))


#Lineare Kennlinie von 10 bis 70 cm darstellen
yReg = []
for i in range(10, 70):
    yReg.append(steigung * i + offset)
plt.plot(range(10, 70), yReg)
plt.title('Lineare Kennlinie')
plt.show()
yReg.clear()

#Nichtlineare kennlinie durch umkehrung der logarithmirung berechnen
for i in safe:
    yReg.append(m.exp(offset) * m.pow(i, steigung))
plt.ylabel('Abstand [cm]')
plt.xlabel('Spannung [V]')
plt.title('Nichtlineare Kennlinie')
plt.plot(safe, yReg)
plt.show()

path = "D:\\Studium\\SSS\\Labor_1\\DinA4_KurzeSeite.csv"
data = np.genfromtxt(path, delimiter=";", dtype=str, max_rows=4000, 
                         skip_header=1000)
i = 0
while i < len(data):    #alle werte in float umwandeln und dabei mittelwert berechnen
        data[i] = data[i].replace(",", ".")
        av = av + data[i].astype(float)
        i+=1
data = data.astype(float)
kurzAV = np.mean(data)
kurzSTD = np.std(data)
deltakurz = m.exp(offset) * steigung * m.pow(kurzAV, steigung -1) * kurzSTD
messfehlercmkurz68 = kurzAV + 1 * kurzSTD * 2
messfehlercmkurz = kurzAV + 1.95 * kurzSTD * 4
kurz = m.exp(offset) * m.pow(kurzAV, steigung)
relk = messfehlercmkurz / kurzAV
print("\nKurze Seite a68% [cm]: " + str(round(kurz, 5)) + "+- 1 * " + str(round(messfehlercmkurz68, 5)) + 
      "\nKurze Seite a95% [cm]: " + str(round(kurz, 5)) + "+- 1.98 * " + str(round(messfehlercmkurz, 5)))
print("relativer fehler:" + str(round(relk, 5)))


path = "D:\\Studium\\SSS\\Labor_1\\DinA4_LangeSeite.csv"
data = np.genfromtxt(path, delimiter=";", dtype=str, max_rows=4000, 
                         skip_header=1000)
i = 0
while i < len(data):    #alle werte in float umwandeln und dabei mittelwert berechnen
        data[i] = data[i].replace(",", ".")
        av = av + data[i].astype(float)
        i+=1
data = data.astype(float)
langAV = np.mean(data)
langSTD = np.std(data)
deltalang = m.exp(offset) * steigung * m.pow(langAV, steigung -1) * langSTD
 
messfehlercmlang68 = langAV + 1 * langSTD * 2
messfehlercmlang = langAV + 1.95 * langSTD * 4
lang = m.exp(offset) * m.pow(langAV, steigung)
rell = messfehlercmlang / langAV
print("\nLange Seite a68% [cm]: " + str(round(lang, 5)) + " +- 1 * " + str(round(messfehlercmlang68, 5)) + 
      "\nLange Seite a95% [cm]: " + str(round(lang, 5)) + " +- 1.98 * " + str(round(messfehlercmlang, 5)))
print("relativer fehler:" + str(round(rell , 5)))
F = m.sqrt(m.pow(kurz * deltalang, 2) + m.pow(lang * deltakurz, 2))

print("\nFläche a68% [cm]: " + str(round(lang * kurz, 5)) + " +- 1 * " + str(round(messfehlercmkurz68 + messfehlercmlang68, 5)) + 
      "\nFläche a95% [cm]: " + str(round(lang * kurz, 5)) + " +- 1.98 * " + str(round(relk + rell, 5)) + "\n")


print("delta y kurze Seite = e^b * a * x^a-1 * delta x = " + str(round(deltakurz, 5))) 
print("delta y lange Seite = e^b * a * x^a-1 * delta x = " + str(round(deltalang, 5))) 
print("Fehlerpfortflanzung Fläche = sqrt((b * delta a)^2 + (a * delta b)^2) = " + str(round(F, 5)))  
    
    
    