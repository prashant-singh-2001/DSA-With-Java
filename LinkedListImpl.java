class Node {
    int value;
    Node next;

    Node() {
        this(0, null);
    }

    Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }
}

class LinkedList {
    private Node head;

    LinkedList() {
        head = null;
    }

    // Insert at the end of the list
    public void insert(int value) {
        Node newNode = new Node(value, null);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    // Insert at a specific position
    public void insert(int value, int position) {
        Node newNode = new Node(value, null);
        if (position == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node temp = head;
            int index = 0;
            while (temp != null && index < position - 1) {
                temp = temp.next;
                index++;
            }
            if (temp != null) {
                newNode.next = temp.next;
                temp.next = newNode;
            } else {
                System.out.println("Position out of bounds");
            }
        }
    }

    // Print all elements in the list
    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    // Delete a node by value
    public void delete(int value) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        if (head.value == value) {
            head = head.next;
            return;
        }
        Node temp = head;
        while (temp.next != null && temp.next.value != value) {
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next = temp.next.next;
        } else {
            System.out.println("Value not found in the list");
        }
    }

    // Get the size of the list
    public int size() {
        int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    // Search for a node by value
    public boolean search(int value) {
        Node temp = head;
        while (temp != null) {
            if (temp.value == value) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }
}

public class LinkedListImpl {
    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.insert(1);
        ll.insert(2);
        ll.insert(3);
        ll.insert(5);
        ll.insert(4, 2);

        System.out.println("Linked List:");
        ll.printList();

        System.out.println("Deleting value 3:");
        ll.delete(3);
        ll.printList();

        System.out.println("Size of the list: " + ll.size());

        System.out.println("Search for value 2: " + ll.search(2));
        System.out.println("Search for value 3: " + ll.search(3));
    }
}
