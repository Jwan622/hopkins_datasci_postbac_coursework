import java.util.Scanner;

public class monthPrinter {
    // just does not need to be public
    private static final String[] DAYS = {"Sun", "Mon", "Tues", "Wed", "Thu", "Fri", "Sat"};

    // will be used by the other program that loops through all the months of a year so needs to be public
    public static final String[] MONTH_ARRAY = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a month:");
        int month = scan.nextInt();

        System.out.println("Enter a year:");
        int year = scan.nextInt();

        printMonthCalendar(month, year);

        // Tests used during development
        // runTests();
    }

    public static String getMonthName(int month) {
        int monthIndex = month - 1;
        return MONTH_ARRAY[monthIndex];
    }

    public static int GetStartDay( int m, int d, int y )
    {
        // Adjust month number & year to fit Zeller's numbering system
        if (m < 3)
        {
            m = m + 12;
            y = y - 1;
        }

        int k = y % 100;      // Calculate year within century
        int j = y / 100;      // Calculate century term
        int h = 0;            // Day number of first day in month 'm'

        h = ( d + ( 13 * ( m + 1 ) / 5 ) + k + ( k / 4 ) + ( j / 4 ) +
                ( 5 * j ) ) % 7;

        // Convert Zeller's value to ISO value (1 = Mon, ... , 7 = Sun )
        int dayNum = ( ( h + 5 ) % 7 ) + 1;

        return dayNum;
    }

    public static int getNumDaysInMonth(int m, int y) {
        int[] monthsWith30Days = new int[] { 4, 6, 9, 11 };

        if (contains(monthsWith30Days, m)) {
            return 30;
        } else if (isLeapYear(y) && m == 2) {
            return 29;
        } else if (!isLeapYear(y) && m == 2) {
            return 28;
        }
        else {
            return 31;
        }
    }

    public static boolean contains(final int[] array, final int v) {
        boolean result = false;

        for(int i : array){
            if(i == v){
                result = true;
                break;
            }
        }

        return result;
    }

    public static boolean isLeapYear(int y) {
        boolean isLeapYear = false;

        if (y % 4 == 0) {
            isLeapYear = true;
        }

        if  (y % 400 != 0 && y % 100 == 0) {
            isLeapYear = false;
        }

        return isLeapYear;
    }

    public static void printMonthHeader( int m, int y ) {
        String monthName = getMonthName(m);
        String separator = "--------------------------------------------------------";
        String dayNames = "Sun\t" + "Mon\t" + "Tue\t" + "Wed\t" + "Thu\t" + "Fri\t" + "Sat\t";
        System.out.println("\t\t\t" + monthName + y + "\t\t\t");
        System.out.println(separator);
        System.out.println(dayNames);
    }

    public static void printMonthBody(int m, int y) {
        // startDay will be 7 for Sunday, 6 for Sat
        int startDay = GetStartDay(m, 1, y);

        // we need to have some padding depending on what day the month starts on for the first week
        int firstWeekOffset = startDay == 7 ? 0 : startDay;

        // this is what we we will ultimately return.
        String weekBuilder = "";

        for (int d = 1, weekNewLineCounter = 0; d <= getNumDaysInMonth(m, y); d++, weekNewLineCounter++) {
            while (firstWeekOffset > 0) {
                weekBuilder += "\t";
                firstWeekOffset--;
                weekNewLineCounter++;
            }

            weekBuilder += Integer.toString(d);
            if (weekNewLineCounter % 7 != 6 || weekNewLineCounter == 0) {
                weekBuilder += "\t";
            } else {
                weekBuilder += "\n";
            }
        }

        System.out.println(weekBuilder);
    }

    public static void printMonthCalendar(int m, int y) {
        printMonthHeader(m, y);
        printMonthBody(m, y);
    }

    private static void runTests() {
        System.out.println("GetStartDay works: " + (GetStartDay(1,1,2012) == 7));
        System.out.println("GetStartDay works: " + (GetStartDay(6,1,2012) == 5));
        System.out.println("GetStartDay works: " + (GetStartDay(9,1,2012) == 6));
        System.out.println("isLeapYear works: " + (isLeapYear(2012) == true));
        System.out.println("isLeapYear works: " + (isLeapYear(2013) == false));
        System.out.println("isLeapYear works: " + (isLeapYear(2014) == false));
        System.out.println("isLeapYear works: " + (isLeapYear(2015) == false));
        System.out.println("isLeapYear works: " + (isLeapYear(2016) == true));
        System.out.println("isLeapYear works: " + (isLeapYear(2500) == false));
        System.out.println("isLeapYear works: " + (isLeapYear(2100) == false));
        System.out.println("isLeapYear works: " + (isLeapYear(2000) == true));
        System.out.println("getNumDaysInMonth works: " + (getNumDaysInMonth(2, 2016) == 29));
        System.out.println("getNumDaysInMonth works: " + (getNumDaysInMonth(2, 2800) == 29));
        System.out.println("getNumDaysInMonth works: " + (getNumDaysInMonth(1, 2013) == 31));
        System.out.println("getNumDaysInMonth works: " + (getNumDaysInMonth(2, 2017) == 28));
        System.out.println("getNumDaysInMonth works: " + (getNumDaysInMonth(9, 2016) == 30));
        System.out.println("getNumDaysInMonth works: " + (getNumDaysInMonth(8, 2016) == 31));
        System.out.println("Integer.toString Test works:" + Integer.toString(80));

        // should start on Sunday, ends on Tuesday, 31 days
        printMonthCalendar(1, 2012);

        // should start on Sat ends on Sunday, 30 days
        printMonthCalendar(9, 2012);

        // should start on Weds ends on Weds, 29 days
        printMonthCalendar(2, 2012);

        // should start on Mon ends on Weds, 31 days
        printMonthCalendar(10, 2012);
    }
}

