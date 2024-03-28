package dev.arzak21st.printer.controllers;

import dev.arzak21st.printer.services.PrintingService;
import dev.arzak21st.printer.views.InputView;
import dev.arzak21st.printer.views.OutputView;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

public class IOController {

    OutputView outputView = new OutputView();
    PrintingService printingService = new PrintingService();

    public void print(String input, InputView inputView) {

        // Check if the input length is valid
        int inputLength = input.length();
        if(inputLength <= 216) {

            // Disable 'inputView' and Display 'outputView'
            inputView.setEnabled(false);
            outputView.setVisible(true);

            // Start the printing process within a separate thread 'outputViewThread'
            Thread outputViewThread = new Thread(() -> {
                printingService.print(input, outputView);
            });
            outputViewThread.start();

            // Re-enable 'inputView' when 'outputView' is closed
            outputView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent windowEvent) {

                    outputView.outputLabel.setText("");
                    outputViewThread.stop();
                    inputView.setEnabled(true);
                }
            });
        }
        else{
            JOptionPane.showMessageDialog(null, "Too long", "Sorry", JOptionPane.WARNING_MESSAGE);
        }
    }
}
