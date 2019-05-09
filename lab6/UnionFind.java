public class UnionFind {
    /** Implement Weighted Quick Union for disjoint set */

    // TODO - Add instance variables?
    private int[] parent; // parent[i] = parent of i
    private int[] size; // size[i] = size of the set if i is root, if i is not root, size is not accurate


    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
            size[i] = 1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        // TODO
        if (vertex < 0 || vertex > parent.length - 1) {
            throw new IllegalArgumentException("Index is out of box!");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        int root = find(v1);
        return size[root];

    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        // TODO
        validate(v1);
        if (parent[v1] == -1) { // if v1 is root
            return -1*size[v1];
        } else {
            return parent[v1];
        }
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
        return find(v1) == find(v2); // find roots of v1 and v2, compare them
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        // TODO
        validate(v1);
        validate(v2);
        int root1 = find(v1);
        int root2 = find(v2);
        if (root1 == root2) return; // if already union, return

        // connect by weight and update size, only update the size of root
        if (size[root1] > size[root2]) {
            parent[root2] = root1;
            size[root1] += size[root2];
        } else {
            parent[root1] = root2;
            size[root2] += size[root1];
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        validate(vertex);
        while (parent[vertex] != -1) {
            vertex = parent[vertex];
        }
        return vertex;
    }

//    public static void main(String[] args) {
//        int n = 7;
//        UnionFind uf = new UnionFind(n);
//        uf.union(1,0);
//        uf.union(4,0);
//        uf.union(2,1);
//        uf.union(5,3);
//        uf.union(5,2);
//    }
}
