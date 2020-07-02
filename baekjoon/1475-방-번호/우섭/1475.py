import math

table = [0 ,0, 0, 0, 0, 0, 0, 0, 0]
value = list(map(int, str(input())))

for i in value:
    if i == 6 or i == 9:
        table[6] += 0.5
    else:
        table[i] += 1

table[6] = math.ceil(table[6])
print(max(table))