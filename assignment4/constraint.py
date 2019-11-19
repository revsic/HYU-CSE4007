import math
import random

import z3


def naive_constraint(x, num_queens):
    """Naive constraint, from nQueensNaive for performance comparison.
    Args:
        x: List[List[z3.Int]], symbols.
        num_queens: int, the number of queens.
    Returns:
        List[z3.BoolRef], constraints.
    """
    # domain constraint
    domain = [z3.Or(x[row][col] == 0, x[row][col] == 1)
              for col in range(num_queens)
              for row in range(num_queens)]

    # row and column constraint
    row_constraint = [z3.Sum(x[row]) == 1 for row in range(num_queens)]
    col_constraint = [z3.Sum([x[row][col] for row in range(num_queens)]) == 1
                      for col in range(num_queens)]
    # O(N^2) diagonal constraint
    diagonal_constraint = \
        [z3.Implies(z3.And(x[i][j] == 1, x[k][h] == 1, i != k, j != h),
                    abs(k - i) != abs(j - h))
         for i in range(num_queens) for j in range(num_queens)
         for k in range(num_queens) for h in range(num_queens)]
    # sum up
    constraint = domain + row_constraint + col_constraint + diagonal_constraint
    return constraint


def default_constraint(x, num_queens):
    """Default N-Queens constraint.
    Args:
        x: List[List[z3.Int]], symbols.
        num_queens: int, the number of queens.
    Returns:
        List[z3.BoolRef], constrains.
    """
    # domain constraint
    domain = [z3.Or(x[row][col] == 0, x[row][col] == 1)
              for col in range(num_queens)
              for row in range(num_queens)]

    # clusters
    rows = [[] for _ in range(num_queens)]
    cols = [[] for _ in range(num_queens)]
    rightdown = [[] for _ in range(num_queens * 2 - 1)]
    leftdown = [[] for _ in range(num_queens * 2 - 1)]

    # variable clustering
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

    # add constraints to each clusters
    row_const = [z3.Sum(row) == 1 for row in rows]
    col_const = [z3.Sum(col) == 1 for col in cols]
    rightdown_const = [z3.Sum(rd) <= 1 for rd in rightdown]
    leftdown_const = [z3.Sum(ld) <= 1 for ld in leftdown]

    # constraint for the number of queens
    nqueens = [x[i][j] for j in range(num_queens) for i in range(num_queens)]
    nqueens = [z3.Sum(nqueens) == num_queens]

    # sum up
    const = domain                          \
        + nqueens                           \
        + row_const + col_const             \
        + rightdown_const + leftdown_const
    return const


def compute_neighbor(x, y, num_queens):
    """Generate neighbor set.
    Args:
        x: int, x-position.
        y: int, y-position.
        num_queens: int, the number of queens.
    Returns:
        List[Tuple[int, int]], coordinates set of neighbors.
    """
    # all neighbor
    # deltas = [[-1, -1], [ 0, -1], [ 1, -1],
    #           [-1,  0],           [ 1,  0],
    #           [-1,  1], [ 0,  1], [ 1,  1]]

    # horizontal-vertical neighbor
    deltas = [          [ 0, -1],
              [-1,  0],           [ 1,  0],
                        [ 0,  1]]

    # diagonal neighbor
    # deltas = [[-1, -1], [ 1, -1],
    #           [-1,  1], [ 1,  1]]

    neighbor = []
    for dx, dy in deltas:
        nx = x + dx
        ny = y + dy
        # coordinate validation
        if 0 <= nx and nx < num_queens and 0 <= ny and ny < num_queens:
            neighbor.append([nx, ny])
    return neighbor


def skipped_neighbor(size):
    """Generate skipped neighbor set.
    Args:
        size: int, number of neighbors to skip.
    Returns:
        Callable, neighbor generator.
    """
    def compute(x, y, num_queens):
        # horizontal-vertical neighbor
        deltas = [          [ 0, -1],
                  [-1,  0],           [ 1,  0],
                            [ 0,  1]]
        neighbor = []
        for dx, dy in deltas:
            for s in range(1, size + 1):
                nx = x + dx * s
                ny = y + dy * s
                # range validation
                if 0 <= nx and nx < num_queens and 0 <= ny and ny < num_queens:
                    neighbor.append([nx, ny])
        return neighbor
    return compute


def neighbor_heuristic(x, num_queens, neighbor_fn):
    """Deterministic neighbor heuristic (in-and version).
    Args:
        x: List[List[z3.Int]], symbols.
        num_queens: int, the number of queens.
        neighbor_fn: Callable, neighbor generator.
    Returns:
        List[z3.BoolRef], constraints.
    """
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
    """Stochastic neighbor heuristic (in-and version).
    Args:
        x: List[List[z3.Int]], symbols.
        num_queens: int, the number of queens.
        neighbor_fn: Callable, neighbor generator.
    Returns:
        List[z3.BoolRef], constraints.
    """
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
    """Deprecated, do not use heuristic.
    """
    return []


def custom_constraint(x, num_queens):
    """Customized constraint. 
    Args:
        x: List[List[z3.Int]], symbols.
        num_queens: int, the number of queens.
    Returns:
        List[z3.BoolRef], constraints.
    """
    return default_constraint(x, num_queens) + heuristic(x, num_queens)
