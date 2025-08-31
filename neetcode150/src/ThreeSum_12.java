

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum_12 {
    public static void main(String[] args) {
        int[] nums = {0,0,0,0};
        List<List<Integer>> res = threeSum_2(nums);
        for(List<Integer> item: res)
            System.out.println(item);
    }

    /**
     * Method 1: binary search
     * O(n^2logn)
     * */
    public static List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;

        // sort
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for(int i=0;i<n-2;i++) {
            if(i!=0 && nums[i] == nums[i-1]) continue;
            for(int j=i+1;j<n-1;j++) {
                if(j!=i+1 && nums[j] == nums[j-1]) continue;
                int target = -nums[i]-nums[j];

                // 在 [j+1,n-1]范围内找 target
                int id = binarySearch(nums, j+1, n-1, target);

                if(id!=-1) {
                    List<Integer> item = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[id]));
                    res.add(item);
                }
            }
        }
         return res;
    }


    private static int binarySearch(int[] nums, int left, int right, int target) {
        if(left > right || right > nums.length - 1) return -1;
        while(left < right) {
            int mid = left + right >> 1;
            if(nums[mid] == target) return mid;
            else if(nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return nums[left] == target ? left : -1;
    }


    /**
     * Method 2: 固定一个起点的双指针
     * */
    public static List<List<Integer>> threeSum_2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // sort
        Arrays.sort(nums);
        int n = nums.length;

        for(int k=0;k<n;k++) {
            // 去重
            if(k!=0 && nums[k] == nums[k-1]) continue;

            int target = -nums[k];
            // 双指针
            int i = k+1, j = n-1;
            while(i < j) {
                // 去重
                while(i+1 < n && nums[i] == nums[i+1]) i++;
                // 退出循环时，nums[i] 和后一个元素不一样
                // 2,3,3,3
                // i 一定停在 2 的位置，而j 一定停在3的位置，位置不会重合
                while(j > i && nums[j] == nums[j-1]) j--;
                // 退出循环时 nums[j] 和前一个不一样

                if(nums[i]+nums[j] == target) {
                    List<Integer> item = new ArrayList<>(Arrays.asList(nums[k], nums[i], nums[j]));
                    res.add(item);
                    i++;
                    j--;
                } else if(nums[i]+nums[j] < target) {
                    i++;
                } else {
                    j--;
                }
            }
        }
        return res;
    }


    /**
     * 优化，增加 break
     * */
    public static List<List<Integer>> threeSum_3(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // sort
        Arrays.sort(nums);
        int n = nums.length;

        // O(n)
        for(int k=0;k<n-2;k++) {
            // 去重
            if(k!=0 && nums[k] == nums[k-1]) continue;
            int target = -nums[k];

            if(nums[n-1] + nums[n-2] < target) continue;
            if(nums[k] + nums[k+1] + nums[k+2] > 0) break;

            // 双指针
            int i = k+1, j = n-1;
            // O(n)
            while(i < j) {
                // 去重
                while(i+1 < n && nums[i] == nums[i+1]) i++;
                // 退出循环时，nums[i] 和后一个元素不一样
                // 2,3,3,3
                // i 一定停在 2 的位置，而j 一定停在3的位置，位置不会重合
                while(j > i && nums[j] == nums[j-1]) j--;
                // 退出循环时 nums[j] 和前一个不一样

                if(nums[i]+nums[j] == target) {
                    List<Integer> item = new ArrayList<>(Arrays.asList(nums[k], nums[i], nums[j]));
                    res.add(item);
                    i++;
                    j--;
                } else if(nums[i]+nums[j] < target) {
                    i++;
                } else {
                    j--;
                }
            }
        }
        return res;
    }
}
