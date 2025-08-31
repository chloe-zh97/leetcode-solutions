import java.util.Arrays;

public class DesignCircularQueue_622 {
    public static void main(String[] args) {
        MyCircularQueue mq = new MyCircularQueue(3);
        // 1,2,3
        mq.enQueue(1);
        mq.enQueue(2);
        mq.enQueue(3);
        //mq.enQueue(4);
        // 1 出栈，front=1, end=2
//        mq.deQueue();
//        mq.enQueue(4);
//        System.out.println(mq.isFull());
//        System.out.println(mq.isEmpty());

        mq.deQueue();
        mq.deQueue();
        mq.deQueue();
    }

    /**
     * Method 1: front 指向头节点，end 指向队列的最后一个元素
     * */
    static class MyCircularQueue {
        private int[] array;
        private int front;
        private int end;

        public MyCircularQueue(int k) {
            int capacity = k + 1;
            array = new int[capacity];
            // 浪费一个位置，front 指向第一个元素位置，end 指向下一个空位置
            front = 0; end = 0;
        }

        public boolean enQueue(int value) {
            if(isFull()) return false;
            array[end] = value;
            end = (end+1) % array.length;
            return true;
        }

        public boolean deQueue() {
            if(isEmpty()) return false;
            front = (front+1) % array.length;
            return true;
        }

        public int Front() {
            if(isEmpty()) return -1;
            return array[front];
        }

        public int Rear() {
            if(isEmpty()) return -1;
            int n = array.length;
            return array[(end+n-1)%n];
        }

        public boolean isEmpty() {
            return front == end;
        }

        public boolean isFull() {
           return (end+1) % array.length == front;
        }
    }
}
