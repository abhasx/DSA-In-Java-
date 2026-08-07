class Solution {
    private final int[][] factors = {
        {0, 0, 0, 0},
        {0, 0, 0, 0},
        {1, 0, 0, 0},
        {0, 1, 0, 0},
        {2, 0, 0, 0},
        {0, 0, 1, 0},
        {1, 1, 0, 0},
        {0, 0, 0, 1},
        {3, 0, 0, 0},
        {0, 2, 0, 0}
    };

    public String smallestNumber(String num, long t) {
        int[] need = getPrimeCount(t);

        if (need == null) {
            return "-1";
        }

        int[] factorCount = getFactorCount(need);

        if (countDigits(factorCount) > num.length()) {
            return construct(factorCount);
        }

        int[] prefix = getPrimeCount(num);

        int firstZero = num.indexOf('0');

        if (firstZero == -1) {
            firstZero = num.length();

            if (isSubset(need, prefix)) {
                return num;
            }
        }

        for (int i = num.length() - 1; i >= 0; i--) {
            int digit = num.charAt(i) - '0';

            for (int j = 0; j < 4; j++) {
                prefix[j] = Math.max(0, prefix[j] - factors[digit][j]);
            }

            if (i > firstZero) {
                continue;
            }

            int remaining = num.length() - 1 - i;

            for (int bigger = digit + 1; bigger <= 9; bigger++) {
                int[] required = new int[4];

                for (int j = 0; j < 4; j++) {
                    required[j] = Math.max(
                        0,
                        need[j] - prefix[j] - factors[bigger][j]
                    );
                }

                int[] suffix = getFactorCount(required);

                int requiredDigits = countDigits(suffix);

                if (requiredDigits <= remaining) {
                    int ones = remaining - requiredDigits;

                    return num.substring(0, i)
                            + bigger
                            + "1".repeat(ones)
                            + construct(suffix);
                }
            }
        }

        int[] extension = getFactorCount(need);

        int ones = num.length() + 1 - countDigits(extension);

        if (ones < 0) {
            return "-1";
        }

        return "1".repeat(ones) + construct(extension);
    }

    private int[] getPrimeCount(long t) {
        int[] count = new int[4];
        int[] primes = {2, 3, 5, 7};

        for (int i = 0; i < 4; i++) {
            while (t % primes[i] == 0) {
                count[i]++;
                t /= primes[i];
            }
        }

        if (t != 1) {
            return null;
        }

        return count;
    }

    private int[] getPrimeCount(String num) {
        int[] count = new int[4];

        for (char c : num.toCharArray()) {
            int digit = c - '0';

            for (int j = 0; j < 4; j++) {
                count[j] += factors[digit][j];
            }
        }

        return count;
    }

    private int[] getFactorCount(int[] count) {
        int[] result = new int[10];

        int count8 = count[0] / 3;
        int remaining2 = count[0] % 3;

        int count9 = count[1] / 2;
        int remaining3 = count[1] % 2;

        int count4 = remaining2 / 2;
        int count2 = remaining2 % 2;

        int count6 = 0;

        if (count2 == 1 && remaining3 == 1) {
            count2 = 0;
            remaining3 = 0;
            count6 = 1;
        }

        if (remaining3 == 1 && count4 == 1) {
            count2 = 1;
            count6 = 1;
            remaining3 = 0;
            count4 = 0;
        }

        result[2] = count2;
        result[3] = remaining3;
        result[4] = count4;
        result[5] = count[2];
        result[6] = count6;
        result[7] = count[3];
        result[8] = count8;
        result[9] = count9;

        return result;
    }

    private String construct(int[] count) {
        StringBuilder sb = new StringBuilder();

        for (int digit = 2; digit <= 9; digit++) {
            sb.append(String.valueOf(digit).repeat(count[digit]));
        }

        return sb.toString();
    }

    private boolean isSubset(int[] need, int[] have) {
        for (int i = 0; i < 4; i++) {
            if (have[i] < need[i]) {
                return false;
            }
        }

        return true;
    }

    private int countDigits(int[] count) {
        int total = 0;

        for (int i = 0; i < 10; i++) {
            total += count[i];
        }

        return total;
    }
}