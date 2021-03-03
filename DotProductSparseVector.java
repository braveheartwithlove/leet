Suppose we have very large sparse vectors (most of the elements in vector are zeros)

Find a data structure to store them
Compute the Dot Product.
Follow-up:
What if one of the vectors is very small?


public class SparseVectorMultiplication {

    public static void main(String[] args) {
        int[] vector1 = {1, 0, 0, 0, 0, 0, 5, 0, 0, 6, 0, 90, 0, 100};
        int[] vector2 = {9, 0, 0, 5, 0, 18, 0, 0, 6, 0, 0, 2, 100};

        SparseVectorMultiplication sp = new SparseVectorMultiplication(vector1, vector2);
        System.out.println(sp.dotProduct());
    }
    // Stores index as key and value as value.
    private Map<Integer, Integer> vector1;
    private Map<Integer, Integer> vector2;
    // Time: O(N) + O(M)
    // Space: O(N) + O(M)
    public SparseVectorMultiplication(int[] vector1, int[] vector2) {
        this.vector1 = new HashMap<>();
        this.vector2 = new HashMap<>();
        // Make vector 1 always bigger than vector 2   // Follo-up
        if (vector1.length < vector2.length) {
            int[] temp = vector2;
            vector1 = vector2;
            vector2 = temp;
        }

        for (int i = 0; i < vector1.length; i++) {
            if (vector1[i] != 0) {
                this.vector1.put(i, vector1[i]);
            }
        }

        for (int i = 0; i < vector2.length; i++) {
            if (vector2[i] != 0) {
                this.vector2.put(i, vector2[i]);
            }
        }
    }

    // Time: O(Min(M,N))
    public long dotProduct() {
        int product = 0;
        for (Map.Entry<Integer, Integer> entry : this.vector2.entrySet()) {
            product += entry.getValue() * this.vector1.getOrDefault(entry.getKey(), 0);
        }
        return product;
    }
}



public int dotProduct(Map<Integer, Integer> vec1, Map<Integer, Integer> vec2) {
    if (vec2.size() < vec1.size()) {
        var tmp = vec1;
        vec1 = vec2;
        vec2 = tmp;
    }
    int product = 0;
    for (var entry : vec1.entrySet()) {
        product += vec2.getOrDefault(entry.getKey(), 0) * entry.getValue();
    }
    return product;
}