import java.util.LinkedList;

public class HashTable {
    private LinkedList<Entry>[] hash;
    private int size;

    static class Entry {
        int key;
        int value;

        Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    HashTable() {
        this(1000);
    }

    @SuppressWarnings("unchecked")
    HashTable(int size) {
        hash = new LinkedList[size];
        this.size = size;
        for (int i = 0; i < size; i++) {
            hash[i] = new LinkedList<>();
        }
    }

    // Mod hashing
    private int hashingFunction(int a) {
        int hashValue = a % size;
        return hashValue;
    }

    // Mid Square Hashing
    private int midSquareHashing(int a) {
        int square = a * a;
        String squareStr = String.valueOf(square);
        int mid = squareStr.length() / 2;
        int hashValue = Integer.parseInt(squareStr.substring(mid - 1, mid + 1));
        return hashValue % size;
    }

    // Mid Fold Hashing
    private int midFoldHashing(int a) {
        String numStr = String.valueOf(a);
        int len = numStr.length();
        int mid = len / 2;
        int part1 = Integer.parseInt(numStr.substring(0, mid));
        int part2 = Integer.parseInt(numStr.substring(mid, len));
        int hashValue = part1 + part2;
        return hashValue % size;
    }

    // Linear Probing
    private int linearProbing(int key) {
        int hashValue = hashingFunction(key);
        while (!hash[hashValue].isEmpty()) {
            hashValue = (hashValue + 1) % size;
        }
        return hashValue;
    }

    // Quadratic Probing
    private int quadraticProbing(int key) {
        int hashValue = hashingFunction(key);
        int i = 1;
        while (!hash[hashValue].isEmpty()) {
            hashValue = (hashValue + i * i) % size;
            i++;
        }
        return hashValue;
    }

    // Chaining
    public void insert(int key, int value) {
        int hashValue = hashingFunction(key);
        hash[hashValue].add(new Entry(key, value));
    }

    public Integer get(int key) {
        int hashValue = hashingFunction(key);
        for (Entry entry : hash[hashValue]) {
            if (entry.key == key) {
                return entry.value;
            }
        }
        return null;
    }

    public boolean remove(int key) {
        int hashValue = hashingFunction(key);
        for (Entry entry : hash[hashValue]) {
            if (entry.key == key) {
                hash[hashValue].remove(entry);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        HashTable ht = new HashTable();

        ht.insert(10, 100);
        ht.insert(20, 200);
        ht.insert(30, 300);

        System.out.println("Value for key 10: " + ht.get(10)); // Output: 100
        System.out.println("Value for key 20: " + ht.get(20)); // Output: 200
        System.out.println("Value for key 30: " + ht.get(30)); // Output: 300

        ht.remove(20);

        System.out.println("Value for key 20: " + ht.get(20)); // Output: null
    }
}
