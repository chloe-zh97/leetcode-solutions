public class MedianofTwoSortedArrays_4 {
    public static void main(String[] args) {
        int[] nums1 = {1,3};
        int[] nums2 = {2};
        System.out.println(findMedianSortedArrays_2(nums1, nums2));
    }

    /**
     * Method 1: 双指针 O(m+n)
     * */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // swap
        if(nums1.length > nums2.length) {
            int[] tmp = nums2;
            nums2 = nums1;
            nums1 = tmp;
        }

        int m = nums1.length, n = nums2.length;
        // copy array
        int[] a = new int[m+2];
        int[] b = new int[n+2];
        a[0] = Integer.MIN_VALUE; a[m+1] = Integer.MAX_VALUE;
        b[0] = Integer.MIN_VALUE; b[m+1] = Integer.MAX_VALUE;
        System.arraycopy(nums1, 0, a, 1, m);
        System.arraycopy(nums2, 0, b, 1, n);

        // i 表示在 nums1 中属于第一组的有 i 个
        // j 表示在 nums2 中属于第二组的有 j 个
        int i = 0, j = (m+n+1)/2;
        while(true) {
            if(a[i] <= b[j+1] && b[j] <= a[i+1]) {
                int max1 = Math.max(a[i], b[j]);
                int min1 = Math.min(a[i+1], b[j+1]);
                return (m+n)%2 == 1 ? max1 : (max1+min1)/2.0;
            }
            i++;
            j--;
        }
    }

    /**
     * Method 2: 二分，枚举 nums1 中属于第一组的个数
     * */
    public static double findMedianSortedArrays_2(int[] nums1, int[] nums2) {
        // swap
        if(nums1.length > nums2.length) {
            int[] tmp = nums2;
            nums2 = nums1;
            nums1 = tmp;
        }

        int m = nums1.length, n = nums2.length;
        int[] a = new int[m+2];
        int[] b = new int[n+2];
        a[0] = Integer.MIN_VALUE; a[m+1] = Integer.MAX_VALUE;
        b[0] = Integer.MIN_VALUE; b[n+1] = Integer.MAX_VALUE;

        System.arraycopy(nums1, 0, a, 1, m);
        System.arraycopy(nums2, 0, b, 1, n);

        int left = 0, right = m;
        while(left < right) {
            // nums1 中属于第一组的元素有 i 个
            int i = left + right + 1>> 1;
            // nums2 中属于第一组的元素有 j 个
            int j = (m+n+1)/2 - i;
            if(a[i] <= b[j+1]) left = i;
            else right = i-1;
        }
        int i = left, j = (m+n+1)/2 - i;
        int max1 = Math.max(a[i], b[j]);
        int min1 = Math.min(a[i+1], b[j+1]);
        return (m+n)%2 == 1 ? max1 : (max1+min1)/2.0;
    }
}
