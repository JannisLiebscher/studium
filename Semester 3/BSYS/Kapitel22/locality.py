from random import randint

for x in range(100):
    if (randint(0,10) <= 8):
        print(randint(0,4), end=",")
        continue
    print(randint(5,20), end=",")
