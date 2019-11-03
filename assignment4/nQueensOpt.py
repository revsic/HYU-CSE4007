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
def solve(num_queens, const_fn):
    x = [[z3.Int(f'x_{row}_{col}')
         for col in range(num_queens)]
         for row in range(num_queens)]
    const = const_fn(x, num_queens)

    s = z3.Solver()
    s.add(const)

    if s.check() == z3.sat:
        m = s.model()
        r = [[j + 1 for j in range(num_queens) if m.evaluate(x[i][j]) == 1][0]
             for i in range(num_queens)]
        return r

    return []


def main(argv):
    num_queens = int(input('N: '))

    if len(argv) > 1 and argv[1] == 'naive':
        const_fn = constraint.naive_constraint
    elif len(argv) > 1 and argv[1] == 'default':
        const_fn = constraint.default_constraint
    else:
        const_fn = constraint.custom_constraint
    
    result, time = solve(num_queens, const_fn)

    print(result)
    print('elapsed time: {} sec'.format(time))
    return 0

if __name__ == '__main__':
    retn = main(sys.argv)
    sys.exit(retn)
