import java.util.*;

public class Graph {
  static class Edge implements Comparable<Edge> {
    int src, dest, weight;

    // Comparator function used for sorting edges based on their weight
    public int compareTo(Edge compareEdge) {
      return this.weight - compareEdge.weight;
    }

    // constructor
    public Edge(int src, int dest, int weight) {
      this.src = src;
      this.dest = dest;
      this.weight = weight;
    }
  }

  // A class to represent a subset for union-find
  static class subset {
    int parent, rank;

    public subset(int parent, int rank) {
      this.parent = parent;
      this.rank = rank;
    }
  }

  // class representing a subset for use in union-find data structure
  static class Subset {
    int parent, rank;

    public Subset(int parent, int rank) {
      this.parent = parent;
      this.rank = rank;
    }
  }

  // recursive function to find the parent/root of a subset element[i]
  static int find(Subset[] subsets, int i) {
    if (subsets[i].parent != i)
      subsets[i].parent = find(subsets, subsets[i].parent);
    return subsets[i].parent;
  }

  // perform the union of two subsets based on ranks
  static void union(Subset[] subsets, int x, int y) {
    int xRoot = find(subsets, x);
    int yRoot = find(subsets, y);

    // checks if ranks are equal or less than for both xRoot and yRoot
    if (subsets[xRoot].rank < subsets[yRoot].rank)
      subsets[xRoot].parent = yRoot;
    else if (subsets[xRoot].rank > subsets[yRoot].rank)
      subsets[yRoot].parent = xRoot;
    // if equal, randomly choose one as the parent and increase its rank
    else {
      subsets[yRoot].parent = xRoot;
      subsets[xRoot].rank++;
    }
  }

  // main
  public static void main(String[] args) {
    // vars
    int vertices = 4;
    int edges = 3;

    Edge[] graphEdges = new Edge[edges];
    graphEdges[0] = new Edge(0, 1, 2);
    graphEdges[1] = new Edge(1, 2, 3);
    graphEdges[2] = new Edge(0, 2, 1);

    // sort edges in non-decreasing order of weight
    Arrays.sort(graphEdges);
    Edge[] result = new Edge[vertices - 1];
    int e = 0, i = 0;

    // initialize subsets for union finding
    Subset[] subsets = new Subset[vertices];
    for (int v = 0; v < vertices; v++)
      subsets[v] = new Subset(v, 0);

    // Kruskal's algorithm
    while (e < vertices - 1 && i < edges) {
      Edge nextEdge = graphEdges[i++];
      int x = find(subsets, nextEdge.src);
      int y = find(subsets, nextEdge.dest);

      // add current edge to the MST result if it doesn't create a cycle, merges
      // subsets
      if (x != y) {
        result[e++] = nextEdge;
        union(subsets, x, y);

      }
    }
    // printing the MST with loop
    System.out.println("Edges in the MST: ");
    for (i = 0; i < e; i++) {
      System.out.println(result[i].src + " - " + result[i].dest + ": " + result[i].weight);
    }
  }
}
