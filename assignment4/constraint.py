import math
import random

import z3


def naive_constraint(x, num_queens):
    domain = [z3.Or(x[row][col] == 0, x[row][col] == 1)
              for col in range(num_queens)
              for row in range(num_queens)]

    row_constraint = [z3.Sum(x[row]) == 1 for row in range(num_queens)]
    col_constraint = [z3.Sum([x[row][col] for row in range(num_queens)]) == 1
                      for col in range(num_queens)]
    diagonal_constraint = \
        [z3.Implies(z3.And(x[i][j] == 1, x[k][h] == 1, i != k, j != h),
                    abs(k - i) != abs(j - h))
         for i in range(num_queens) for j in range(num_queens)
         for k in range(num_queens) for h in range(num_queens)]

    constraint = domain + row_constraint + col_constraint + diagonal_constraint
    return constraint


def default_constraint(x, num_queens):
    domain = [z3.Or(x[row][col] == 0, x[row][col] == 1)
              for col in range(num_queens)
              for row in range(num_queens)]

    rows = [[] for _ in range(num_queens)]
    cols = [[] for _ in range(num_queens)]
    rightdown = [[] for _ in range(num_queens * 2 - 1)]
    leftdown = [[] for _ in range(num_queens * 2 - 1)]

    for i in range(num_queens):
        for j in range(num_queens):
            var = x[i][j]
            rows[i].append(x[i][j])
            cols[j].append(x[i][j])

            if i >= j:
                rightdown[i - j].append(x[i][j])
            else:
                rightdown[num_queens + j - i - 1].append(x[i][j])

            if num_queens - i - 1 >= j:
                leftdown[num_queens - i - j - 1].append(x[i][j])
            else:
                leftdown[i + j].append(x[i][j])

    row_const = [z3.Sum(row) == 1 for row in rows]
    col_const = [z3.Sum(col) == 1 for col in cols]
    rightdown_const = [z3.Sum(rd) <= 1 for rd in rightdown]
    leftdown_const = [z3.Sum(ld) <= 1 for ld in leftdown]

    nqueens = [x[i][j] for j in range(num_queens) for i in range(num_queens)]
    nqueens = [z3.Sum(nqueens) == num_queens]

    const = domain                          \
        + nqueens                           \
        + row_const + col_const             \
        + rightdown_const + leftdown_const
    return const


def compute_neighbor(x, y, num_queens):
    # deltas = [[-1, -1], [ 0, -1], [ 1, -1],
    #           [-1,  0],           [ 1,  0],
    #           [-1,  1], [ 0,  1], [ 1,  1]]
    deltas = [          [ 0, -1],
              [-1,  0],           [ 1,  0],
                        [ 0,  1]]
    # deltas = [[-1, -1], [ 1, -1],
    #           [-1,  1], [ 1,  1]]
    neighbor = []
    for dx, dy in deltas:
        nx = x + dx
        ny = y + dy
        if 0 <= nx and nx < num_queens and 0 <= ny and ny < num_queens:
            neighbor.append([nx, ny])
    return neighbor


def skipped_neighbor(size):
    def compute(x, y, num_queens):
        deltas = [          [ 0, -1],
                  [-1,  0],           [ 1,  0],
                            [ 0,  1]]
        neighbor = []
        for dx, dy in deltas:
            for s in range(1, size + 1):
                nx = x + dx * s
                ny = y + dy * s
                if 0 <= nx and nx < num_queens and 0 <= ny and ny < num_queens:
                    neighbor.append([nx, ny])
        return neighbor
    return compute


def neighbor_heuristic(x, num_queens, neighbor_fn):
    const = []
    for i in range(num_queens):
        for j in range(num_queens):
            const.append(
                z3.Implies(
                    x[i][j] == 1,
                    z3.And([x[nx][ny] == 0
                            for nx, ny in neighbor_fn(i, j, num_queens)])))
    return const


def random_neighbor_heuristic(x, num_queens, neighbor_fn):
    const = []
    for i in range(num_queens):
        px = random.randint(0, num_queens - 1)
        py = random.randint(0, num_queens - 1)
        const.append(
            z3.Implies(
                x[px][py] == 1,
                z3.And([x[nx][ny] == 0
                        for nx, ny in neighbor_fn(px, py, num_queens)])))
    return const


def heuristic(x, num_queens):
    return []


def custom_constraint(x, num_queens):
    return default_constraint(x, num_queens) + heuristic(x, num_queens)
