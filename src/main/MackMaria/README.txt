RandomMST
1/ For this problem we decided to use a modified version of Kruskals algorithm to find the MST. First we add nodes one at
 a time to the graph along with an edge pointing to ever other existing node in the graph. In an effort to reduce
 the memory usage of our program (IE. the number of edges stored at any given point) while ensuring correctness,
 we ran Krusals algorithm against our graph every time we added a new node. After each iteration we end up with MST(i)
 where i is the number of nodes added thusfar. There are three additional optimisations I attempted which are documented
 more thoroughly in the code. Two attempted to improve the average case by ignoring edges that couldn't possibly be added
 to the MST, before any processing has been done. Doing this improved our programs speed by about 5 times on average.

Looking back we should have considered using Prim's algorithm over Kruskals. This is because the graphs we are dealing
with are very dense ie. have lots of edges. When using an adjacency matrix to store the graph in Prim's
you can achieve a time complexity of O(V^2) which is not dependent on the number of edges. In this scenario that
would most likely be more optimal.

2.
	V			Runtime			Output
	10          0.002s          1.1748414121495079
	100         0.008s          1.093292263223537
	1000        0.061s          1.2206607344551461
	10000       4.997s          1.179055127043645

3. The RandomMST program, in the worst case, executes kruskal's algorithm V times, where V is the number of vertexes given
to the program. This results in a time complexity V*kruskal's which is V*(E*logV). Because our list of edges are at most
2V long the result is O(V*V*logV)

CircleMST
1.
Every time we add a new vertex we recompute the minimum spanning tree using kruskal's algorithm
to compute the new minimum spanning tree. We first generate all the edges connecting the new vertex to
all the other vertices in the graph excluding edges that are larger than the smallest edge we've seen
so far. Then we recalculate the minimum spanning tree over all the newly
generated edges and the edges in the previous minimum spanning tree.

2.
	V			Runtime			Output
	10          0.002s          3.208117455355781
	100         0.008s          12.0808547683665
	1000        0.048s          36.542923820722216
	10000       3.343s          116.02299860786574

3. For our algorithm, if we have V vertices, we need to run kruskal's algorithms V times. Since
kruskal's algorithm runs in E log V and since we have at most 2V edges in the graph at any time, we can say that
the algorithm is in O(v^2logV)