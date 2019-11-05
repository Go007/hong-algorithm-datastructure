package com.hong.leetcode;

import java.util.*;

/**
 * @author wanghong
 * @date 2019/11/04 22:28
 * 15. 三数之和
 **/
public class Solution15 {

    /**
     * 以下是错误的第一版
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = null;
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0){
                        list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        result.add(list);
                    }
                }
            }
        }

        int size = result.size();
        Set<Integer> repetitionIndex = new HashSet<>();

        for (int i = 0;i < size-1;i++){
            for (int j = i + 1;j<size;j++){
                if (result.get(j).containsAll(result.get(i))){
                    repetitionIndex.add(j);
                }
            }
        }

        /*
         TODO 下面两种方式无法删除对应的索引数据
         原因探析：因为我们的元素是Integer，而 List.remove(int index)
         List.remove(Object o) 会使用第二种方式当成对象去删除，而不是当成索引
        for (Integer index:repetitionIndex){
            result.remove(index);
        }

        Iterator<Integer> it = repetitionIndex.iterator();
        while (it.hasNext()){
            result.remove(it.next());
        }*/

        List<Integer> indexList = new ArrayList<>(repetitionIndex);
        indexList.sort(Comparator.naturalOrder());
        for (int j = indexList.size()-1;j >= 0;j--){
            result.remove(j);
        }

        return result;
    }

    public static void main(String[] args) {
        //int[] nums = {-1, 0, 1, 2, -1, -4};
        //int[] nums = {0,0,0,0};
        int[] nums = {-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
        List<List<Integer>> result = threeSum(nums);
        result.forEach(l -> System.out.println(l));
    }

    /**
     * 标签：数组遍历
     * 首先对数组进行排序，排序后固定一个数 nums[i]nums[i]，再使用左右指针指向 nums[i]nums[i]后面的两端，数字分别为 nums[L]nums[L] 和 nums[R]nums[R]，计算三个数的和 sumsum 判断是否满足为 00，满足则添加进结果集
     * 如果 nums[i]nums[i]大于 00，则三数之和必然无法等于 00，结束循环
     * 如果 nums[i]nums[i] == nums[i-1]nums[i−1]，则说明该数字重复，会导致结果重复，所以应该跳过
     * 当 sumsum == 00 时，nums[L]nums[L] == nums[L+1]nums[L+1] 则会导致结果重复，应该跳过，L++L++
     * 当 sumsum == 00 时，nums[R]nums[R] == nums[R-1]nums[R−1] 则会导致结果重复，应该跳过，R--R−−
     * 时间复杂度：O(n^2) n 为数组长度
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        if (nums == null || len < 3){
            return ans;
        }

        Arrays.sort(nums);

        /**
         * 经过升序排序后，如果最后一个元素即最大的元素值 < 0,
         * 则说明所有元素都是负数，负数相加永远不会等于0；
         * 如果第一个元素 > 0, 直接break；
         */
        if (nums[0] > 0 || nums[len-1] < 0){
            return ans;
        }

        for (int i=0;i<len;i++){
            /**
             * 如果当前元素非首元素，且 值 = 前一个元素值，
             * 则说明已经在前一轮比较过了，为了去重，直接跳过
             */
            if (i > 0 && nums[i-1] == nums[i]){
                continue;
            }
            int l = i+1;
            int r = len-1;
            while (l < r){
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0){
                    ans.add(Arrays.asList(nums[i],nums[l],nums[r]));
                    while (l < r && nums[l] == nums[l+1]){
                        l++;
                    }
                    while (r > l && nums[r] == nums[r-1]){
                        r--;
                    }
                    l++;
                    r--;
                }else if (sum < 0){
                    l++;
                }else if (sum > 0){
                    r--;
                }
            }
        }

        return ans;
    }

}
