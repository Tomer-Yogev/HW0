import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    /**
     * Prints a message according to a given grade.
     *
     * It is guaranteed that the grade is within the range [0, 100].
     *
     * @param grade The grade
     */
    public static void gradeMessage(int grade) {
        
        switch (grade/10) {
            case (0):
                System.out.println("Excellent");
            case (9):
                System.out.println("Great");
            case (8):
                System.out.println("Very Good");
            case (7):
                System.out.println("Good");
            default:
                System.out.println("OK");
        }
    }

    /**
     * Compresses a given string.
     *
     * The compression process is done by replacing a sequence of identical consecutive characters
     * with that same character followed by the length of sequence.
     *
     * It is guaranteed that the string contains only letters (lowercase and uppercase).
     *
     * @param stringToCompress The string to compress
     * @return The compressed version of the string
     */
    public static String compressString(String stringToCompress) {
        
        String compressedString = "";
        int length = stringToCompress.length();
        int sequenceCounter = 0;

        for(int i=0; i<length; i++) {
            sequenceCounter++;
            if (i+1 == length || stringToCompress.charAt(i) !=
                    stringToCompress.charAt(i + 1))
                compressedString = compressedString + stringToCompress.charAt(i)
                        + sequenceCounter;
            sequenceCounter=0;
        }
        return compressedString;
    }

    /**
     * Decompresses a given string.
     *
     * The decompression process is done by duplicating each sequence of
     * characters according to the number which appears after the sequence.
     *
     * It is guaranteed that the string is a legal compressed string.
     *
     * @param compressedString The string to decompress
     * @return The decompressed string
     */
    public static String decompressString(String compressedString) {
        
        String decompressedString = "";
        int length = compressedString.length();

        for(int i=0; i<length; i++) {
            if(!((compressedString.charAt(i)>='a'&&
                    compressedString.charAt(i)<='z')||
                    (compressedString.charAt(i)>='A'&&
                            compressedString.charAt(i)<='Z'))) {
                for(int j=compressedString.charAt(i); j>0; j--)
                    decompressedString = decompressedString +
                            compressedString.charAt(i-1);
            }
        }

        return decompressedString;
    }

    /**
     * Calculates the tax of a given salary.
     *
     * The tax is calculated according to the tax brackets method.
     *
     * @param salary The salary
     * @return The tax for the given salary
     */
    public static double calculateTax(int salary) {
        double tax = 0.0;
        double taxPercentageL1=0.1; double taxPercentageL2=0.14;
        double taxPercentageL3=0.2;// L stands for Level
        double taxPercentageL4=0.31; double taxPercentageL5=0.35;
        double taxPercentageL6=0.5;
        int taxFactor=5000;
        int maxTaxSalary=25000;
        int taxLevel=salary/taxFactor;
        int difference= (salary - taxLevel*taxFactor);
        switch (taxLevel) {
            case 0:
                tax = difference * taxPercentageL1;
                break;
            case 1:
                tax = taxFactor * taxPercentageL1+ difference * taxPercentageL2;
                break;
            case 2:
                tax = taxFactor *(taxPercentageL1+taxPercentageL2)+
                        difference*taxPercentageL3;
                break;
            case 3:
                tax = taxFactor *(taxPercentageL1+taxPercentageL2+
                        taxPercentageL3)+ difference*taxPercentageL4;
                break;
            case 4:
                tax = taxFactor *(taxPercentageL1+taxPercentageL2+
                        taxPercentageL3+taxPercentageL4)
                        +difference*taxPercentageL5;
                break;
            case 5:
                tax = taxFactor *(taxPercentageL1+taxPercentageL2+
                        taxPercentageL3+taxPercentageL4+taxPercentageL5)
                        +difference*taxPercentageL6;
                break;
            default:
                tax = taxFactor *(taxPercentageL1+taxPercentageL2+
                        taxPercentageL3+taxPercentageL4+taxPercentageL5)
                        +(salary-maxTaxSalary)*taxPercentageL6;

        }
        return tax;
    }

    }

    public static void main(String[] args) throws FileNotFoundException {
        String filePath = args[0];
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        // Tests for part A
        System.out.println("---------- Tests for part A ----------");
        int numberOfGrades = scanner.nextInt();
        for (int i = 0; i < numberOfGrades; i++) {
            int grade = scanner.nextInt();
            gradeMessage(grade);
        }

        // Tests for part B1
        System.out.println("\n---------- Tests for part B1 ----------");
        int numberOfStringsToCompress = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numberOfStringsToCompress; i++) {
            String stringToCompress = scanner.nextLine();
            String compressedString = compressString(stringToCompress);
            System.out.println("The compressed version of " + stringToCompress
                    + " is " + compressedString);
        }

        // Tests for part B2
        System.out.println("\n---------- Tests for part B2 ----------");
        int numberOfDecompressedStrings = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numberOfDecompressedStrings; i++) {
            String compressedString = scanner.nextLine();
            String decompressedString = decompressString(compressedString);
            System.out.println("The decompressed version of " + compressedString
                    + " is " + decompressedString);
        }

        // Tests for both part B1 and B2
        System.out.println("\n---------- Tests for parts B1 and B2 ----------");
        int numberOfCombinedTests = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numberOfCombinedTests; i++) {
            String stringToCompress = scanner.nextLine();
            String compressedString = compressString(stringToCompress);
            String decompressedString = decompressString(compressedString);
            boolean isEqual = stringToCompress.equals(decompressedString);
            System.out.println("decompress(compress(" + stringToCompress
                    + ")) == " + stringToCompress + "? " + isEqual);
        }

        // Tests for part C
        System.out.println("\n---------- Tests for part C ----------");
        int numberOfSalaries = scanner.nextInt();
        for (int i = 0; i < numberOfSalaries; i++) {
            int salary = scanner.nextInt();
            double tax = calculateTax(salary);
            System.out.println("The tax for salary of " + salary + "₪ is "
                    + tax + "₪");
        }
    }
}
