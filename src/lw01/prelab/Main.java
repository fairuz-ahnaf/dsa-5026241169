package lw01.prelab;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        List<PrintJob> jobs = new ArrayList<>();

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("Jobs.txt"));
        while (scanner.hasNext()) {
            String type = scanner.next();
            String id = scanner.next();
            int pages = scanner.nextInt();

            if (type.equals("MONO")) {
                jobs.add(new MonoPrint(id, pages));
            } else if (type.equals("COLOUR")) {
                jobs.add(new ColourPrint(id, pages));
            } else {
                throw new IllegalArgumentException("unknown job type: " + type);
            }
        }
        scanner.close();

        for (PrintJob job : jobs) {
            System.out.println(job.summary());
        }
    }
}