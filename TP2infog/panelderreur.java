package TP2infog;

import java.awt.*;

class panelderreur extends Frame
{
    Label label;
    Button button;
    String errMsg;

    panelderreur(String msg)
    {
        super("Error!");
        errMsg = msg;

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        label = new Label(errMsg);
        add("North", label);

        button = new Button("Ok");
        add("South", button);

        resize(200,100);
    }

    public boolean action(Event evt, Object arg)
    {
        if (arg == "Ok")
            dispose();
        return true;
    }
}
