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
