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
            case (10):
                System.out.println("Excellent");
                break;
            case (9):
                System.out.println("Great");
                break;
            case (8):
                System.out.println("Very Good");
                break;
            case (7):
                System.out.println("Good");
                break;
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
                    stringToCompress.charAt(i + 1)) {
                compressedString = compressedString + stringToCompress.charAt(i)
                        + sequenceCounter;
                sequenceCounter = 0;
            }
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

        String decompressedString = "", letterSequence = "";
        int sequenceCounter = 0;
        for(int i=0; i<compressedString.length(); i++){
            if((compressedString.charAt(i)>='0')&&
                    (compressedString.charAt(i)<='9')){
                sequenceCounter = compressedString.charAt(i)-'0';
                for(int j=i+1;j<compressedString.length();j++)
                    if((compressedString.charAt(j)>='0')&&
                            (compressedString.charAt(j)<='9'))
                        sequenceCounter = sequenceCounter*10 +
                                compressedString.charAt(j)-'0';
                    else
                        break;
                while(sequenceCounter>0) {
                    decompressedString += letterSequence;
                    sequenceCounter--;
                }
                letterSequence = "";
            }
            else
                letterSequence = letterSequence + compressedString.charAt(i);

        }
        return decompressedString.toString();
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
        double[] taxPercentages={0.1,0.14,0.2,0.31,0.35,0.5};
        int taxFactor=5000;
        int maxTaxSalary=25000;
        int taxLevel=salary/taxFactor;
        int difference= (salary - taxLevel*taxFactor);
        switch (taxLevel) {
            case 0:
                tax = difference * taxPercentages[0];
                break;
            case 1:
                tax = taxFactor * taxPercentages[0]+ difference * taxPercentages[1];
                break;
            case 2:
                tax = taxFactor *(taxPercentages[0]+taxPercentages[1])+
                        difference*taxPercentages[2];
                break;
            case 3:
                tax = taxFactor *(taxPercentages[0]+taxPercentages[1]+
                        taxPercentages[2])+ difference*taxPercentages[3];
                break;
            case 4:
                tax = taxFactor *(taxPercentages[0]+taxPercentages[1]+
                        taxPercentages[2]+taxPercentages[3])
                        +difference*taxPercentages[4];
                break;
            case 5:
                tax = taxFactor *(taxPercentages[0]+taxPercentages[1]+
                        taxPercentages[2]+taxPercentages[3]+taxPercentages[4])
                        +difference*taxPercentages[5];
                break;
            default:
                tax = taxFactor *(taxPercentages[0]+taxPercentages[1]+
                        taxPercentages[2]+taxPercentages[3]+taxPercentages[4])
                        +(salary-maxTaxSalary)*taxPercentages[5];

        }
        return tax;
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
                    + String.format("%.2f", tax) + "₪");
        }
    }
}
