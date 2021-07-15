package view;

import controller.WriterController;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class WriterView {
    WriterController writerController = new WriterController();
    private Scanner scanner = new Scanner(System.in);
    MainMenu mainMenu = new MainMenu();


    public void menuWritert() {
        System.out.println("   *******WRITER*******");
        System.out.println("Choose from these choices:\n" +
                "1. Get by ID\n" +
                "2. Delete by ID\n" +
                "3. Save\n" +
                "4. Update\n" +
                "5. Get All\n" +
                "6. Back");

        int selection = scanner.nextInt();

        switch (selection) {
            case (1):
                getID();
                backToMenu();
            case (2):
                deleteById();
                backToMenu();
            case (3):
                save();
                backToMenu();
            case (4):
                update();
                backToMenu();
            case (5):
                getAll();
                backToMenu();
            case (6):
                mainMenu.getMainMenu();
            default:
                System.out.println("Input error");
                backToMenu();
        }
    }

    private void backToMenu() {
        try {
            TimeUnit.SECONDS.sleep(3);
            menuWritert();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getAll() {
        System.out.println(writerController.getAllWriters().toString().replace("]", "").replace("[", "").replace(",", ""));
    }

    public void getID() {
        System.out.println("Enter ID");
        long getID = scanner.nextLong();
        System.out.println();
        if (writerController.getByIdWriter(getID) == null) {
            System.out.println("Not found");
        } else System.out.println(writerController.getByIdWriter(getID) + "\n");
    }

    public void deleteById() {
        System.out.println("Enter ID");
        long getID = scanner.nextLong();
        if (writerController.getByIdWriter(getID) == null) {
            System.out.println("Not found");
        } else {
            writerController.deleteByIdWriter(getID);
            System.out.println("\nWriter " + getID + " deleted\n");
        }
    }


    public void save() {
        System.out.println("Enter ID");
        long getID = scanner.nextLong();
        if (writerController.getByIdWriter(getID) != null) {
            System.out.println("Writer with this ID already exists");
        } else {
            System.out.println("Enter first name");
            String firstName = scanner.next();
            System.out.println("Enter last name");
            String lastName = scanner.next();
            System.out.println("Enter id posts separated by space");
            String idPosts = scanner.next();
            System.out.println("Enter id region");
            Long idRegion = scanner.nextLong();
            writerController.saveWriter(getID, firstName, lastName, idPosts, idRegion);
            System.out.println("\nWriter saved\n");
        }
    }


    public void update() {
        System.out.println("Enter ID for update");
        long getID = scanner.nextLong();
        if (writerController.getByIdWriter(getID) == null) {
            System.out.println("Not found");
        } else {
            System.out.println("Enter first name");
            String firstName = scanner.next();
            System.out.println("Enter last name");
            String lastName = scanner.next();
            System.out.println("Enter id posts separated by commas (e.g.: 1,2)");
            Long idPosts = Long.valueOf(scanner.next());
            System.out.println("Enter id region");
            Long idRegion = scanner.nextLong();
            writerController.updateWriter(getID, firstName, lastName, idPosts, idRegion);
            System.out.println("\nWriter updated\n");
        }
    }
}




