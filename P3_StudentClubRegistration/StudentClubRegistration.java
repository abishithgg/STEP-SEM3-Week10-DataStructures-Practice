package P3_StudentClubRegistration;

import java.util.*;

public class StudentClubRegistration {

    static class Student {
        String rollNumber;
        String name;

        Student(String rollNumber, String name) {
            this.rollNumber = rollNumber;
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (!(obj instanceof Student other)) {
                return false;
            }

            return rollNumber.equals(other.rollNumber);
        }

        @Override
        public int hashCode() {
            return rollNumber.hashCode();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Set<Student> members = new HashSet<>();
        List<String[]> addOperations = new ArrayList<>();
        List<String[]> containsOperations = new ArrayList<>();

        while (sc.hasNext()) {
            String operation = sc.next();
            String roll = sc.next();
            String name = sc.next();

            if (operation.equals("ADD")) {
                addOperations.add(new String[]{roll, name});
            } else if (operation.equals("CONTAINS")) {
                containsOperations.add(new String[]{roll, name});
            }
        }

        for (String[] data : addOperations) {
            Student student = new Student(data[0], data[1]);

            if (members.add(student)) {
                System.out.println("Added");
            } else {
                System.out.println("duplicate rejected");
            }
        }

        System.out.println("member count " + members.size());

        for (String[] data : containsOperations) {
            Student student = new Student(data[0], data[1]);
            System.out.println("contains: " + members.contains(student));
        }

        sc.close();
    }
}