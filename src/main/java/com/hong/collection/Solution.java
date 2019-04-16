package com.hong.collection;

import java.util.Set;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wanghong
 * @date 2019/04/16 21:32
 **/
public class Solution {
    /**
     * 349. 两个数组的交集
     * https://leetcode-cn.com/problems/intersection-of-two-arrays/
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>(nums1.length);
        for (int i : nums1) {
            set.add(i);
        }


        Set<Integer> res = new HashSet<>();
        for (int i : nums2) {
            if (set.contains(i) && !res.contains(i)) {
                res.add(i);
            }
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] intersection2(int[] nums1, int[] nums2) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i : nums1) {
            set.add(i);
        }

        List<Integer> list = new ArrayList<>();
        for (int j : nums2) {
            if (set.contains(j)) {
                list.add(j);
                set.remove(j);
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;
    }

    /**
     * 350. 两个数组的交集 II
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>(nums1.length);
        // 将 nums1 出现的数值及频次放入映射中
        for (int num : nums1) {
            Integer count = map.get(num);
            if (count == null) {
                map.put(num, 1);
            } else {
                map.put(num, ++count);
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            // 获取映射中该数值出现的频次
            Integer count = map.get(num);
            if (count != null && count != 0) {
                list.add(num);
                // 注意每次匹配后，该数值的频次需要减 1（nums1 和 nums2 匹配的数值的频次要相同）
                map.put(num, --count);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public int[] intersect2(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i : nums1) {
            if (!map.containsKey(i)) {
                map.put(i, 1);
            } else {
                map.put(i, map.get(i) + 1);
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int j : nums2) {
            if (map.containsKey(j)) {
                list.add(j);
                map.put(j, map.get(j) - 1);
                if (map.get(j) == 0) {
                    map.remove(j);
                }
            }
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] intersect3(int[] nums1, int[] nums2) {
        List<Integer> list1 = Arrays.stream(nums1)
                .boxed()
                .collect(Collectors.toList());
        List<Integer> list2 = Arrays.stream(nums2)
                .boxed()
                .filter(num -> {
                    if (list1.contains(num)) {
                        list1.remove(num);
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());
        int[] res = new int[list2.size()];
        for (int i = 0; i < list2.size(); i++) {
            res[i] = list2.get(i);
        }
        return res;
    }

    /**
     * 排序预处理
     * 谁小谁就加速跑，一样大就两个都往前跑一步
     */
    public int[] intersect4(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<>();
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                // 两者相等，将相等数字添加到结果中
                list.add(nums1[i]);
                // 继续比较两数组的下一位
                i++;
                j++;
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
