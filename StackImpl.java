import java.util.EmptyStackException;

public class StackImpl {
    public static void main(String[] args) {
        // Create a new stack instance stack1
        Stack stack1 = new Stack();
        
        // Push several integers onto stack1
        stack1.push(5);
        stack1.push(7);
        stack1.push(8);
        stack1.push(52);
        stack1.push(54);
        stack1.push(65);
        stack1.push(75);
        stack1.push(0);

        // Print the contents of stack1
        System.out.println("Stack1 contents:");
        stack1.printStack();

        // Pop all elements from stack1 and print each one
        System.out.println("Popping all elements from Stack1:");
        while (!stack1.isEmpty()) {
            System.out.println(stack1.pop());
        }

        // Array to be pushed onto another stack
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        
        // Create a new stack instance stack2
        Stack stack2 = new Stack();
        
        // Push the array elements onto stack2
        stack2.push(arr);

        // Print the contents of stack2 by popping all elements
        System.out.println("Stack2 contents:");
        while (!stack2.isEmpty()) {
            System.out.println(stack2.pop());
        }

        // Print the contents of stack1 after it has been emptied
        System.out.println("Stack1 after popping all elements:");
        stack1.printStack();
    }
}

class Stack {
    private int[] stack; // Array to hold stack elements
    private int top; // Index of the top element
    private static final int INITIAL_CAPACITY = 10; // Initial capacity of the stack

    // Constructor to initialize stack and top
    Stack() {
        stack = new int[INITIAL_CAPACITY];
        top = -1;
    }

    // Push a single integer onto the stack
    public void push(int a) {
        if (top == stack.length - 1) {
            resize(); // Resize the stack if it's full
        }
        stack[++top] = a;
    }

    // Pop and return the top element from the stack
    public int pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack[top--];
    }

    // Return the top element without removing it from the stack
    public int peep() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack[top];
    }

    // Push an array of integers onto the stack
    public void push(int[] arr) {
        for (int i : arr) {
            push(i);
        }
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Print all elements in the stack from top to bottom
    public void printStack() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
        } else {
            for (int i = top; i >= 0; i--) {
                System.out.println(stack[i]);
            }
        }
    }

    // Resize the stack array when it becomes full
    private void resize() {
        int newSize = stack.length * 2;
        int[] newStack = new int[newSize];
        System.arraycopy(stack, 0, newStack, 0, stack.length);
        stack = newStack;
    }
}
