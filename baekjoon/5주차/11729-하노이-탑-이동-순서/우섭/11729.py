from sys import stdin

tower = 6
def hanoi(a, b, k):
    if k == 1:
        print("{} {}".format(a, b))
        return
    
    hanoi(a, tower-a-b, k-1)
    print("{} {}".format(a, b))
    hanoi(tower-a-b, b, k-1)

getN = int(stdin.readline())
print(2**getN-1)
hanoi(1, 3, getN)
