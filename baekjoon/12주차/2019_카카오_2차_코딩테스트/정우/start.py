import requests

url = 'http://localhost:8000'

def start(user, problem, count):
    uri = url + '/start' + '/' + user + '/' + str(problem) + '/' + str(count)
    return requests.post(uri).json()

if __name__ == '__main__':
    user = 'tester'

    # problem 0
    problem = 0
    count = 2
    print(start(user, problem, count))
