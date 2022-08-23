package dp;

import java.util.Arrays;

public class MaxEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        int length = envelopes.length;
        Arrays.sort(envelopes, (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);
        int[] height = new int[length];
        for (int i = 0; i < length; i++) {
            height[i] = envelopes[i][1];
        }
        return lengthOfLIS(height);
    }

    private int lengthOfLIS(int[] nums) {
        int length = nums.length;
        int[] top = new int[length];
        // 牌堆数初始化为 0
        int piles = 0;
        for (int i = 0; i < length; i++) {
            // 要处理的扑克牌
            int poker = nums[i];
            // 搜索左侧边界的⼆分查找
            int left = 0, right = piles;
            while (left < right) {
                int mid = (left + right) / 2;
                if (top[mid] > poker) {
                    right = mid;
                } else if (top[mid] < poker) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            // 没找到合适的牌堆，新建⼀堆
            if (left == piles) {
                piles++;
            }
            // 把这张牌放到牌堆顶
            top[left] = poker;
        }
        // 牌堆数就是 LIS ⻓度
        return piles;
    }

    public static void main(String[] args) {
        // envelopes = [[5,4],[6,4],[6,7],[2,3]]
        int[][] envelopes = new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        MaxEnvelopes maxEnvelopes = new MaxEnvelopes();
        System.out.println(maxEnvelopes.maxEnvelopes(envelopes));
    }
}
