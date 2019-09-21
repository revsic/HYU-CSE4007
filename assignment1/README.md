# Assignment1: N-Queens solver
Solve N-Queens problem with several searching methods.

## Environments
Tested environments
- Java SE 8u221
- OpenJDK 8
- Windows 10
- Mac OS X Mojave
- Ubuntu 18.04 (WSL)

Suggested environments
- Java >= 8
- bash or powershell environment

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
- D: depth of state space

Characteristics
- completeness: whether algorithm can always find existing solution.
- optimality: whether found solution is least-cost solution.
- complexity: time, space usage.

Summary

| name | completeness | optimality | time complexity |
|--|--|--|--|
| BFS | O | O | b^d |
| DFS | O | X | b^D |
| DFID | O | O | b^d |

### BFS

Breadth-first search.
- completeness: O, it traverses whole state space.
- optimality: O, it traverses all states of same depth and go to next depth.
- complexity
    - time: O(b^d)
    - memory: it saves all previous states to expand next depth.

Implementation: Queue based iteration.

It is not effective on memory complexity view since it cannot prune the records because of state expansion for next depth. I want to experiment that BFS shows crash because of large memory consumption, but it is too slow to stack the record one by one, so I can't check it. I just append my memory view, which represent BFS spend 8GB memory on 8-Queens problem.

![assignment1 bfs resource view](../rsrc/assignment1_bfs_rsrc_view.png)


### DFS

Depth-first search.
- completeness: O, it traverse whole state space.
- optimality: X
- complexity
    - time: O(b^D)
    - memory: it can release the branch if it traverse all child branches.

Implementation: Stack base iteration.

In same environment, 8-Queens problem, DFS spend only 145MB memory and 0.78 seconds, where DFS spend 8GB memory and more than 10 seconds. We can find memory consumption is much efficient than BFS as we expected in alorithmic analysis, but time relative result is so interested.

![assignment1 dfs resource view](../rsrc/assignment1_dfs_rsrc_view.png)

I think about why, and I conclude that N-Queens problem has solution in known depth, like N, so that if N is go bigger and BFS try to go deeper with stacking all states with large memory consumption, it has to take long time to approach that known depth. Then it starts to check bottom states whether it is solved or not. 

But DFS goes to the known depth faster than BFS, and check the states in the middel of traverse. And I don't expriment yet, but if the solution rate is high enough or have certain distribution, DFS can find the solution much faster than BFS. And the experiment result show that DFS is much faster than BFS in N-Queens problem, so we can guess that solution rate is high enough for DFS to find the solution. 

### DFID

Depth-first search with iterative deepening.
- completeness: O, since it base on DFS.
- optimality: O, it use DFS with limited depths and iteratively increase it.
- complexity
    - time: O(b^d)
    - memory: it can release the branch like DFS.

Implementation: Use DFS with limited depth and iteratively increasing depths to find solution.

Since N-Queens problem has solution in known depths, like N, DFID is not such effective comparatively to DFS or BFS on such problems. But it can be more effective than BFS on view of memory complexity.
