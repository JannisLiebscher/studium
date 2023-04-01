import matplotlib.pyplot as plt

opt =       [49.87, 86.00, 93.10, 95.57, 96.77, 97.34, 97.71]
fifo =      [49.87, 79.88, 88.46, 92.26, 94.21, 95.11, 95.80]
lru =       [49.87, 85.68, 90.79, 93.96, 95.36, 96.12, 96.74]
random =    [49.87, 79.55, 87.58, 91.45, 93.63, 94.63, 95.34]
clock =     [49.87, 80.25, 88.51, 92.37, 94.41, 95.29, 95.96]
clock2 =    [49.87, 81.40, 89.21, 92.84, 94.72, 95.59, 96.19]
clock5 =    [49.87, 82.97, 89.89, 93.35, 95.04, 95.84, 96.38]
clock8 =    [49.87, 83.24, 89.87, 93.33, 95.01, 95.81, 96.36]


loop = [1, 2, 3, 4, 5, 6, 7]
#all policies
plt.figure(figsize=(16, 10))
plt.xlabel("Cache Block size")
plt.ylabel("Hit Rate")
plt.title("Page Hitrate für verschiedene Policies und Cachegrößen")

plt.plot(loop, opt, "k", label='OPT')
plt.plot(loop, fifo, "b", label='FIFO')
plt.plot(loop, lru, "r", label='LRU')
plt.plot(loop, random, "y", label='RANDOM')
plt.plot(loop, clock5, "g", label='CLOCK 5b')
plt.legend(loc='best')
plt.show()
#different CLOCK policies
plt.figure(figsize=(16, 10))
plt.xlabel("Cache Block size")
plt.ylabel("Hit Rate")
plt.title("Page Hitrate CLOCK policy mit variablen clock-bits")

plt.plot(loop, opt, "k", label='OPT')
plt.plot(loop, clock, "b", label='CLOCK')
plt.plot(loop, clock2, "r", label='CLOCK 2b')
plt.plot(loop, clock5, "g", label='CLOCK 5b')
plt.plot(loop, clock8, "m", label='CLOCK 8b')
plt.legend(loc='best')
plt.show()