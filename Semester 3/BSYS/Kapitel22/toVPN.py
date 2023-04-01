file = open('./valgrind.txt', 'r')
vpnFile = open('./vpn.txt', 'w')

for line in file:
    if not line.startswith('='):
        vpnFile.write(str(int(line[3:11], 16) >> 12) + "\n")

file.close()
vpnFile.close()
