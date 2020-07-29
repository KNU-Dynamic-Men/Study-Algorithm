import sys, collections

(rows, cols) = map(int, sys.stdin.readline().rstrip().split(" "))

maze_body = []
for _ in range(rows):
    converted_list = map(int, list(sys.stdin.readline().rstrip()))
    maze_body.append(list(converted_list))
index_body = [[0] * cols for _ in range(rows)]

traverse_queue = collections.deque()
traverse_queue.append([0, 0])
index_body[0][0] = 1

done = False
while traverse_queue and not done:
    row, col = traverse_queue.popleft()
    for rd, cd in [(-1, 0), (0, -1), (1, 0), (0, 1)]:
        nrow, ncol = row + rd, col + cd
        if 0 <= nrow < rows and 0 <= ncol < cols \
            and index_body[nrow][ncol] == 0 and maze_body[nrow][ncol] == 1:
            index_body[nrow][ncol] = index_body[row][col] + 1

            if nrow == rows - 1 and ncol == cols - 1:
                print(index_body[nrow][ncol])
                done = True
                break
            traverse_queue.append([nrow, ncol])
    # print(index_body)
    # print(traverse_queue)
# while 0 != len(traverse_queue):
#     row, col = traverse_queue.popleft()  # Dequeue(popleft) element from queue
#     maze_body[row][col] = 0  # Set current maze location `Not Iterable`

#     # Break if end criteria met
#     if row == rows - 1 and col == cols - 1:
#         print(distance)
#         break

#     # Insert every direction to queue
#     for rd, cd in [(-1, 0), (0, -1), (1, 0), (0, 1)]:
#         nrow, ncol = row + rd, col + cd
#         if 0 <= nrow < rows and 0 <= ncol < cols and maze_body[nrow][ncol] == 1:
#             traverse_queue.append([nrow, ncol])
#     print(traverse_queue)