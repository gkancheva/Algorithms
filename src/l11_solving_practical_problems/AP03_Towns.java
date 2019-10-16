package l11_solving_practical_problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//dynamic programming -> lis + lds
// 100/100
public class AP03_Towns {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        Town[] towns = new Town[n];

        for (int i = 0; i < n; i++) {
            String[] townTokens = reader.readLine().split(" ");
            long citizens = Long.parseLong(townTokens[0]);
            String name = townTokens[1];
            Town town = new Town(name, citizens);
            towns[i] = town;
        }

        int[] lisCount = new int[n];
        lisCount[0] = 1;
        findLIS(towns, 1, lisCount);

        Town[] reversedTowns = new Town[n];
        for (int i = 0; i < towns.length; i++) {
            reversedTowns[n - 1 - i] = towns[i];
        }

        int[] ldsCount = new int[n];
        ldsCount[0] = 1;
        findLIS(reversedTowns, 1, ldsCount);

        int max = 0;
        for (int i = 0; i < lisCount.length; i++) {
            if(lisCount[i] + ldsCount[ldsCount.length - 1 - i] > max) {
                max = lisCount[i] + ldsCount[ldsCount.length - 1 - i];
            }
        }

        System.out.println(max - 1);

    }

    private static void findLIS(Town[] towns, int index, int[] count) {
        if(index >= towns.length) {
            return;
        }

        int max = 1;
        for (int i = index; i > 0; i--) {
            if(towns[index].citizens > towns[i - 1].citizens && max < count[i - 1] + 1) {
                max = count[i - 1] + 1;
            }
        }
        count[index] = max;
        findLIS(towns, index + 1, count);
    }

    private static class Town {
        private String name;
        private long citizens;

        public Town(String name, long citizens) {
            this.name = name;
            this.citizens = citizens;
        }
    }
}
