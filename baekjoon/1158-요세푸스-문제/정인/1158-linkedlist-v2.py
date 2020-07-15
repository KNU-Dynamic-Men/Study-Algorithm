import sys

class Node(object):
    def __init__(self, item):
        self.item = item
        self.next = '\0'
        
class LinkedList(object):

    def __init__(self):
        super()
        self.root = '\0'
        self.end = '\0'
        self.cursor = self.root

    def remove(self):
        if self.root == '\0' or self.end == '\0':
            pass
        elif self.cursor != '\0':
            instance_cursor = self.root
            while instance_cursor != '\0' and instance_cursor.next != self.cursor:
                instance_cursor = instance_cursor.next
            
            if instance_cursor != '\0':
                removed_item_value = instance_cursor.next.item
                if instance_cursor.next == self.root:
                    self.root = instance_cursor.next.next
                instance_cursor.next = instance_cursor.next.next
                return removed_item_value
        return False

    def insert_last(self, item):
        new_node = Node(item)
        new_node.next = self.root

        if self.end != '\0':
            self.end.next = new_node
            self.end = self.end.next
        else:
            self.root = new_node
            self.end = new_node

    def insert_first(self, item):
        new_node = Node(item)
        if self.root != '\0':
            new_node.next = self.root
        else:
            new_node.next = new_node
            self.end = new_node
        self.root = new_node
        self.end.next = new_node
    
    def init(self):
        self.cursor = Node('\0')
        self.cursor.next = self.root

    def next(self):
        if self.cursor.next != '\0':
            self.cursor = self.cursor.next
            return self.cursor.item
        else:
            return False
    
    # def __str__(self):
    #     instance_cursor = self.root
    #     item_list = []
    #     item_list.append('<')
    #     while instance_cursor != self.end:
    #         item_list.append(str(instance_cursor.item))
    #         if instance_cursor.next != self.root:
    #             item_list.append(', ')
    #         instance_cursor = instance_cursor.next
    #     if instance_cursor != '\0': item_list.append(str(instance_cursor.item))
    #     item_list.append('>')

    #     return ''.join(item_list)

    # def __len__(self):
    #     if self.root == '\0': return 0
    #     instance_cursor = self.root
    #     count = 1
    #     while instance_cursor.next != self.root:
    #         count += 1
    #         instance_cursor = instance_cursor.next

    #     return count

[people_num, kth_removal] = [int(s) for s in sys.stdin.readline().rstrip().split(" ")]
people_list = LinkedList()
josepus_list = []

for people_id in range(people_num, 0, -1):
    people_list.insert_first(people_id)

people_list.init()
for _ in range(people_num):
    for _r in range(kth_removal):
        people_list.next()
    josepus_list.append(people_list.remove())

print(josepus_list.__str__().replace("[", "<").replace("]", ">"))