package hellofx.SmartMap;

public class Street {
    public int weight;
    public Position position1;
    public Position position2;

    public Street() {
        weight = 0;
    }

    public Position getPos(Position position) {
        if (position1.equals(position)) {
            return position2;
        } else {
            return position1;
        }
    }
}
