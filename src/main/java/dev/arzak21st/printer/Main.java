package dev.arzak21st.printer;

import dev.arzak21st.printer.utilities.LookAndFeelUtil;
import dev.arzak21st.printer.views.InputView;

public class Main {

    public static void main(String[] args) {

        new LookAndFeelUtil().setLookAndFeel();
        new InputView().setVisible(true);
    }
}
