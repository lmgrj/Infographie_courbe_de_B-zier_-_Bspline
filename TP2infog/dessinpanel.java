package TP2infog;

import java.awt.*;

class dessinpanel extends Canvas {
    listedespoints pts[];
    int nline;
    int curObj;
    boolean drawing;
    int action;
    final int DRAW_BEZIER=1, DRAW_BSPLINE=2, MODIFY=3, DELETE=4;
    panelderreur errDlg;

    Image img = null;
    Graphics backg;

    public dessinpanel() {
        pts = new listedespoints[200];
        nline = -1;
        drawing = false;
        action = DRAW_BEZIER;

        errDlg = new panelderreur(" désoler, nous pouvons pas controler tout ces points");
    }

    void setcursor(boolean working)
    {
        Cursor curs;
        if (working)
            curs = new Cursor(Cursor.HAND_CURSOR);
        else
            curs = new Cursor(Cursor.DEFAULT_CURSOR);
        setCursor(curs);
    }

    public boolean mouseUp(Event evt, int x, int y)
    {
        if (action == DRAW_BEZIER || action == DRAW_BSPLINE)
        {
            if (drawing) {
                if (!pts[nline].addPoint(x,y))
                {
                    if (!errDlg.isShowing()) errDlg.show();
                    drawing = false;
                    nline --;
                    setcursor(drawing);
                }
            }
            repaint();
        }
        if (action == MODIFY)
        {
            if (drawing)
            {
                drawing = false;
                setcursor(drawing);
            }
        }
        if (action == DELETE)
        {
            if (curObj != -1)
            {
                for (int i=curObj; i< nline; i++) pts[i] = pts[i+1];
                nline--;
                repaint();
            }
        }
        return true;
    }

    public boolean mouseDown(Event evt, int x, int y)
    {
        if (action == DRAW_BEZIER || action == DRAW_BSPLINE)
        {
            if (drawing == false)
            {
                nline ++;
                if (action == DRAW_BEZIER) pts[nline] = new Bezier();
                if (action == DRAW_BSPLINE) pts[nline] = new bspline();
                pts[nline].addPoint(x,y);
                drawing = true;
                setcursor(drawing);
            }
            else {
                if (evt.clickCount == 2) {
                    if (!pts[nline].done())
                    {
                        if (!errDlg.isShowing()) errDlg.show();
                        nline --;
                    }
                    drawing = false;
                    setcursor(drawing);
                }
            }
        }
        if (action == MODIFY)
        {
            if (curObj != -1) {
                drawing = true;
                setcursor(drawing);
            }
        }
        return true;
    }

    public boolean mouseMove(Event evt, int x, int y)
    {
        if (action == DRAW_BEZIER || action == DRAW_BSPLINE)
        {
            if (drawing)
            {
                pts[nline].changePoint(x,y);
                repaint();
            }
        }
        if (action == MODIFY || action == DELETE)
        {
            if (drawing == false)
            {
                int oldObj = curObj;
                curObj = -1;
                for (int i=0; i<=nline; i++)
                {
                    if (pts[i].inRegion(x,y) != -1)
                    {
                        curObj = i;
                        break;
                    }
                }
                if (oldObj != curObj) repaint();
            }
        }
        return true;
    }

    public boolean mouseDrag(Event evt, int x, int y)
    {
        if (action == MODIFY)
        {
            if (drawing == true)  {
                pts[curObj].changeModPoint(x,y);
                if (!pts[curObj].createFinal())
                {
                    if (!errDlg.isShowing()) errDlg.show();
                    nline --;
                }
                repaint();
            }
        }
        return true;
    }

    public void HandleButtons(Object label)
    {
        if (drawing)
        {
            drawing = false;
            setcursor(drawing);
        }
        if (label == "Clear All")
        {
            nline = -1;
            repaint();
            return;
        }

        if (action == DRAW_BEZIER || action == DRAW_BSPLINE)
        {
            if (drawing) pts[nline].done();
        }
        if (label == "Draw Bezier")
        {
            action = DRAW_BEZIER;
            for (int i=0; i<=nline; i++)
                pts[i].setShow(false);
            repaint();
        }
        else if (label == "Draw B-Spline")
        {
            action = DRAW_BSPLINE;
            for (int i=0; i<=nline; i++)
                pts[i].setShow(false);
            repaint();
        }
        else if (label == "Modify")
        {
            action = MODIFY;
            for (int i=0; i<=nline; i++)
                pts[i].setShow(true);
            repaint();
        }
        else if (label == "Delete curve")
        {
            action = DELETE;
            for (int i=0; i<=nline; i++)
                pts[i].setShow(true);
            repaint();
        }
    }

    public void paint(Graphics g)
    {
        update(g);
    }

    public void update(Graphics g) {    //Don't bother
        int i,n;
        Dimension d=size();

        if (img == null)
        {
            img = createImage(d.width, d.height);
            backg = img.getGraphics();
        }

        backg.setColor(new Color(255,255,255));    //Set color for background
        backg.fillRect(0,0, d.width, d.height);  //Draw Backround

        // draw border
        backg.setColor(new Color(0,0,0));
        backg.drawRect(1,1,d.width-3,d.height-3);

        for (n=0; n <= nline; n++)
            pts[n].draw(backg);

        g.drawImage(img, 0, 0, this);
    }
}
