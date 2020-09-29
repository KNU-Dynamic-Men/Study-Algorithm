import requests, time
from collections import defaultdict

url = 'http://localhost:8000'


class Elevator:
    elevator_capacity = 8
    def __init__(self, height, id, floor=1, passengers=[], status='STOPPED', elevator_capacity=8):
        self.id = id
        self.floor = floor
        self.passengers = passengers
        self.cache = []
        self.status = status
        self.destination = [0 for _ in range(height+1)]
        self.total_destination = 0
        self.elevator_capacity = elevator_capacity
        self.is_up = True
        self.cmd = "STOP"
        self.exit_call_list, self.exit_id_list = [], []
        self.rest_list = []
        self.enter_call_list, self.enter_id_list = [], []
        self.exit_or_enter_list = []


    def set_action(self, building):
        if self.status in ('UPWARD', 'DOWNWARD'):
            tmp = []
            for call in self.passengers:
                if call['end'] != self.floor:
                    tmp.append(call)
            if ((self.total_destination == 0 or self.destination[self.floor] != 0) and
                len(tmp) < self.elevator_capacity) or \
                    self.floor in (0, 25):
                self.status = 'STOPPED'
                self.cmd = 'STOP'
            else:
                if self.status == 'UPWARD':
                    self.floor += 1
                elif self.status == 'DOWNWARD':
                    self.floor -= 1
        elif self.status == 'STOPPED':
            for call in self.passengers:
                if call['end'] == self.floor:
                    self.exit_call_list.append(call)
                    self.exit_id_list.append(call['id'])
                else:
                    self.rest_list.append(call)
            rest_cache = []
            for call in self.cache:
                if call['start'] == self.floor and \
                        len(self.rest_list) + len(self.enter_call_list) < self.elevator_capacity:
                    self.enter_call_list.append(call)
                    self.enter_id_list.append(call['id'])
                    self.destination[call['end']] += 1
                    self.total_destination += 1
                else:
                    rest_cache.append(call)
            self.passengers = self.rest_list+self.enter_call_list if self.enter_call_list else self.rest_list
            self.rest_list = []
            if self.exit_call_list or self.enter_call_list:
                self.destination[self.floor] -= len(self.exit_call_list)
                self.total_destination -= len(self.exit_call_list)
                self.cache = rest_cache
                self.status = 'OPENED'
                self.cmd = 'OPEN'
            elif self.total_destination != 0:
                to_up = False
                to_down = False
                for i, floor in enumerate(self.destination):
                    if floor > 0 and self.floor < i:
                        to_up = True
                    elif floor > 0 and self.floor > i:
                        to_down = True
                if to_up and (self.is_up or (not self.is_up and to_up)):
                    self.is_up = True
                    self.floor += 1
                    self.status = 'UPWARD'
                    self.cmd = "UP"
                elif to_down and (not self.is_up or (self.is_up and to_down)):
                    self.is_up = False
                    self.floor -= 1
                    self.status = 'DOWNWARD'
                    self.cmd = "DOWN"
        elif self.status == 'OPENED':
            if self.exit_id_list:
                self.cmd = 'EXIT'
                self.exit_or_enter_list = self.exit_id_list
                self.exit_call_list, self.exit_id_list = [], []
            else:
                if self.enter_call_list:
                    for call in self.enter_call_list:
                        self.destination[call['start']] += 1
                        self.total_destination += 1
                    self.cmd = 'ENTER'
                    self.exit_or_enter_list = self.enter_id_list
                    self.enter_call_list, self.enter_id_list = [], []
                else:
                    self.status = 'STOPPED'
                    self.cmd = 'CLOSE'
        return self.cmd, self.exit_or_enter_list


    def allocate_passenger(self, call):
        self.cache.append(call)
        self.destination[call['start']] += 1
        self.total_destination += 1


def oncalls(token):
    uri = url + '/oncalls'
    return requests.get(uri, headers={'X-Auth-Token': token}).json()


def action(token, cmds):
    uri = url + '/action'
    return requests.post(uri, headers={'X-Auth-Token': token}, json={'commands': cmds}).json()


def find_nearby_elevator(elevator_capacity, elevator_list, floor, end):
    dist = 100
    elevator_idx = -1
    for i, elevator in enumerate(elevator_list):
        if (elevator.status in ('UP', 'STOPPED') and
                len(elevator.passengers) < elevator_capacity and
                elevator.floor <= floor < end):
            d = abs(floor - elevator.floor)
            if (d == dist and elevator.status == 'STOPPED') or d < dist:
                dist = d
                elevator_idx = i
        elif (elevator.status in ('DOWN', 'STOPPED') and
              len(elevator.passengers) < elevator_capacity and
              end < floor <= elevator.floor):
            d = abs(floor - elevator.floor)
            if (d == dist and elevator.status == 'STOPPED') or d < dist:
                dist = d
                elevator_idx = i
    return elevator_idx, dist


def run(token, building_height, elevator_num, elevator_capacity, call_num):
    elevator_list = [Elevator(building_height, i, elevator_capacity=elevator_capacity) for i in range(elevator_num)]
    building = [[] for _ in range(building_height + 1)]
    new_calls = [0]*call_num
    cache_dic = defaultdict(list)

    while True:
        oncall_dic = oncalls(token)
        print(oncall_dic)
        if oncall_dic['is_end']:
            break
        elevator_server_list = oncall_dic['elevators']
        call_server_list = oncall_dic['calls']

        for call in call_server_list:
            if new_calls[call['id']] == 0:
                elevator_idx, dist = find_nearby_elevator(elevator_capacity, elevator_list, call['start'], call['end'])
                elevator_list[elevator_idx].allocate_passenger(call)
                new_calls[call['id']] = 1

        commands = []
        for elevator in elevator_list:
            cmd, exit_or_enter_list = elevator.set_action(building)
            if cmd in ('ENTER', 'EXIT'):
                commands.append({
                    "elevator_id": elevator.id,
                    "command": cmd,
                    "call_ids": exit_or_enter_list
                })
            else:
                commands.append({
                    "elevator_id": elevator.id,
                    "command": cmd
                })

        print(commands)
        print(action(token, commands))


def start(user, problem, count):
    uri = url + '/start' + '/' + user + '/' + str(problem) + '/' + str(count)
    return requests.post(uri).json()


if __name__ == '__main__':
    # problem 1
    token = 'wUkwb'
    building_height = 25
    count = 1
    elevator_capacity = 8
    call_num = 200
    token = start('tester', 1, count)['token']
    run(token, building_height, count, elevator_capacity, call_num)
