from z3 import *
import time

# Number of Queens
print("N: ")
N = int(input())

start = time.time()
# Variables
X = [[Int("x_%s_%s" % (row, col)) for row in range(N)] for col in range(N) ]

# Constraints
domain = [Or(X[row][col] == 0, X[row][col] == 1) for row in range(N) for col in range(N) ]

rowConst = [Sum(X[row]) == 1 for row in range(N)]

colConst = [Sum([X[row][col] for row in range(N)]) == 1 for col in range(N) ]

digConst = [Implies(And(X[i][j] == 1, X[k][h] == 1,
            i != k, j != h), abs(k - i) != abs(j - h))
            for i in range(N) for j in range(N) 
            for k in range(N) for h in range(N)]

eight_queens_c = domain + rowConst + colConst + digConst

s = Solver()
s.add(eight_queens_c)

if s.check() == sat:
    m = s.model()
    r = [[m.evaluate(X[i][j]) for j in range(N)] for i in range(N)]
    print_matrix(r)

print("elapsed time: ", time.time() - start, " sec")

