public class Pos {
    private int X;
    private int Y;

public Pos (int x, int y){
    this.X = x;
    this.Y = y;
}


public int getX () {
    return this.X;
}

public int getY() {
    return this.Y;
}

public void setX (int X) {
    if (X >= 0)
        this.X = X;
}

public void setY (int Y) {
    if (Y >= 0)
        this.Y = Y;
}

}