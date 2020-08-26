import sys

def printer(card, is_reverse):
    if card:
        if not is_reverse:
            tmp = '['
            for i in card[:-1]:
                tmp += (str(i) + ',')
            tmp += (str(card[-1]) + ']')
        else:
            tmp = '['
            for i in reversed(card[1:len(card)]):
                tmp += (str(i) + ',')
            tmp += (str(card[0]) + ']')
    else:
        tmp = '[]'
    print(tmp)

n = int(sys.stdin.readline())
for _ in range(n):
    command = list(sys.stdin.readline().strip('\n'))
    length = int(sys.stdin.readline())
    card = sys.stdin.readline().lstrip('[').rstrip('\n').rstrip(']').split(',')
    if card[0] == '':
        card.pop()
    is_reverse = False
    is_end = True
    for i in command:
        if i == 'R':
            is_reverse = not is_reverse
        elif i == 'D':
            if card:
                if is_reverse:
                    card.pop(-1)
                else:
                    card.pop(0)
            else:
                is_end = False
                print('error')
                break
    if is_end:
        printer(card, is_reverse)
