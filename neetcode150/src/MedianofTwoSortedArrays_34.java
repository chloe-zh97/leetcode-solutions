import java.util.Arrays;

public class MedianofTwoSortedArrays_34 {
    public static void main(String[] args) {
        int[] nums1 = {};
        int[] nums2 = {2,3};
        System.out.println(findMedianSortedArrays_3(nums1, nums2));
    }

    /**
     * 返回两个 sorted array 的中位数
     * Method 1: 暴力法
     * 先合并两个数组，再找中位数
     * */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] nums = new int[m+n];
        for(int i=0;i<m;i++)
            nums[i] = nums1[i];

        for(int i=m;i<nums.length;i++)
            nums[i] = nums2[i-m];

        // sort
        Arrays.sort(nums);

        int len = m+n;
        int a = nums[len / 2], b = nums[(len-1)/2];
        return (a+b) / 2.0;
    }


    /**
     * Method optimal: 令 nums1 是长度更大的那个，枚举 left partition 中
     * nums1 所占的个数
     * */
    public static double findMedianSortedArrays_2(int[] nums1, int[] nums2) {
        if(nums1.length < nums2.length) return findMedianSortedArrays_2(nums2, nums1);
        int m = nums1.length, n = nums2.length;

        int totalLen = m+n;
        // left partition 需要多少个元素
        // half 向上取整
        int half = totalLen / 2;
        final int INF = Integer.MAX_VALUE / 2;

//        if(n == 0) {
//            if(m % 2 == 1) return nums1[half];
//            else return (nums1[(totalLen-1)/2] + nums1[totalLen / 2]) / 2.0;
//        }

        // 二分枚举 nums1 中，在 left partition 的个数
        int left = 0, right = half;

        while(left <= right) {
            // 代表个数
            int mid = left + right >> 1;
            int remain = half-mid;

            // nums2[0..index] 在 left partition 中
            int indexB = remain-1;
            int indexA = mid-1;

            System.out.println("half="+half+" indexA="+indexA+" indexB="+indexB);

            int leftA = indexA < 0 ? -INF : nums1[indexA];
            int rightA = indexA+1 >= nums1.length ? INF: nums1[indexA+1];
            int leftB = (indexB < 0 || indexB > nums2.length-1) ? -INF : nums2[indexB];
            int rightB = remain >= nums2.length ? INF: nums2[indexB+1];

            //System.out.println("left="+left+" right="+right);
//            System.out.println("half="+half+" indexA="+indexA+" indexB="+indexB);
//            System.out.println("leftA="+leftA+" rightA="+rightA + " leftB="+leftB+" rightB="+rightB);

            if(leftA <= rightB && leftB <= rightA) {
                // 找到正确的 left partition
                if(totalLen % 2 == 1) return Math.min(rightA, rightB);
                else {
                    // 偶数
                    return (Math.max(leftA, leftB) + Math.min(rightA, rightB)) / 2.0;
                }
            } else if(leftA > rightB) {
                // A 中 left partition 元素太多了
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static double findMedianSortedArrays_3(int[] nums1, int[] nums2) {
        int[] A = nums1;
        int[] B = nums2;
        int total = A.length + B.length;
        int half = (total + 1) / 2;

        if (B.length < A.length) {
            int[] temp = A;
            A = B;
            B = temp;
        }

        int l = 0;
        int r = A.length;
        while (l <= r) {
            int i = (l + r) / 2;
            int j = half - i;

            int Aleft = i > 0 ? A[i - 1] : Integer.MIN_VALUE;
            int Aright = i < A.length ? A[i] : Integer.MAX_VALUE;
            int Bleft = j > 0 ? B[j - 1] : Integer.MIN_VALUE;
            int Bright = j < B.length ? B[j] : Integer.MAX_VALUE;

            if (Aleft <= Bright && Bleft <= Aright) {
                if (total % 2 != 0) {
                    return Math.max(Aleft, Bleft);
                }
                return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
            } else if (Aleft > Bright) {
                r = i - 1;
            } else {
                l = i + 1;
            }
        }
        return -1;
    }
}
