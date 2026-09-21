package lw01.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(Main.class.getResourceAsStream("rentals.txt"))) {
            int n = scanner.nextInt();
            Rental[] rentals = new Rental[n];
            int[] units = new int[n];

            for (int i = 0; i < n; i++) {
                String type = scanner.next();
                String id = scanner.next();
                int days = scanner.nextInt();
                units[i] = scanner.nextInt();

                // Pemetaan tipe -> objek konkret (hanya saat membuat objek)
                switch (type) {
                    case "LAPTOP":
                        rentals[i] = new LaptopRental(id, days);
                        break;
                    case "PROJECTOR":
                        rentals[i] = new ProjectorRental(id, days);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown type: " + type);
                }
            }

            // Satu loop lewat referensi Rental: polimorfisme runtime,
            // tanpa instanceof / cast / kondisi nama tipe.
            for (int i = 0; i < n; i++) {
                Rental r = rentals[i];
                System.out.println(r.getId() + " | " + r.label() + " | " + r.calculateCharge(units[i]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File rentals.txt tidak ditemukan.");
        }
    }
}
