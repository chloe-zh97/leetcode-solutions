//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Random;
//
//public class InsertDeleteGetRandomO1_380 {
//    class RandomizedSet {
//
//        // 存储 index
//        HashMap<Integer, Integer> indexMap;
//        // 存储 val
//        HashMap<Integer, Integer> valMap;
//
//        public RandomizedSet() {
//            indexMap = new HashMap<>();
//            valMap = new HashMap<>();
//        }
//
//        public boolean insert(int val) {
//            if(valMap.containsKey(val)) return false;
//
//            int size = indexMap.size();
//            indexMap.put(size, val);
//            valMap.put(val, size);
//            return true;
//        }
//
//        public boolean remove(int val) {
//            if(!valMap.containsKey(val)) return false;
//
//            int lastIndex = indexMap.size()-1;
//            int lastElem = indexMap.get(lastIndex);
//
//            // 待移除元素的位置
//            int removedIndex = valMap.get(val);
//
//            if(lastIndex == removedIndex) {
//                // 移除最后一个元素
//                valMap.remove(val);
//                indexMap.remove(removedIndex);
//            } else {
//                valMap.remove(val);
//                // 替换坐标
//                indexMap.put(removedIndex, lastElem);
//                indexMap.remove(lastIndex);
//                valMap.put(lastElem, removedIndex);
//
//            }
//            return true;
//        }
//
//        public int getRandom() {
//            Random random = new Random();
//            int index = random.nextInt(indexMap.size());
//            return indexMap.get(index);
//        }
//    }
//
//    class RandomizedSet_2 {
//        HashMap<Integer, Integer> map;
//        List<Integer> nums;
//        Random random;
//
//        public RandomizedSet_2() {
//            map = new HashMap<>();
//            nums = new ArrayList<>();
//            random = new Random();
//        }
//
//        public boolean insert(int val) {
//            if(map.containsKey(val)) return false;
//
//            nums.add(val);
//            map.put(val, nums.size()-1);
//            return true;
//        }
//
//        public boolean remove(int val) {
//            if(!map.containsKey(val)) return false;
//            int removedIndex = map.get(val);
//            int lastElem = nums.getLast();
//
//            nums.set(removedIndex, lastElem);
//            map.put(lastElem, removedIndex);
//
//            map.remove(val);
//            nums.removeLast();
//
//            return true;
//        }
//
//        public int getRandom() {
//            int id = random.nextInt(nums.size());
//            return nums.get(id);
//        }
//    }
//
//    public static void main(String[] args) {
//
//    }
//
//}
