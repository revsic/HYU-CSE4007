import sys
import time

import z3


def timer(func):
    def decorated(*args, **kwargs):
        start = time.time()
        retn = func(*args, **kwargs)
        return retn, time.time() - start
    return decorated


@timer
def solve(num_queens):
    pass


def main(_):
    num_queens = input('N: ')
    result, time = solve(num_queens)

    print(result)
    print('elapsed time: {} sec'.format(time))
    return 0

if __name__ == '__main__':
    retn = main(sys.argv)
    sys.exit(retn)
