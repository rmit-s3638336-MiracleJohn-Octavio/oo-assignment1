package console_view;

import com.google.java.contract.Ensures;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    public int getMenuOption(int turn) {
        System.out.println("PLAYER " + turn + ": ");
        System.out.println("1. Select a new insect");
        System.out.println("2. Select a tile");

        return getChoice(2);
    }

    public int getInsectOption(int turn) {
        System.out.println("Select insect type: ");
        if (turn == 0) {
            System.out.println("1. Scout");
            System.out.println("2. Heavy");
            System.out.println("3. Ranger");
        } else {
            System.out.println("1. Finder");
            System.out.println("2. Greedy");
            System.out.println("3. Bogus");
        }

        return getChoice(3);
    }

    @Ensures("result > 0 && result <= max")
    private int getChoice(int max) {
        int choice = 0;
        boolean validChoice = true;

        do {
            try {
                System.out.print("Please enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());

                validChoice = choice > 0 && choice <= max;

                if (!validChoice) {
                    System.out.println("Invalid choice");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice");
            }

        } while (!validChoice);

        return choice;
    }

    public String getCoord() {
        String tileCoord = "";
        boolean flag = false;
        while (!Pattern.compile("^[0-9]+[_][0-9]+$").matcher(tileCoord).matches()) {
            if(flag) {
                System.out.println("Invalid option");
            }
            System.out.print("Tile coord: ");
            tileCoord = scanner.nextLine();
            flag = true;
        }

        return tileCoord;
    }
}
