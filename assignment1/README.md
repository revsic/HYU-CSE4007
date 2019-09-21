# Assignment1: N-Queens solver
Solve the N-Queens problem with several searching methods.

## Environments
Tested environments
- Java SE 8u221
- OpenJDK 8
- Windows 10
- Mac OS X Mojave
- Ubuntu 18.04 (WSL)

Suggested environments
- Java >= 8
- bash or PowerShell environment

## Usage
To compile and run
```
mkdir classes
javac -d classes assignment1/Application.java
cd classes
java assignment1.Application 7 /abs/path/to/save/result
```

## Methods
- [BFS](#bfs): Breadth-first search
- [DFS](#dfs): Depth-first search
- [DFID](#dfid): DFS with iterative deepening

Notation
- b: maximum branching factor
- d: depth of least-cost solution
- D: depth of state-space

Characteristics
- completeness: whether an algorithm can always find an existing solution.
- optimality: whether the found solution is the least-cost solution.
- complexity: time, space usage.

Summary

| name | completeness | optimality | time complexity |
|--|--|--|--|
| BFS | O | O | b^d |
| DFS | O | X | b^D |
| DFID | O | O | b^d |

Experiment: 8-Queens problem, 10 times experiment, mean value

| name | time | memory |
|--|--|--|
| DFS | 0.0486 | 145.2MB |
| BFS | 0.4277 | more than 8GB |
| DFID | 0.1354 | 146.6MB |

### BFS

Breadth-first search.
- completeness: O, it traverses the whole state space.
- optimality: O, it traverses all states of the same depth and goes to the next depth.
- complexity
    - time: O(b^d)
    - memory: it saves all previous states to expand the next depth.

Implementation: Queue based iteration.

It is not effective on memory complexity view since it cannot prune the records because of state expansion for the next depth. I want to experiment that BFS shows a crash because of large memory consumption, but it is too slow to stack the record one by one, so I can't check it. I just append my memory view, which represents BFS spend 8GB memory on the 8-Queens problem.

![assignment1 bfs resource view](../rsrc/assignment1_bfs_rsrc_view.png)


### DFS

Depth-first search.
- completeness: O, it traverses the whole state space.
- optimality: X
- complexity
    - time: O(b^D)
    - memory: it can release the branch if it traverses all child branches.

Implementation: Stack base iteration.

In same environment, the 8-Queens problem, DFS spend only 145MB memory and 0.78 seconds, where DFS spend 8GB memory and more than 10 seconds. We can find memory consumption is much efficient than BFS as we expected in algorithmic analysis, but time relative result is so interested.

![assignment1 dfs resource view](../rsrc/assignment1_dfs_rsrc_view.png)

I think about why, and I conclude that N-Queens problem has the solution in known depth, like N, so that if N is going bigger and BFS try to go deeper with stacking all states with large memory consumption, it has to take a long time to approach that known depth. Then it starts to check the bottom states whether it is solved or not. 

But DFS goes to the known depth faster than BFS, and check the states in the middle of the traverse. And I don't experiment yet, but if the solution rate is high enough or has a certain distribution, DFS can find the solution much faster than BFS. And the experiment result shows that DFS is much faster than BFS in the N-Queens problem, so we can guess that the solution rate is high enough for DFS to find the solution. 

### DFID

Depth-first search with iterative deepening.
- completeness: O, since it bases on DFS.
- optimality: O, it uses DFS with limited depths and iteratively increases it.
- complexity
    - time: O(b^d)
    - memory: it can release branches like DFS.

Implementation: Use DFS with limited depth and iteratively increasing depths to find a solution.

Since the N-Queens problem has the solution in known depths, like N, DFID is not such effective on such problems.

## Experiment results
- Time experiment (sec, 7-Queens)

| name | 1th | 2nd | 3rd | 4th | 5th | 6th | 7th | 8th | 9th | 10th | mean |
|--|--|--|--|--|--|--|--|--|--|--|--|
| DFS | 0.047 | 0.05 | 0.052 | 0.047 | 0.048 | 0.048 | 0.048 | 0.049 | 0.049 | 0.048 | 0.0486 |
| BFS | 0.42 | 0.43 | 0.424 | 0.428 | 0.431 | 0.436 | 0.428 | 0.426 | 0.432 | 0.422 | 0.4277 |
| DFID | 0.135 | 0.132 | 0.14 | 0.133 | 0.136 | 0.132 | 0.14 | 0.134 | 0.137 | 0.135 | 0.1354 |

- Memory experiment (8-Queens)
    - Select maximum value in each experiment.
    - BFS is too slow to solve the 8-Queens problem, And I find that it always over 8GB, so I'll write that 'BFS use more than 8GB on 10 experiments.'

| name | 1th | 2nd | 3rd | 4th | 5th | 6th | 7th | 8th | 9th | 10th | mean |
|--|--|--|--|--|--|--|--|--|--|--|--|
| DFS | 145MB | 144MB | 146MB | 145MB | 146MB | 145MB | 145MB | 145MB | 145MB | 146MB | 145.2MB |
| DFID | 145MB | 145MB | 158MB | 146MB | 145MB | 146MB | 146MB | 145MB | 145MB | 145MB | 146.6MB |