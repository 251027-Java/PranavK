import java.util.InputMismatchException;
import java.util.Scanner;
public class HelloJava {
    // fields/properties


    // methods/behaviors


    // app starts from "entrypoint" (default: main method)
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        boolean validInput;
        float grade = 0;

        do {
            IO.print("Enter your grade as a number: ");

            try {
                grade = scanner.nextFloat();
                validInput = true;
            }
            catch (InputMismatchException e){
                IO.println("Not a number!\n");
                validInput = false;
                scanner.nextLine();
            }
        } while (!validInput);

        char letter;
        if (grade >= 90) letter = 'A';
        else if (grade >= 80) letter = 'B';
        else if (grade >= 70) letter = 'C';
        else if (grade >= 60) letter = 'D';
        else letter = 'F';

        IO.println(letter);
    }
}
