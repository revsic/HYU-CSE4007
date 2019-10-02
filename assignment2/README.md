# Assignment2: Hill climbing
Solve the N-Queens problem with Hill Climbing approach.

## Environments
Tested environments
- Java SE 8u221
- OpenJDK 8
- Windows 10
- Ubuntu 18.04 (WSL)

Suggested environments
- Java >= 8
- bash or PowerShell environment

## Usage
To compile and run
```
mkdir classes
javac -d classes assignment2/Application.java
cd classes
java assignment2.Application 7 /abs/path/to/save/result
```

## Sample result
board size 7
```
>Hill Climbing
Location : 1 3 0 6 4 2 5
Time : 0.097
```
board size 8
```
>Hill Climbing
Location : 3 6 4 1 5 0 2 7
Time : 0.375
```
board size 12
```
>Hill Climbing
Location : 6 10 2 0 3 8 4 1 9 11 5 7
Time : 22.732
```

## Methods

### 1. Hill climbing

Local Search의 일종으로 현재의 상태와 Neighbor의 상태만을 통해 목적 함수 Objective의 최대 혹은 최솟값 탐색을 목적으로 한다.

Hill climbing은 문제 환경에서 State와 Neighbor를 정의하고, 현재의 State에서 Objective를 최대 혹은 최소화하는 Neighbor를 선택 이동, Greedy search와 같이 재귀적으로 Objective의 극값을 찾아간다.

```py
def hill_climbing(problem):
    current = make_node(problem.inital_state())
    while True:
        neighbor = highest(current.successor())
        if neighbor.value <= current.value:
            return current.state
        current = neighbor
    return current
```

<img src="../rsrc/assignment2_local_search.png" width="60%">

이때 Hill climbing은 global context가 아닌 local context만을 보기 때문에, 접근 중인 optima가 local optima인지 global optima인지 알 수 없다. 이러한 문제를 해결하기 위해 Random restart 혹은 Random walk 등의 부가적인 policy가 필요하다.

<img src="../rsrc/assignment2_local_minima.png" width="60%">

N-Queens의 문제 환경에서 State와 Neighbor는 다음과 같이 정의할 수 있다.
- State: Queen의 위치를 나타내는 NxN boolean matrix
- Neighbor: N개의 Queen 중 하나를 골라 1칸 이동 (Nx8 Neighbor State)

이때 objective의 global optima가 N-Queens problem의 solution이라 가정하면, random initial state에 대해서 neighbor를 objective에 따라 평가하고, neighbor selection policy에 따라 다음 상태를 선택해 가면 문제를 해결할 수 있다.

### 2. Objective

N-Queens의 Constraint는 다음과 같다.
- 각 행에는 하나의 Queen만이 존재한다.
- 각 열에는 하나의 Queen만이 존재한다.
- 각 대각선에는 하나 이하의 Queen이 존재한다.

이를 Objective로 활용하기 위해 각 행과 열, 대각선에 몇 개의 Queen이 존재하는지 나타내는 배열을 정의한다. 이후, 각 배열이 가지는 constraint를 기반으로 objective를 설계한다.
```py
objective = sum(abs(r - 1) for r in row) +
            sum(abs(c - 1) for c in col) +
            sum(max(x - 1, 0) for x in rightdown) +
            sum(max(y - 1, 0) for y in leftdown)
where row, col = N size array
      rightdown, leftdown = (2N - 1) size array
```
여기서 row와 col은 각 행과 열에 위치하는 Queen의 개수, rightdown은 우하향 대각선, leftdown은 좌하향 대각선을 의미한다.

주어진 NxN boolean matrix가 constraint를 만족한다면 objective는 0에 수렴할 것이고, 더 많은 constraint를 어길수록 큰 값을 가지게 된다. N-Queens를 해결하기 위해 해당 objective를 최소로 하는 Hill climbing method를 실행한다.

### 3. Escaping local optima and Neighbor selection

N-Queens problem에선 앞서 정의된 Objective가 0에 수렴해야 solution이 존재한다. 이때 0보다 큰 값에서 objective가 수렴할 경우 N-Queens solution을 찾을 수 없다. 이렇게 local optima에 빠질 경우 이를 빠져나갈 policy가 필요하다.

1. Random walk, Stochastic selection

neighbor를 평가하여 선택하는 과정에서, 무조건 가장 높은 값을 선택하기보단 확률적인 프로세스를 통해 적절한 neighbor를 선택한다.

2. Random restart

local optima에 빠진 경우, random state에서 다시 시작한다.

[Hill Climbing](./hillclimbing/HillClimbing.java) 구현체에서는 후자를 선택하여 neighbor 선발 방식에서 전자보다 높은 자율성을 부여하였다. Random restart와 모든 neighbor 중 가장 높은 평가치를 가진 상태를 선택하는 방식을 채택하였다.

### 4. Experiment

1. 수렴 시간



2. Random restart 횟수
