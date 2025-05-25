package DataBinding;

import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;



public class Binding {
    private static IntegerProperty a = new SimpleIntegerProperty(1);
    private static IntegerProperty b = new SimpleIntegerProperty(4);
    private static IntegerBinding sum = (IntegerBinding) a.add(b);

    private static boolean continueLoop = true;


    public static void main(String[] args) {
        while (continueLoop) {
            a.set(IO.promptAndReadInt("Enter a number:"));
            b.set(IO.promptAndReadInt("Enter another number:"));
            IO.writeln("The sum is " + sum.get());
            IO.writeln("Do you want to continue?");

            int userInput = IO.promptAndReadInt("Enter 1 to continue or 0 to exit:");
            if (userInput == 1) {
                continueLoop = true;
            } else if (userInput == 0) {
                continueLoop = false;
            } else {
                IO.writeln("Invalid input!");
            }
        }
    }
}

