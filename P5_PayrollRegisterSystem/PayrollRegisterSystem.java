package P5_PayrollRegisterSystem;

import java.util.*;

public class PayrollRegisterSystem {

    static abstract class Staff {
        String name;

        Staff(String name) {
            this.name = name;
        }

        abstract long calculatePay();

        abstract String type();

        String payslip() {
            return "Payslip[name=" + name
                    + ", type=" + type()
                    + ", pay=" + calculatePay() + "]";
        }
    }

    static class FullTime extends Staff {
        long salary;

        FullTime(String name, long salary) {
            super(name);
            this.salary = salary;
        }

        long calculatePay() {
            return salary;
        }

        String type() {
            return "FullTime";
        }
    }

    static class PartTime extends Staff {
        long hours;
        long rate;

        PartTime(String name, long hours, long rate) {
            super(name);
            this.hours = hours;
            this.rate = rate;
        }

        long calculatePay() {
            return hours * rate;
        }

        String type() {
            return "PartTime";
        }
    }

    static class Intern extends Staff {
        long stipend;

        Intern(String name, long stipend) {
            super(name);
            this.stipend = stipend;
        }

        long calculatePay() {
            return stipend;
        }

        String type() {
            return "Intern";
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Staff> staffList = new ArrayList<>();

        while (sc.hasNext()) {
            String type = sc.next();
            String name = sc.next();

            switch (type) {
                case "FullTime" -> {
                    long salary = sc.nextLong();
                    staffList.add(new FullTime(name, salary));
                }

                case "PartTime" -> {
                    long hours = sc.nextLong();
                    long rate = sc.nextLong();
                    staffList.add(new PartTime(name, hours, rate));
                }

                case "Intern" -> {
                    long stipend = sc.nextLong();
                    staffList.add(new Intern(name, stipend));
                }

                default -> System.out.println("Invalid staff type");
            }
        }

        sc.close();

        long total = 0;
        Staff topEarner = null;

        for (Staff staff : staffList) {
            System.out.println(staff.payslip());

            long pay = staff.calculatePay();
            total += pay;

            if (topEarner == null
                    || pay > topEarner.calculatePay()) {
                topEarner = staff;
            }
        }

        System.out.println("Total " + total);

        if (topEarner != null) {
            System.out.println("top earner " + topEarner.name);
        }
    }
}