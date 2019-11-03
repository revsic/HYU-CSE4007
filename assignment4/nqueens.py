import sys
import time

import z3

import constraint


def timer(func):
    def decorated(*args, **kwargs):
        start = time.time()
        retn = func(*args, **kwargs)
        return retn, time.time() - start
    return decorated


@timer
def solve(num_queens):
    x = [[z3.Int(f'x_{row}_{col}') for col in range(num_queens)]
         for row in range(num_queens)]

    const = constraint.naive_constraint(x, num_queens)

    s = z3.Solver()
    s.add(const)

    if s.check() == z3.sat:
        m = s.model()
        r = [[j + 1 for j in range(num_queens) if m.evaluate(x[i][j]) == 1][0]
             for i in range(num_queens)]
        return r

    return []


def main(_):
    num_queens = int(input('N: '))
    result, time = solve(num_queens)

    print(result)
    print('elapsed time: {} sec'.format(time))
    return 0

if __name__ == '__main__':
    retn = main(sys.argv)
    sys.exit(retn)
