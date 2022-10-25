package hellofx.Enemy;

import com.almasb.fxgl.texture.AnimatedTexture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static hellofx.Constant.GameConstant.TITLE_SIZE;
import static hellofx.Map.MyMap.canGoThisWay;
public class EnemyRandom extends Enemy {

    private final List<Integer> turn_ = new ArrayList<Integer>();

    private int corner;

    public int index_x_;
    public int index_y_;


    public boolean turn_right_;
    public boolean turn_left_;
    public boolean turn_up_;
    public boolean turn_down_;
    public EnemyRandom() {
        super();
        right_ = false;
        left_ = false;
        up_ = false;
        down_ = false;
        texture = new AnimatedTexture(animRight);
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
    }

    @Override
    public void onUpdate(double tpf) {
        if(!isDead) {
            move();
            if (right_) {
                setRightAnimationOnce();
                entity.translateX(1);
            } else if (left_) {
                setLeftAnimationOnce();
                entity.translateX(-1);
            } else if (up_) {
                entity.translateY(-1);
            } else if (down_) {
                entity.translateY(1);
            }
        } else {
            setDeadAnimationOnce();
        }
    }

    public void move() {
        if ((int) (entity.getY() % TITLE_SIZE) != 0 || (int) (entity.getX() % TITLE_SIZE) != 0) {
            return;
        }
        setMap();
        setCorner();
        if (corner == 1) {
            turnBack();
        }
        setTurnRandom();
    }

    public void setMap() {

        index_x_ = (int) entity.getX() / TITLE_SIZE;
        index_y_ = (int) entity.getY() / TITLE_SIZE;

        // set ben trai
        turn_left_ = canGoThisWay(index_x_ - 1, index_y_);
        //set ben phai
        turn_right_ = canGoThisWay(index_x_ + 1, index_y_);
        //set ben tren
        turn_up_ = canGoThisWay(index_x_, index_y_ - 1);
        //set ben duoi
        turn_down_ = canGoThisWay(index_x_, index_y_ + 1);

        //System.out.println(index_x_ + " " +index_y_ + " " + turn_right_ + " " + turn_left_ + " " + turn_down_ + " " + turn_up_ );

    }

    public void setCorner() {
        corner = 0;
        if (turn_right_) {corner++;}
        if (turn_left_) {corner++;}
        if (turn_up_) {corner++;}
        if (turn_down_) {corner++;}
        //System.out.println(corner);
    }
    public void setTurnRandom() {
        if (turn_right_ && !left_) {turn_.add(0);}
        if (turn_left_ && !right_) {turn_.add(1);}
        if (turn_up_ && !down_) {turn_.add(2);}
        if (turn_down_ && !up_) {turn_.add(3);}

//        for (int i =0; i< turn_.size(); i++) {
//            System.out.print(turn_.get(i) + "  ");
//        }
//        System.out.println();

        int ranNum = ThreadLocalRandom.current().nextInt(0,turn_.size());
        switch (turn_.get(ranNum)) {
            case 0:
                turnRight();
                break;
            case 1:
                turnLeft();
                break;
            case 2:
                turnUp();
                break;
            case 3:
                turnDown();
                break;
            default:
        }
        turn_.clear();
    }


    @Override
    public void turnBack() {
        if (turn_right_) {
            turnRight();
        } else if (turn_left_) {
            turnLeft();
        } else if (turn_up_) {
            turnUp();
        } else if (turn_down_) {
            turnDown();
        }
    }
}
