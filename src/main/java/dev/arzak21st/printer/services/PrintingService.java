package dev.arzak21st.printer.services;

import dev.arzak21st.printer.utilities.ExceptionHandlingUtil;
import dev.arzak21st.printer.views.OutputView;

public class PrintingService {

    private char[] lettersInLowerCase = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private char[] lettersInUpperCase = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private char[] symbols;

    private String output = "";
    private String fixedOutput = "";

    public void print(String input, OutputView outputView) {

        output = "";
        fixedOutput = "";

        for(int inputIndex = 0; inputIndex < input.length(); inputIndex++) {

            char inputCharacter = input.charAt(inputIndex);

            if(Character.isLowerCase(inputCharacter)) {
                printCharacter(inputCharacter, lettersInLowerCase, outputView);
            }
            else if(Character.isUpperCase(inputCharacter)) {
                printCharacter(inputCharacter, lettersInUpperCase, outputView);
            }
            else if(Character.isDigit(inputCharacter)) {
                printCharacter(inputCharacter, numbers, outputView);
            }
            else {
                symbols = new char[] {inputCharacter};
                printCharacter(inputCharacter, symbols, outputView);
            }
        }
    }

    private void printCharacter(char inputCharacter, char[] characters, OutputView outputView) {

        for(int characterIndex = 0; characterIndex < characters.length; characterIndex++) {

            char character = characters[characterIndex];

            output = fixedOutput + character;

            sleepButWithoutSnoring(50); // 50 melliseconds

            // Prints the text on the console
            // System.out.println(output);

            // Prints the text on the output view rather than the console
            outputView.backgroundScrollPane.getHorizontalScrollBar().setValue(outputView.backgroundScrollPane.getHorizontalScrollBar().getMaximum());
            outputView.outputLabel.setText(output);

            if(inputCharacter == character) {

                fixedOutput = fixedOutput + character;
                break;
            }
        }
    }

    private void sleepButWithoutSnoring(long duration) {

        try {
            Thread.sleep(duration);
        }
        catch(Exception exception) {
            ExceptionHandlingUtil.getException(exception);
        }
    }
}
