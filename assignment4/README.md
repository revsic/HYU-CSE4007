# Assignment4: SAT Solver.
Solve the N-Queens problem with SAT solver.

## Environments
Tested environments
- python 3.7.3
- z3-solver 4.5.1.0.post2
- Windows 10

Suggested environments
- Python3
- z3-solver 4.5.1.0.post2

## Usage
To run solver
```
PS assignment4> python .\nQueensOpt.py
N: 20
[7, 9, 11, 2, 14, 3, 18, 8, 13, 5, 10, 1, 16, 6, 20, 17, 19, 12, 15, 4]
elapsed time: 12.4160315990448 sec
```

## Sample result

Mahcine Environments
- Intel(R) Core(TM) i7-8700 CPU @ 3.20GHz
- 32GB Memory
- Samsung SSD 850 PRO 256GB

naive: 105sec
```
N: 20
[17, 4, 11, 8, 16, 19, 1, 5, 15, 10, 12, 3, 18, 13, 2, 6, 20, 14, 7, 9]
elapsed time: 105.02632117271423 sec
```
optimized: 12sec (10x speed)
```
N: 20
[7, 9, 11, 2, 14, 3, 18, 8, 13, 5, 10, 1, 16, 6, 20, 17, 19, 12, 15, 4]
elapsed time: 12.4160315990448 sec
```

## Preface: SAT Solver

Satisfiability solver, 이하 SAT solver는 주어진 logical statements를 모두 만족시키는 Model을 구하는 알고리즘, 혹은 그 구현체이다. Model을 구성할 각 심볼과 해당 심볼로 구성된 logical statements를 정의하면, SAT Solver은 해당 심볼에 값을 대입해 가며 충족 가능성을 연산한다.

N-Queens에서는 NxN Boolean Matrix를 통해 Queen의 위치를 나타내고, constraint와 여러 heuristic을 logical statements로 표현한다. SAT solver가 모든 문장을 만족시키는 해를 찾으면, 그 해가 N-Queens problem의 정해가 된다.

## Naive Method

미리 주어진 풀이이다. Symbol과 Constraint에 관련된 정의만 존재하고, 추가 heuristic은 존재하지 않는다.

```
N: 20
[17, 4, 11, 8, 16, 19, 1, 5, 15, 10, 12, 3, 18, 13, 2, 6, 20, 14, 7, 9]
elapsed time: 105.02632117271423 sec
```

### 1. Symbol representation

NxN Integer Matrix를 상정한다. 이는 z3에서 지원하는 Sum operatoion을 이용하기 위함으로 보이며, 값을 0과 1로 한정되어야 하는 부분은 추후 Constraint에서 보충한다.
```py
x = [[z3.Int(f'x_{row}_{col}')
     for col in range(num_queens)]
     for row in range(num_queens)]
```

### 2. Constraint representation

1. domain constraint

NxN matrix에서 각 cell은 퀸의 존재 여부를 나타내므로 값을 0 혹은 1로 한정한다.
```py
domain = [z3.Or(x[row][col] == 0, x[row][col] == 1)
          for col in range(num_queens)
          for row in range(num_queens)]
```

2. row, column constraint

각 행과 열에는 단 하나의 퀸만이 존재한다. naive에서는 이를 각 행과 열에 주어진 수의 합이 1임을 상정했다.
```py
row_constraint = [z3.Sum(x[row]) == 1 for row in range(num_queens)]
col_constraint = [z3.Sum([x[row][col] for row in range(num_queens)]) == 1
                  for col in range(num_queens)]
```

3. diagonal constraint

각 대각선에 존재하는 퀸의 수는 하나 이하이다. naive에서는 모든 매트릭스를 순회하면 주어진 두 퀸에 대해서 좌표값의 차, 혹은 합을 통해 동일 대각선상에 존재하는지 확인했다.
```py
diagonal_constraint = \
    [z3.Implies(z3.And(x[i][j] == 1, x[k][h] == 1, i != k, j != h),
                abs(k - i) != abs(j - h))
    for i in range(num_queens) for j in range(num_queens)
    for k in range(num_queens) for h in range(num_queens)]
```

## Optimizing

위 보다 빠른 환경을 조성하기 위해 Constraint와 Heuristic을 수정해 보았다. 

### 1. Symbol representation

### 2. Constraint representation

### 3. Heuristic representation
