public class DesignCircularDeque_641 {
    public static void main(String[] args) {

    }

    static class MyCircularDeque {
        private int[] arr;
        private int front = 0;
        private int end = 0;

        public MyCircularDeque(int k) {
            int capacity = k + 1;
            arr = new int[capacity];
        }

        public boolean insertFront(int value) {
           if(isFull()) return false;
           int n = arr.length;
           front = (front-1+n)%n;
           arr[front] = value;
           return true;
        }

        public boolean insertLast(int value) {
            if(isFull()) return false;
            int n = arr.length;
            arr[end] = value;
            end = (end+1)%n;
            return true;
        }

        public boolean deleteFront() {
            if(isEmpty()) return false;
            int n = arr.length;
            front = (front+1) % n;
            return true;
        }

        public boolean deleteLast() {
            if(isEmpty()) return false;
            int n = arr.length;
            end = (end-1+n) % n;
            return true;
        }

        public int getFront() {
            if(isEmpty()) return -1;
            return arr[front];
        }

        public int getRear() {
            if(isEmpty()) return -1;
            int n = arr.length;
            return arr[(end-1+n)%n];
        }

        public boolean isEmpty() {
            return front == end;
        }

        public boolean isFull() {
            int n = arr.length;
            return (end+1)%n == front;
        }
    }
}
