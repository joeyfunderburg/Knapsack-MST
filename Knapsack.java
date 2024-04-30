public class Knapsack {
  // A method to find the maximum of two integers
  static int max(int a, int b) {
    return (a > b) ? a : b;
  }

  // Returns maximum value that can be put in a knapsack of capacity
  static int knapsack(int W, int[] wt, int[] val, int n) {
    int[] K = new int[W + 1];

    // loop iteratively calculates the max value achievable with the given items and
    // knapsack capacity, updates values in K[] based on each item's weight, value,
    // and remaining capacity
    for (int i = 0; i < n; i++) {
      for (int w = W; w >= wt[i]; w--) {
        K[w] = max(K[w], val[i] + K[w - wt[i]]);
      }
    }

    // return max value that can be acheived
    return K[W];
  }

  // Build table K[][] in bottom up manner (to do)
  public static void main(String[] args) {
    int[] val = { 60, 100, 120, 160, 50, 110, 150, 200 }; // Values
    int[] wt = { 10, 20, 30, 40, 10, 25, 35, 45 }; // Item Weights
    int W = 120; // Maximum weight capacity of knapsack
    int n = val.length; // Number of items

    // prints
    System.out
        .println("Maximum value that can be put in a knapsack of capacity W = " + W + " is " + knapsack(W, wt, val, n));
  }
}
