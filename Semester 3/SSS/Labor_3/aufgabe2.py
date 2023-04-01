import matplotlib.pyplot as plt
import numpy as np

#Phasenverschiebung der Lautsprecher, Großer = Linker Lautsprecher, Kleiner = Rechter Lautsprecher
phaseGR = [1044, 363.5, 198.5, 40.19, 21.16, 18.32, 10.11, 6.31, 4.35, 3, 3.439, 1.76, 1.31, 0.79, 0.61, 0.27]
phaseKL = [1694, 438.3, 145.6, 92.62, 21.55, 15.19, 8.58, 6.16, 3.45, 4.06, 2.02, 2.15, 0.68, 0.93, 0.33, 0.4]

frequenzen = ["100", "200", "300", "400", "700", "850", "1000",
         "1200", "1500", "1700", "2000", "3000", "4000", "5000",
         "6000", "10000"]
amplitudeKL = []
amplitudeGR = []
for a in range(0, 16):
    part = float(frequenzen[a])
    part = 100. /  part
    part = part * 892000
    # Linke Spalte Frequenzgenerator, rechte Spalte Lautsprecher
    # Einlesen der kleinen Dateien - dabei wird die linke Spalte als x und die rechte als y definiert
    x, y = np.loadtxt('messungen/' + frequenzen[a] + 'R.csv', delimiter=';', unpack=True)
    x = x[:int(part)]
    y = y[:int(part)]
    # Einlesen der großen Dateien - dabei wird die linke Spalte als u und die rechte als v definiert
    u, v = np.loadtxt('messungen/' + frequenzen[a] + 'L.csv', delimiter=';', unpack=True)
    u = u[:int(part)]
    v = v[:int(part)]
    
    # Die einzelnen .csv Dateien des kleinen Lautsprechers werden in jeweils einem Plot dargestellt
    plt.plot(y, 'y', label="Lautsprecher")
    plt.plot(x, 'b', label="Frequenzgenerator")
    plt.title(frequenzen[a] + "R")
    plt.ylabel('Spannung [V]')
    plt.xlabel('frequenzen  [ns]')
    plt.grid(True)
    plt.legend()
    loc, lab = plt.xticks()
    e = len(loc) - 1
    newLabels = []
    for x in loc:
        newLabels.append(round(x * 0.000056, 1))
    plt.xticks(loc[1:e], newLabels[1:e])
    plt.xlim(xmin=0)
    plt.savefig('plots/' + str(frequenzen[a]) + 'R.png')
    plt.show()
    
    # Die einzelnen .csv Dateien des großen Lautsprechers werden in jeweils einem Plot dargestellt
    plt.plot(v,'y', label="Lautsprecher")
    plt.plot(u,'b', label="Frequenzgenerator")
    plt.title(frequenzen[a] + "L")
    plt.ylabel('Spannung [V]')
    plt.xlabel('frequenzen  [ns]')
    plt.grid(True)
    plt.legend()
    loc, lab = plt.xticks()
    newLabels = []
    for x in loc:
        newLabels.append(round(x * 0.000056, 1))
    plt.xticks(loc[1:e], newLabels[1:e])
    plt.xlim(xmin=0)
    plt.savefig('plots/' + str(frequenzen[a]) + 'L.png')
    plt.show()

    print("---------------------------")
    # Der maximale Amplitudenwert des kleinen Lautsprechers für die jeweilige Frequenz
    print("Amplitude L:", frequenzen[a], np.max(np.abs(y)))
    amplitudeKL.append(np.max(np.abs(y)))
    # Der maximale Amplitudenwert des großen Lautsprechers für die jeweilige Frequenz
    print("Amplitude R:", frequenzen[a], np.max(np.abs(v)))
    amplitudeGR.append(np.max(np.abs(v)))
    
fr = ["100", "300", "700", "1000", "1700", "3000", "5000", "10000"]
plt.plot(frequenzen, amplitudeKL, "b", label="Kleiner Lautsprecher")
plt.plot(frequenzen, amplitudeGR, "y", label="Großer Lautsprecher")
plt.title("Amplitudengang")
plt.ylabel('Amplitude [V]')
plt.xlabel('Frequenz [Hz]')
plt.savefig('plots/AmpFreq.png')
plt.legend()
plt.xticks(fr)
plt.show()

plt.plot(frequenzen, phaseKL, "b", label="Kleiner Lautsprecher")
plt.plot(frequenzen, phaseGR, "y", label="Großer Lautsprecher")
plt.title("Phasengang")
plt.ylabel('Phasenverschiebung [ns]')
plt.xlabel('Frequenz [Hz]')
plt.savefig('plots/PhaseFreq.png')
plt.legend()
plt.xticks(fr)
plt.show()

# for Schleife zum Berechnen des Bode Diagramms
for a in range(0, 16):
    # 20 log10 zur Berechnung des Bode-Diagramms für den großen Lautsprecher
    amplitudeGR[a] = 1 / amplitudeGR[a]
    amplitudeGR[a] = 20 * np.log10(amplitudeGR[a])
    # 20 log10 zur Berechnung des Bode-Diagramms für den großen Lautsprecher
    amplitudeKL[a] = 1 / amplitudeKL[a]
    amplitudeKL[a] = 20 * np.log10(amplitudeKL[a])
    # Berechnung des Phasenwinkels mit −∆t * f * 360 für den großen Lautsprecher
    phaseGR[a] = (phaseGR[a] * -1) * int(frequenzen[a]) * 360
    # Berechnung des Phasenwinkels mit −∆t * f * 360 für den kleinen Lautsprecher
    phaseKL[a] = (phaseKL[a] * -1) * int(frequenzen[a]) * 360

# Darstellung des Phasengangs für beide Lautsprecher im Verhältnis zur Frequenz
plt.plot(frequenzen, amplitudeKL, 'b', label='Kleiner Lautsprecher')
plt.plot(frequenzen, amplitudeGR, 'y', label='Grosser Lautsprecher')
plt.title("Bode-Amplitude")
plt.ylabel('Amplitude in V')
plt.xlabel('Frequenz f in Hz')
plt.grid(True)
plt.semilogx()
plt.legend()
plt.savefig('plots/bodeamplitudengang.png')
plt.show()

# Darstellung des Phasengangs für beide Lautsprecher im Verhältnis zur Frequenz
plt.plot(frequenzen, phaseKL, 'b', label='Kleiner Lautsprecher')
plt.plot(frequenzen, phaseGR, 'y', label='Grosser Lautsprecher')
plt.title("Bode-Phasenverschiebung")
plt.ylabel('Phasenverschiebung in µs')
plt.xlabel('Frequenz f in Hz')
plt.grid(True)
plt.semilogx()
plt.legend()
plt.savefig('plots/bodephasengang.png')
plt.show()