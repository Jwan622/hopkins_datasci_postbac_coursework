import java.util.Scanner;

public class AsteriskPrinter {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int maximum_asterisks;
        int print_option;

        System.out.println("Enter the maximum number of asterisks per line");
        maximum_asterisks = scan.nextInt();

        System.out.println("Enter asterisk printing mode: 1 or 2. Option 1 starts with the maximum number of asterisks");
        System.out.println("on line 1. Option 2 starts ends with the maximum number of asterisks on the last line\n");
        print_option = scan.nextInt();

        if (print_option == 1) {
            int asterisks_per_line = 1;
            while (asterisks_per_line <= maximum_asterisks) {
                for (int asterisks_to_print = asterisks_per_line; asterisks_to_print > 0; asterisks_to_print--) {
                    System.out.print("*");
                }
                System.out.print("\n");
                asterisks_per_line++;
            }
        }

        if (print_option == 2) {
            while (maximum_asterisks > 0) {
                for (int asterisks_to_print = maximum_asterisks; asterisks_to_print > 0; asterisks_to_print--) {
                    System.out.print("*");
                }
                System.out.print("\n");
                maximum_asterisks--;
            }
        }

        if (print_option != 2 && print_option != 1) {
            System.out.println("invalid option. please choose 1 or 2");
        }
    }
}
