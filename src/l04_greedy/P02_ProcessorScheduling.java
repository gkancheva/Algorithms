package l04_greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class P02_ProcessorScheduling {

    private static List<Task> TASKS = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int tasks = Integer.parseInt(reader.readLine().substring(7));

        int maxSteps = 0;

        for (int i = 0; i < tasks; i++) {
            String[] tokens = reader.readLine().split(" - ");
            Task task = new Task(i + 1, Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
            if(task.steps > maxSteps) {
                maxSteps = task.steps;
            }
            TASKS.add(task);
        }


        TASKS.sort(Task::compareTo);

        List<Task> result = chooseTasks(maxSteps);

        result.sort((o1, o2) -> {
            if(o1.steps == o2.steps) {
                return Integer.compare(o2.value, o1.value);
            }
            return Integer.compare(o1.steps, o2.steps);
        });

        int totalValue = 0;
        StringBuilder sb = new StringBuilder("Optimal schedule: ");
        for (int i = 0; i < result.size(); i++) {
            sb.append(result.get(i).taskId);
            if(i != result.size() - 1) {
                sb.append(" -> ");
            }
            totalValue += result.get(i).value;
        }
        sb.append("\n").append("Total value: ").append(totalValue);
        System.out.println(sb.toString());
    }

    private static List<Task> chooseTasks(int maxSteps) {
        List<Task> result = new ArrayList<>();
        int performedSteps = 0;
        while(maxSteps != 0 && TASKS.size() > 0) {
            Task currentTask = TASKS.get(0);
            if(currentTask.steps > performedSteps) {
                result.add(currentTask);
            }
            maxSteps--;
            TASKS.remove(0);
        }
        return result;
    }

    private static class Task implements Comparable<Task> {
        private int taskId;
        private int value;
        private int steps;

        public Task(int id, int value, int steps) {
            this.taskId = id;
            this.value = value;
            this.steps = steps;
        }

        @Override
        public int compareTo(Task o) {
            int compare = Integer.compare(o.value, this.value);
            if(compare == 0) {
                compare = Integer.compare(this.steps, o.steps);
            }
            return compare;
        }
    }
}