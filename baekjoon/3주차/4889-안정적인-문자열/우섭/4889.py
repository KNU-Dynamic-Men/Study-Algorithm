from sys import stdin
import math

num = 0
while (True):
    num += 1
    com = list(stdin.readline().rstrip())
    if '-' in com[0]:
        break
    count = 0
    st = []
    for i in com:
        if i == '{':
            st.append('{')
        else:
            if st:
                st.pop()
            else:
                st.append('{')
                count += 1
    count += math.ceil(len(st) / 2)
    print('{0}. {1}'.format(num, count))
