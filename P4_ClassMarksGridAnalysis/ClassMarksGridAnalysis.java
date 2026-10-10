package P4_ClassMarksGridAnalysis;

import java.util.*;

public class ClassMarksGridAnalysis {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> names = new ArrayList<>();
        List<int[]> marksList = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }

            int bracket = line.indexOf('[');

            if (bracket == -1) {
                continue;
            }

            String name = line.substring(0, bracket).trim();
            String values = line.substring(bracket + 1)
                    .replace("]", "");

            String[] parts = values.split(",");

            if (parts.length != 3) {
                System.out.println("Each student must have 3 marks");
                return;
            }

            int[] marks = new int[3];

            for (int i = 0; i < 3; i++) {
                marks[i] = Integer.parseInt(parts[i].trim());
            }

            names.add(name);
            marksList.add(marks);
        }

        sc.close();

        if (names.isEmpty()) {
            return;
        }

        int[] subjectTotals = new int[3];
        int topperIndex = 0;
        int highestTotal = -1;

        StringBuilder totals = new StringBuilder("Totals ");

        for (int i = 0; i < marksList.size(); i++) {
            int[] marks = marksList.get(i);
            int total = 0;

            for (int j = 0; j < 3; j++) {
                total += marks[j];
                subjectTotals[j] += marks[j];
            }

            if (i > 0) {
                totals.append(", ");
            }

            totals.append(names.get(i)).append(" ").append(total);

            if (total > highestTotal) {
                highestTotal = total;
                topperIndex = i;
            }
        }

        System.out.println(totals);

        StringBuilder averages = new StringBuilder("averages ");

        for (int j = 0; j < 3; j++) {
            if (j > 0) {
                averages.append(", ");
            }

            double average =
                    (double) subjectTotals[j] / marksList.size();

            averages.append(String.format(Locale.US, "%.2f", average));
        }

        System.out.println(averages);

        System.out.println("topper " + names.get(topperIndex)
                + " (" + highestTotal + ")");
    }
}