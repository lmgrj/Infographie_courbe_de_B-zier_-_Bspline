package TP2infog;

import java.awt.*;

class listedespoints {
    Pointunitaire pt[];
    int num;
    int x,y,z; 	// color
    boolean showLine;
    int curPt;
    final int MAXCNTL = 50;
    final int range = 5;

    listedespoints() {
        num = 0;
        curPt = -1;
        pt = new Pointunitaire[MAXCNTL];
        x = (int)(Math.random() * 255);
        y = (int)(Math.random() * 255);
        z = (int)(Math.random() * 255);
    }

    boolean addPoint(int x, int y)
    {
        if (num == MAXCNTL) return false;
        pt[num] = new Pointunitaire(x,y);
        num++;
        return true;
    }

    void changePoint(int x, int y)
    {
        pt[num-1].x = x;
        pt[num-1].y = y;
    }

    void changeModPoint(int x, int y)
    {
        pt[curPt].x = x;
        pt[curPt].y = y;
    }

    boolean createFinal()
    {
        return true;
    }

    boolean done()
    {
        return true;
    }

    void setShow(boolean show)
    {
        showLine = show;
    }

    int inRegion(int x, int y)
    {
        int i;
        for (i=0; i<num; i++)
            if (Math.abs(pt[i].x-x) < range && Math.abs(pt[i].y-y) < range)
            {
                curPt = i;
                return i;
            }
        curPt = -1;
        return -1;
    }

    void draw(Graphics g)
    {
        int i;
        int l = 3;
        for (i=0; i< num-1; i++)
        {
            g.drawLine(pt[i].x-l, pt[i].y, pt[i].x+l, pt[i].y);
            g.drawLine(pt[i].x, pt[i].y-l, pt[i].x, pt[i].y+l);
            drawDashLine(g, pt[i].x,pt[i].y,pt[i+1].x,pt[i+1].y);   //Draw segment
        }
        g.drawLine(pt[i].x-l, pt[i].y, pt[i].x+l, pt[i].y);
        g.drawLine(pt[i].x, pt[i].y-l, pt[i].x, pt[i].y+l);
    }

    // draw dash lines
    protected void drawDashLine(Graphics g, int x1, int y1, int x2, int y2)
    {
        final float seg = 8;
        double x, y;

        if (x1 == x2)
        {
            if (y1 > y2)
            {
                int tmp = y1;
                y1 = y2;
                y2 = tmp;
            }
            y = (double)y1;
            while (y < y2)
            {
                double y0 = Math.min(y+seg, (double)y2);
                g.drawLine(x1, (int)y, x2, (int)y0);
                y = y0 + seg;
            }
            return;
        }
        else if (x1 > x2)
        {
            int tmp = x1;
            x1 = x2;
            x2 = tmp;
            tmp = y1;
            y1 = y2;
            y2 = tmp;
        }
        double ratio = 1.0*(y2-y1)/(x2-x1);
        double ang = Math.atan(ratio);
        double xinc = seg * Math.cos(ang);
        double yinc = seg * Math.sin(ang);
        x = (double)x1;
        y = (double)y1;

        while ( x <= x2 )
        {
            double x0 = x + xinc;
            double y0 = y + yinc;
            if (x0 > x2) {
                x0 = x2;
                y0  = y + ratio*(x2-x);
            }
            g.drawLine((int)x, (int)y, (int)x0, (int)y0);
            x = x0 + xinc;
            y = y0 + yinc;
        }
    }
}
