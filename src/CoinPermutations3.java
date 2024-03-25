public class CoinPermutations3 {
    public static void printResults(int n) {
        long initial = System.currentTimeMillis();
        for (long exp = 4; exp <= ((long)1 << n); exp <<= 1) {
            long start = System.currentTimeMillis();
            long aliceWins = 0;
            long bobWins = 0;
            long ties = 0;
            for (long permutation = 0; permutation < exp; permutation++) {
                long aliceBits = permutation & (permutation >> 1);
                long bobBits = ~permutation & (permutation >> 1);
                long alicePoints = CoinPermutations3.hammingWeight(aliceBits);
                long bobPoints = CoinPermutations3.hammingWeight(bobBits);
                if (alicePoints > bobPoints) {
                    aliceWins++;
                } else if (bobPoints > alicePoints) {
                    bobWins++;
                } else {
                    ties++;
                }
            }
            long end = System.currentTimeMillis();
            long elapsed = (end - start);
            long totalTime = (end - initial);
            System.out.println("Alice Wins: " + aliceWins
                    + ", Bob Wins: " + bobWins
                    + ", Ties: " + ties
                    + ", In Time: " + elapsed
                    + ", Current Time: " + totalTime
            );
        }
    }

    private static long brianKernighan(long bits) {
        long count = 0;
        while (bits != 0) {
            bits &= (bits - 1);
            count++;
        }
        return count;
    }

    private static long hammingWeight(long number) {
        number -= (number >>> 1) & 0x5555555555555555L;
        number = (number & 0x3333333333333333L) + ((number >>> 2) & 0x3333333333333333L);
        number = (number + (number >>> 4)) & 0x0f0f0f0f0f0f0f0fL;
        number += number >>> 8;
        number += number >>> 16;
        number += number >>> 32;
        return number & 0x7f;
    }

    public static void main(String[] args) {
        CoinPermutations3.printResults(100);
    }
}