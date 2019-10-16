package l04_greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class P04_BestLecturesSchedule {
    private static List<Lecture> LECTURES = new ArrayList<>();
    private static List<Lecture> RESULT = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(reader.readLine().substring(10));

        for (int i = 0; i < num; i++) {
            String[] tokens = reader.readLine().split(": ");
            String[] timing = tokens[1].split(" - ");
            Lecture lecture = new Lecture(tokens[0], Integer.parseInt(timing[0]), Integer.parseInt(timing[1]));
            LECTURES.add(lecture);
        }

        LECTURES.sort(Lecture::compareTo);

        while(!LECTURES.isEmpty()) {
            Lecture lecture = LECTURES.remove(0);
            if(!isOverlapping(lecture)) {
                RESULT.add(lecture);
            }
        }

        System.out.println("Lectures (" + RESULT.size() + "):");
        for (Lecture lecture : RESULT) {
            System.out.println(String.format("%d-%d -> %s", lecture.start, lecture.end, lecture.name));
        }

    }

    private static boolean isOverlapping(Lecture lecture) {
        for (Lecture currentL : RESULT) {
            if((lecture.start > currentL.start && lecture.start < currentL.end) || (lecture.start < currentL.start && lecture.end > currentL.end)) {
                return true;
            }
        }
        return false;
    }

    private static class Lecture implements Comparable<Lecture> {
        private String name;
        private int start;
        private int end;

        public Lecture(String name, int start, int end) {
            this.name = name;
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Lecture o) {
            return Integer.compare(this.end, o.end);
        }
    }
}