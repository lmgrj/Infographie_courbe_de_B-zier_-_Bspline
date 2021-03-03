package TP2infog;

class bspline extends Bezier
{
    protected void bspline_to_Bezier(int j, Pointunitaire p[], Pointunitaire v[])
    {
        int h,i;
        double tmp,x1,x2;

        for (h=0;h<=1;h++) {
            for (i=0;i<=1;i++)  {
                tmp=1.0*((j+h)-(j-2+h+i))*1.0/((j+1+i+h)-(j-2+h+i));
                x1=p[j-2+i+h].x;
                x2=p[j-2+i+h-1].x;
                v[2*h+i].x=(int)(tmp*x1+(1.0-tmp)*x2);
                x1=p[j-2+i+h].y;
                x2=p[j-2+i+h-1].y;
                v[2*h+i].y=(int)(tmp*x1+(1.0-tmp)*x2);
            }
            tmp=1.0*((j+h)-(j-1+h))/((j+1+h)-(j-1+h));
            x1=v[1+2*h].x;
            x2=v[2*h].x;
            v[3*h].x=(int)(tmp*x1+(1.0-tmp)*x2);
            x1=v[1+2*h].y;
            x2=v[2*h].y;
            v[3*h].y=(int)(tmp*x1+(1-tmp)*x2);
        }
    }

    protected boolean bspline_generation(Pointunitaire pt[], int n, Pointunitaire result[], int num[])
    {
        Pointunitaire v[];
        int i,j;

        v = new Pointunitaire[4];
        for (i=0; i<4; i++) v[i] = new Pointunitaire();
        for (j=3;j<n;j++) {
            bspline_to_Bezier(j,pt,v);
            if (num[0] > 0) num[0]=num[0]-1;
            if (!try_bezier_generation(v,4,result,num)) return false;
        }
        return true;
    }

    boolean createFinal()
    {
        int n[];
        n = new int[1];
        n[0] = 0;
        if (bspline_generation(pt, num, bpt, n))
        {
            bnum = n[0];
            return true;
        }
        else {
            bnum = 0;
            return false;
        }
    }

}
