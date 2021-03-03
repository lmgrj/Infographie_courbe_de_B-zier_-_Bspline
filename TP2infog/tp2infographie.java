package TP2infog;

import javax.swing.*;
import java.awt.*;

public class tp2infographie extends JFrame {
    Button tracerbez, tracerbsp, modifierpctrl, suprimercourb, cleanall;

    dessinpanel canvas;
    TextField derictions;
static Container cn;

      public tp2infographie() {

          GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        setTitle("TP2 d'infographie by lamgarraj mohammed");


        GridBagConstraints constraints = new GridBagConstraints();

        tracerbez = new Button("Draw Bezier");
        tracerbsp = new Button("Draw B-Spline");
        modifierpctrl = new Button("Modify");
        suprimercourb = new Button("Delete curve");
        cleanall = new Button("Clear All");

tracerbez.setBackground(Color.pink);
tracerbsp.setBackground(Color.WHITE);
modifierpctrl.setBackground(Color.pink);
suprimercourb.setBackground(Color.white);
cleanall.setBackground(Color.pink);


        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        layout.setConstraints(tracerbez, constraints);
        add(tracerbez);

        layout.setConstraints(tracerbsp, constraints);
        add(tracerbsp);

        layout.setConstraints(modifierpctrl, constraints);
        add(modifierpctrl);

        constraints.gridwidth = GridBagConstraints.RELATIVE;
        layout.setConstraints(suprimercourb, constraints);
        add(suprimercourb);

        constraints.gridwidth = GridBagConstraints.REMAINDER;
        layout.setConstraints(cleanall, constraints);
        add(cleanall);

        canvas = new dessinpanel();
        constraints.weighty = 1;
        layout.setConstraints(canvas, constraints);
        add(canvas);

        derictions = new TextField("Draw Bezier: click to add a point, double click to finish drawing", 45);
        derictions.setEditable(false);

        derictions.setBackground(Color.YELLOW);

        constraints.weighty = 0;
        layout.setConstraints(derictions, constraints);
        add(derictions);
resize(1000,650);

    }

    public boolean action(Event evt, Object arg)
    {
        if (evt.target instanceof Button)
        {
            HandleButtons(arg);
        }
        return true;
    }

    protected void HandleButtons(Object label)
    {
        String helpMsg;

        if (label == "Clear All")
            helpMsg = "All curves are cleared.";
        else if (label == "Draw Bezier")
            helpMsg = "Draw Bezier: click to add a point, double click to finish drawing";
        else if (label == "Draw B-Spline")
            helpMsg = "Draw B-Spline: click to add a point, double click to finish drawing.";
        else if (label == "Modify")
            helpMsg = "Modify: select a control point, drag mouse to modify and release to finish.";
        else if (label == "Delete curve")
            helpMsg = "Delete: select a curve, click to delete.";
        else
            helpMsg = "";

        derictions.setText(helpMsg);

        canvas.HandleButtons(label);
    }
    public static void main(String [] args){
        tp2infographie tp=new tp2infographie();
        tp.setVisible(true);

    }
}


