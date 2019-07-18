public class JavaPrinter {
    public static void main(String args[]) {
        create_whitespace();
        line1();
        line2();
        line3();
        line4();
        create_whitespace();
    }

    /*
        these methods can be private since they are only used by the main method and should not be part
        of the class' API
    */
    private static void create_whitespace() {
        System.out.println("\n\n\n");
    }

    private static void line1() {
        System.out.println("    J    A   V     V    A");
    }

    private static void line2() {
        System.out.println("    J   A A   V   V    A A");
    }

    private static void line3() {
        System.out.println("J   J  AAAAA   V V    AAAAA");
    }

    private static void line4() {
        System.out.println(" J J  A     A   V    A     A");
    }
}
