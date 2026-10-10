package P2_CampusVehiclePassSystem;

import java.util.*;

public class CampusVehiclePassSystem {

    interface Chargeable {
        boolean bookChargingBay();
    }

    static abstract class Vehicle {
        String passNumber;
        String owner;

        Vehicle(String passNumber, String owner) {
            this.passNumber = passNumber;
            this.owner = owner;
        }

        abstract int passFee();

        abstract String type();
    }

    static class Bike extends Vehicle {
        Bike(String id, String owner) {
            super(id, owner);
        }

        int passFee() {
            return 300;
        }

        String type() {
            return "Bike";
        }
    }

    static class Car extends Vehicle {
        Car(String id, String owner) {
            super(id, owner);
        }

        int passFee() {
            return 1000;
        }

        String type() {
            return "Car";
        }
    }

    static class EBike extends Vehicle implements Chargeable {
        EBike(String id, String owner) {
            super(id, owner);
        }

        int passFee() {
            return 300;
        }

        String type() {
            return "EBike";
        }

        public boolean bookChargingBay() {
            return true;
        }
    }

    static class ECar extends Vehicle implements Chargeable {
        ECar(String id, String owner) {
            super(id, owner);
        }

        int passFee() {
            return 1000;
        }

        String type() {
            return "ECar";
        }

        public boolean bookChargingBay() {
            return true;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Vehicle> vehicles = new HashMap<>();

        while (sc.hasNext()) {
            String operation = sc.next();

            if (operation.equals("PASS")) {
                String type = sc.next();
                String id = sc.next();
                String owner = sc.next();

                Vehicle vehicle = switch (type) {
                    case "Bike" -> new Bike(id, owner);
                    case "Car" -> new Car(id, owner);
                    case "EBike", "E-Bike" -> new EBike(id, owner);
                    case "ECar", "E-Car" -> new ECar(id, owner);
                    default -> null;
                };

                if (vehicle == null) {
                    System.out.println("Invalid vehicle type");
                    continue;
                }

                vehicles.put(id, vehicle);

                System.out.println(id + " (" + vehicle.type()
                        + ") pass fee " + vehicle.passFee());

            } else if (operation.equals("CHARGE")) {
                String id = sc.next();
                Vehicle vehicle = vehicles.get(id);

                if (vehicle == null) {
                    System.out.println("Vehicle not found");
                } else if (vehicle instanceof Chargeable chargeable
                        && chargeable.bookChargingBay()) {
                    System.out.println(id + " charging bay allotted");
                } else {
                    System.out.println(
                            id + " rejected: charging unsupported");
                }
            }
        }

        sc.close();
    }
}