package TP2infog;

class Pointunitaire
{
    int x,y;

    Pointunitaire(Pointunitaire p)
    {
        x = p.x;
        y = p.y;
    }
    Pointunitaire(int _x, int _y)
    {
        x = _x;
        y = _y;
    }
    Pointunitaire()
    {
        x = 0;
        y = 0;
    }
    void copy(Pointunitaire p)
    {
        x = p.x;
        y = p.y;
    }
}
