package Or;

public class Data {
    int x1;
    int x2;
    int xB=1;
    int target;

    public Data(int x1, int x2, int target) {
        this.x1 = x1;
        this.x2 = x2;
        this.target = target;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getxB() {
        return xB;
    }

    public void setxB(int xB) {
        this.xB = xB;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "Data{" + "x1=" + x1 + ", x2=" + x2 + ", xB=" + xB + ", target=" + target + '}';
    }    
    
}

