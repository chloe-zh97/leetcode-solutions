import java.util.ArrayList;
import java.util.List;

public class MyMinHeap {

    private List<Integer> heap;

    public MyMinHeap() {
        heap = new ArrayList<>();
    }

    /**
     * 返回节点 i 的父亲节点 index
     * */
    private int parent(int i) {
        return (i-1)/2;
    }

    private int leftChild(int i) {
        return 2*i+1;
    }

    private int rightChild(int i) {
        return 2*i+2;
    }

    /**
     * 交换节点 i 和 节点 j 的值
     * */
    private void swap(int i, int j) {
        int tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }

    /**
     * 在 heap 中插入 value, 先放在数组最后，然后自底向上调整
     * */
    public void insert(int value) {
        heap.add(value);

        int curIndex = heap.size()-1;

        while(curIndex > 0 && heap.get(curIndex) < heap.get(parent(curIndex))) {
            // 当没有到达根节点并且当前的 id 比父亲节点的值还要小
            swap(curIndex, parent(curIndex));
            curIndex = parent(curIndex);
        }
    }

    /**
     * 在 heap 中获取最小的节点并删除
     * */
    public int extractMin() {
        int min = heap.get(0);
        // 最后一个元素
        int lastElem = heap.removeLast();

        if(heap.isEmpty()) return min;

        // 替换
        heap.set(0, lastElem);

        int curIndex = 0;
        while(true) {
            // 直到父亲节点和孩子节点大小关系正确后退出循环
            int left = leftChild(curIndex);
            int right = rightChild(curIndex);
            int smallest = curIndex;
            if(left < heap.size() && heap.get(left) < heap.get(smallest)) {
                smallest = left;
            }

            if(right < heap.size() && heap.get(right) < heap.get(smallest)) {
                smallest = right;
            }

            if(smallest == curIndex) break;

            // swap, smallest 和 curIndex
            swap(smallest, curIndex);
            curIndex = smallest;
        }

        return min;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public static void main(String[] args) {
        MyMinHeap minHeap = new MyMinHeap();
        minHeap.insert(10);
        minHeap.insert(5);
        minHeap.insert(15);
        minHeap.insert(20);
        minHeap.insert(25);

        System.out.println(minHeap.extractMin());
        System.out.println(minHeap.extractMin());
    }

}
