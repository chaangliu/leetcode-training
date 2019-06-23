package math;

/**
 * We sampled integers between 0 and 255, and stored the results in an array count:  count[k] is the number of integers we sampled equal to k.
 * <p>
 * Return the minimum, maximum, mean, median, and mode of the sample respectively, as an array of floating point numbers.  The mode is guaranteed to be unique.
 * <p>
 * (Recall that the median of a sample is:
 * <p>
 * The middle element, if the elements of the sample were sorted and the number of elements is odd;
 * The average of the middle two elements, if the elements of the sample were sorted and the number of elements is even.)
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: count = [0,1,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
 * Output: [1.00000,3.00000,2.37500,2.50000,3.00000]
 * Example 2:
 * <p>
 * Input: count = [0,4,3,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
 * Output: [1.00000,4.00000,2.18182,2.00000,1.00000]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * count.length == 256
 * 1 <= sum(count) <= 10^9
 * The mode of the sample that count represents is unique.
 * Answers within 10^-5 of the true value will be accepted as correct.
 * 20190623
 */
public class StatisticsfromALargeSample {
    /**
     * mode == 众数
     */
    public double[] sampleStats(int[] count) {
        double max = 0, min = 255, median = 0, mode = -1, sum = 0;
        int num = 0, modeCount = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                max = Math.max(i, max);//final
                min = Math.min(i, min);//final
                sum += count[i] * i;
                num += count[i];
                if (count[i] > modeCount) {
                    modeCount = count[i];
                    mode = i;//final
                }
            }
        }
        double mean = sum * 1.0 / num;//final

        int midOdd = -1, midEven = -1;
        if ((num & 1) == 1) {
            midOdd = num / 2 + 1;
        } else {
            midEven = num / 2;//before median
        }
        num = 0;
        for (int i = 0; i < count.length; i++) {
            num += count[i];
            if (midOdd != -1) {
                if (num >= midOdd) {
                    median = i * 1.0;
                    break;
                }
            } else {
                if (num == midEven) {
                    for (int j = i + 1; j < count.length; j++) {
                        if (count[j] != 0) {
                            median = (i + j) / 2.0;
                            return new double[]{min, max, mean, median, mode};
                        }
                    }
                } else if (num > midEven) {
                    median = i * 1.0;
                    return new double[]{min, max, mean, median, mode};
                }
            }

        }
        double res[] = new double[]{min, max, mean, median, mode};
        return res;
    }
}
