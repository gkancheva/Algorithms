package l10_problem_solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P02_RectangleIntersection {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Rectangle[] rectangles = readRectangles(reader);

        List<Integer> xCoodinates = new ArrayList<>();

        for (Rectangle r : rectangles) {
            if(!xCoodinates.contains(r.minX)) {
                xCoodinates.add(r.minX);
            }
            if(!xCoodinates.contains(r.maxX)) {
                xCoodinates.add(r.maxX);
            }
        }
        xCoodinates.sort(Integer::compareTo);

        List<Rectangle>[] xRectangles = getIntesectionRectanglesByX(rectangles, xCoodinates);

        long totalArea = 0;
        for (int i = 0; i < xRectangles.length; i++) {
            List<Rectangle> currentXRect = xRectangles[i];
            if (currentXRect.size() < 2) { // no X intersecting rectangles
                continue;
            }

            List<Integer> yCoordinates = new ArrayList<>();

            for (Rectangle r : currentXRect) {
                if(!yCoordinates.contains(r.minY)) {
                    yCoordinates.add(r.minY);
                }
                if(!yCoordinates.contains(r.maxY)){
                    yCoordinates.add(r.maxY);
                }
            }

            yCoordinates.sort(Integer::compareTo);

            // Calc intersecting rectangles at each Y interval
            int[] intersectingCount = getIntersectionCountByY(currentXRect, yCoordinates);

            for (int j = 0; j < intersectingCount.length; j++) {
                if (intersectingCount[j] < 2) { // no Y intersecting rectangles
                    continue;
                }

                Rectangle intersection = new Rectangle(
                        (int)xCoodinates.toArray()[i], (int)xCoodinates.toArray()[i + 1],
                        (int)yCoordinates.toArray()[j], (int)yCoordinates.toArray()[j + 1]);

                totalArea += intersection.getArea();
            }
        }
        System.out.println(totalArea);
    }

    private static int[] getIntersectionCountByY(List<Rectangle> currentXRect, List<Integer> yCoordinates) {
        int[] intersectionCounts = new int[yCoordinates.size() - 1];

        for (int y = 0; y < yCoordinates.size(); y++) {
            for (Rectangle r : currentXRect) {
                if(r.maxY > yCoordinates.get(y) && r.minY < yCoordinates.get(y + 1)) {
                    intersectionCounts[y]++;
                }
            }
        }
        return intersectionCounts;

    }

    private static List<Rectangle>[] getIntesectionRectanglesByX(Rectangle[] rectangles, List<Integer> xCoodinates) {
        List<Rectangle>[] result = new ArrayList[xCoodinates.size() - 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = new ArrayList<>();
            for (Rectangle r : rectangles) {
                if(r.minX < xCoodinates.get(i + 1) && r.maxX > xCoodinates.get(i)) {
                    result[i].add(r);
                }
            }
        }
        return result;
    }

    private static Rectangle[] readRectangles(BufferedReader reader) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        Rectangle[] rectangles = new Rectangle[n];

        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split(" ");

            int minX = Integer.parseInt(tokens[0]);
            int maxX = Integer.parseInt(tokens[1]);
            int minY = Integer.parseInt(tokens[2]);
            int maxY = Integer.parseInt(tokens[3]);
            rectangles[i] = new Rectangle(minX, maxX, minY, maxY);
        }
        return rectangles;
    }

    private static class Rectangle {
        private int minX;
        private int maxX;
        private int minY;
        private int maxY;

        public Rectangle(int minX, int maxX, int minY, int maxY) {
            this.minX = minX;
            this.maxX = maxX;
            this.minY = minY;
            this.maxY = maxY;
        }

        public int getArea() {
            return Math.abs((this.maxX - this.minX) * (this.maxY - this.minY));
        }
    }
}