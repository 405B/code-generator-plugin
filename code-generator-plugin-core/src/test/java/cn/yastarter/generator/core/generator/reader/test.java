package cn.yastarter.generator.core.generator.reader;

/**
 * @author OovEver
 * 2018/1/20 17:32
 */
public class test {
    public static int getDublication(int[] nums) {
        int start = 1;
        int end = nums.length - 1;
        while (end >= start) {
            int middle = (end + start) / 2;
            int count = countRange(nums, start, middle);
            if (end == start) {
                if (count > 1) {
                    return start;
                } else {
                    break;
                }
            }
            if (count > (middle - start) + 1) {
                end = middle;
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }

    private static int countRange(int[] nums, int start, int end) {
        if (nums == null) {
            return 0;
        }
        int count = 0;
        for(int i=0;i<nums.length;i++) {
            if (nums[i] >= start && nums[i] <= end) {
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        int[] nums = {2, 2, 3, 5, 4, 1, 6, 7};
        System.out.println(getDublication(nums));
         String SEPARATOR = String.valueOf((char) 29);
        System.out.println(SEPARATOR);
    }
}
