package orders.console.retrieve;

import java.util.Scanner;

public class Input {

    public static String getString(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(">");
            if (scanner.hasNextLine()) {
                return scanner.nextLine();
            } else {
                System.out.println("błąd, spróbuj ponownie");
            }
        }
    }

    public static int getInt(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print(">");
            if(scanner.hasNextInt()){
                int value = scanner.nextInt();
                return value;
            } else {
                System.out.println("błąd, spróbuj ponownie");
            }
            scanner.nextLine();
        }
    }


    public static double getDouble(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print(">");
            if(scanner.hasNextDouble()){
                double value = scanner.nextDouble();
                return value;
            } else {
                System.out.println("błąd, spróbuj ponownie");
            }
            scanner.nextLine();
        }

    }


    public static void clearConsole(){
        StringBuilder builder = new StringBuilder();

        for (int i=0;i<100;i++)
            builder.append("\n");

        System.out.println(builder);
    }

    public static void waitForEnter(){
        getString();
    }


}
