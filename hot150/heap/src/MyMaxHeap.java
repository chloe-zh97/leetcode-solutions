import java.util.ArrayList;
import java.util.List;
/**
 * 大根堆
 * */
public class MyMaxHeap {
    private List<Integer> heap;

    public MyMaxHeap() {
        heap = new ArrayList<>();
    }

    private int parent(int i) {
        return (i-1)/2;
    }

    private int leftChild(int i) {
        return i*2+1;
    }

    private int rightChild(int i) {
        return i*2+2;
    }

    /**
     * 交换节点 i 和 j 的值
     * */
    private void swap(int i, int j) {
        int tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }

    private boolean isEmpty() {
        return heap.isEmpty();
    }

    public void insert(int value) {
        // 先加到最后，然后自底向上调整
        heap.add(value);

        int curIndex = heap.size() - 1;

        while(curIndex > 0 && heap.get(curIndex) > heap.get(parent(curIndex))) {
            // 没有调整到顶端且这个节点比自己的父亲节点还要大，交换它们
            swap(curIndex, parent(curIndex));
            curIndex = parent(curIndex);
        }
    }

    public int extractMax() {
        if(heap.isEmpty()) return -1;

        int max = heap.get(0);
        int lastElem = heap.removeLast();

        if(heap.isEmpty()) return max;

        // swap
        heap.set(0, lastElem);

        // 自顶向下调整
        int curIndex = 0;
        while(true) {
            int left = leftChild(curIndex);
            int right = rightChild(curIndex);
            int biggest = curIndex;
            if(left < heap.size() && heap.get(left) > heap.get(biggest)) {
                biggest = left;
            }

            if(right < heap.size() && heap.get(right) > heap.get(biggest)) {
                biggest = right;
            }

            if(biggest == curIndex) break;

            // swap
            swap(biggest, curIndex);
            curIndex = biggest;
        }

        return max;
    }

    public static void main(String[] args) {
        MyMaxHeap maxHeap = new MyMaxHeap();
        maxHeap.insert(10);
        maxHeap.insert(5);
        maxHeap.insert(15);
        maxHeap.insert(20);
        maxHeap.insert(25);

        System.out.println(maxHeap.extractMax());
        System.out.println(maxHeap.extractMax());
    }
}
