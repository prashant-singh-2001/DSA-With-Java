import java.util.EmptyStackException;

class Queue {
    int[] queue;
    int front, rear, size;
    // Default constructor with initial size
    Queue() {
        this(1000);
    }

    // Constructor with custom size
    Queue(int size) {
        queue = new int[size];
        front = 0;
        rear = 0;
        this.size = size;
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return front == rear;
    }

    // Check if the queue is full
    public boolean isFull() {
        return rear == size;
    }

    // Enqueue an element into the queue
    public void enqueue(int a) {
        if (isFull()) {
            throw new EmptyStackException();
        }
        queue[rear] = a;
        rear++;
    }

    // Dequeue an element from the queue
    public int dequeue() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        int a = queue[front];
        front++;
        // Reset front and rear when the queue is empty
        if (front == rear) {
            front = 0;
            rear = 0;
        }
        return a;
    }

    // Peek the front element of the queue
    public int peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return queue[front];
    }
}

public class QueueImpl {
    public static void main(String[] args) {
        // Create a new queue instance
        Queue q = new Queue();
        
        // Enqueue several integers into the queue
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        q.enqueue(6);
        q.enqueue(7);
        q.enqueue(8);
        q.enqueue(9);

        // Dequeue and print all elements from the queue
        while (!q.isEmpty()) {
            System.out.println(q.dequeue());
        }
    }
}
