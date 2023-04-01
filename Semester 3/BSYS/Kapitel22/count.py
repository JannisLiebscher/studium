import matplotlib.pyplot as plt
file = open('./vpn.txt', 'r')
ar = [ ]
for num in file:
    ar.append(num)
file.close()
#dictionary with VPN as key and count as value
d = dict.fromkeys(set(ar), 0)
for x in d:
    d[x] = ar.count(x)
#sort pagenumbers by acces
pages = sorted(d, key=d.get)
count = []
for w in pages:
    count.append(d[w])
pageCount = str(len(d))

#get ratio
ges = 0
for x in count:
    ges += x
lastSeven = 0
for x in count[len(count) - 7:len(count) + 1]:
    lastSeven += x

#use 17 most used pages to plot
pages = pages[270:len(pages)]
count = count[270:len(count)]

plt.figure(figsize=(16, 12))
plt.xticks(rotation = 45)
plt.text(0, 90000,'Die letzten 7 Pages beinhalten ' + 
         str(format(lastSeven, ',d').replace(",", " ")) + 
         ' von insgesamt ' + 
         str(format(ges, ',d').replace(",", " ")) +
         ' Zugriffen (' + str(round(((lastSeven/ges) * 100), 2)) + "%)", fontsize=15)

clrs = []
for x in range (0, (len(count))):
    if(x > len(count) - 8 ):
        clrs.append("green")
    else:
        clrs.append("black")

plt.bar(pages, count, color=clrs)
plt.xlabel("VPN", fontsize=12)
plt.ylabel("Anzahl Zugriffe", fontsize=12)
plt.title("Zugriffe der ersten 17 von insgesamt " + pageCount + 
          " Pages, nach menge der Zugriffe", fontsize=20)

plt.savefig("accesPerPage.png")
plt.show()
