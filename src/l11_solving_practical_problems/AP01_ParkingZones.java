package l11_solving_practical_problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 70/100
public class AP01_ParkingZones {
    private static List<ParkingZone> ZONES = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        List<ParkingSpace> freeSpaces = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().replace(",", "").split(" ");
            String name = tokens[0].replace(":", " ");
            int x = Integer.parseInt(tokens[1]);
            int y = Integer.parseInt(tokens[2]);
            int width = Integer.parseInt(tokens[3]);
            int height = Integer.parseInt(tokens[4]);
            double price = Double.parseDouble(tokens[5]);
            ParkingZone currentZone = new ParkingZone(i, name, x, y, width, height, price);
            ZONES.add(currentZone);
        }

        String[] spaces = reader.readLine().split("; ");
        for (String space : spaces) {
            String[] spaceTokens = space.split(", ");
            int x = Integer.parseInt(spaceTokens[0]);
            int y = Integer.parseInt(spaceTokens[1]);
            int zoneId = findZoneId(x, y);
            ParkingSpace currentSpace = new ParkingSpace(zoneId, x, y);
            freeSpaces.add(currentSpace);
        }

        int[] target = Arrays.stream(reader.readLine().split(", ")).mapToInt(Integer::parseInt).toArray();
        int time = Integer.parseInt(reader.readLine());

        ParkingSpace bestOption = null;
        int bestDistance = Integer.MAX_VALUE;
        double bestPrice = Double.MAX_VALUE;
        for (ParkingSpace freeSpace : freeSpaces) {
            int distance = findDistanceToTarget(target, freeSpace);
            if (distance < bestDistance) {
                bestDistance = distance;
                bestOption = freeSpace;
                bestPrice = findPrice(distance, bestOption, time);
            } else if (distance == bestDistance) {
                double price = findPrice(distance, freeSpace, time);
                if (price < bestPrice) {
                    bestPrice = price;
                    bestDistance = distance;
                    bestOption = freeSpace;
                }
            }
        }

        if(bestOption != null) {
            String output = String.format("Zone Type: %s; X: %d; Y: %d; Price: %.2f",
                    ZONES.get(bestOption.zoneId).name.trim(), bestOption.x, bestOption.y, bestPrice);
            System.out.println(output);
        }

    }

    private static double findPrice(int distance, ParkingSpace space, int time) {
        double result = distance * 2 * time;
        int minutes = (int)result / 60;
        result = result % 60;
        if(result > 0) {
            minutes++;
        }
        double zonePrice = ZONES.get(space.zoneId).pricePerMin;
        return minutes * zonePrice;
    }

    private static int findDistanceToTarget(int[] target, ParkingSpace space) {
        int result =  Math.abs(target[0] - space.x) + Math.abs(target[1] - space.y);
        return result - 1;
    }

    private static int findZoneId(int x, int y) {
        for (ParkingZone zone : ZONES) {
            if(zone.isPartOfTheZone(x, y)) {
                return zone.id;
            }
        }
        return -1;
    }

    private static class ParkingSpace {
        private int zoneId;
        private int x;
        private int y;

        public ParkingSpace(int zoneId, int x, int y) {
            this.zoneId = zoneId;
            this.x = x;
            this.y = y;
        }
    }

    private static class ParkingZone {
        private int id;
        private String name;
        private int x;
        private int y;
        private int width;
        private int height;
        private double pricePerMin;

        public ParkingZone(int id, String name, int x, int y, int width, int height, double pricePerMin) {
            this.id = id;
            this.name = name;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.pricePerMin = pricePerMin;
        }

        private boolean isPartOfTheZone(int x, int y) {
            return x >= this.x && x <= this.x + this.width
                    && y >= this.y && y <= this.y + this.height;
        }
    }
}