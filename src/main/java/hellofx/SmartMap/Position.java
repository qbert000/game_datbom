package hellofx.SmartMap;

import java.util.ArrayList;
import java.util.List;

public class Position {
    public int index_x;
    public int index_y;

    public int index_x_vir;
    public int index_y_vir;
    public int reckon;

    public boolean right_;
    public boolean left_;
    public boolean up_;
    public boolean down_;

    public int step_x;
    public int step_y;


    public List<Boolean> direction = new ArrayList<>();
    public List<Street> street = new ArrayList<>();


    public void setRight_() {
        right_ = true;
        left_ = false;
        up_ = false;
        down_ = false;
        step_x = 1;
        step_y = 0;
    }
    public void setLeft_() {
        right_ = false;
        left_ = true;
        up_ = false;
        down_ = false;
        step_x = -1;
        step_y = 0;
    }
    public void setUp_() {
        right_ = false;
        left_ = false;
        up_ = true;
        down_ = false;
        step_x = 0;
        step_y = -1;
    }
    public void setDown_() {
        right_ = false;
        left_ = false;
        up_ = false;
        down_ = true;
        step_x = 0;
        step_y = 1;
    }
    public void setPositionVir() {
        index_x_vir = index_x;
        index_y_vir = index_y;
    }
    public Position() {

    }
    public Position(int y, int x, boolean right, boolean left, boolean up, boolean down) {
        this.index_x = x;
        this.index_y = y;
        this.direction.add(right);
        this.direction.add(left);
        this.direction.add(up);
        this.direction.add(down);
        for (int i = 0; i < 4; i++) {
            street.add(new Street());
        }
        index_x_vir = x;
        index_y_vir = y;
        //System.out.println(direction.get(0) + " " +direction.get(1) + " " + direction.get(2) + " " + direction.get(3));
    }

    public void findAround() {
        for (int i = 0; i < direction.size(); i++) {
            if (direction.get(i)) {
                go(i);
                goFind(i);
                street.get(i).position2 = this;
                setPositionVir();
            }
        }
    }

    public void goFind(int i) {
        index_x_vir += step_x;
        index_y_vir += step_y;
        street.get(i).weight++;
//        System.out.println(index_x_vir + " " + index_y_vir);
//        if ( index_x_vir < 1 || index_y_vir < 1) {
//            return;
//        }
        if (SmartMap.smartMap[index_y_vir][index_x_vir]) {
            street.get(i).position1 = newPosition(index_x_vir, index_y_vir);
            connectPosition(i);
            return;
        }
        SmartMap.checkAround(index_y_vir, index_x_vir);
        if (SmartMap.corner == 1) {
            direction.set(i, false);
            street.get(i).weight = 1000;
            return;
        } else if (SmartMap.corner == 2) {
            if (SmartMap.gate[0] && !left_) {
                setRight_();
            } else if (SmartMap.gate[1] && !right_) {
                setLeft_();
            } else if (SmartMap.gate[2] && !down_) {
                setUp_();
            } else if (SmartMap.gate[3] && !up_) {
                setDown_();
            }
            goFind(i);
        }
    }

    public void connectPosition(int i) {
        if (right_) {
            street.get(i).position1.direction.set(1, false);
            street.get(i).position1.street.set(1, street.get(i));
        } else if (left_) {
            street.get(i).position1.direction.set(0, false);
            street.get(i).position1.street.set(0, street.get(i));
        } else if (up_) {
            street.get(i).position1.direction.set(3, false);
            street.get(i).position1.street.set(3, street.get(i));
        } else if (down_) {
            street.get(i).position1.direction.set(2, false);
            street.get(i).position1.street.set(2, street.get(i));
        }
    }

    public void go(int i) {
        if (i == 0) {
            setRight_();
        } else if (i == 1) {
            setLeft_();
        } else if (i == 2) {
            setUp_();
        } else if (i == 3) {
            setDown_();
        }
    }

    public Position newPosition(int x, int y) {
        for (Position position: SmartMap.smartPosition) {
            if (position.index_x == x && position.index_y == y) {
                return position;
            }
        }
        return new Position();
    }

    public void print() {
        for (int i = 0; i < direction.size(); i++) {
            if (street.get(i).weight != 0) {
                System.out.println(street.get(i).position1.index_x + " " + street.get(i).position1.index_y + " " + street.get(i).weight);
            }
        }
    }
}
