package hellofx.Enemy;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import hellofx.Map.MyMap;
import hellofx.SmartMap.Position;
import hellofx.SmartMap.SmartMap;
import javafx.util.Duration;
// import hellofx.SmartMap.Street;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static hellofx.Constant.GameConstant.ENEMY_SIZE;
import static hellofx.Constant.GameConstant.TITLE_SIZE;
// import static hellofx.Constant.GameConstant.starterPosX;
import static hellofx.Map.MyMap.canGoThisWay;

public class EnemyPontan extends EnemyDahl {

    private List<Integer> check = new ArrayList<>();
    private Position vir;
    public EnemyPontan() {
        animDead = new AnimationChannel(FXGL.image("enemy/enemyPontan.png"), 6, TITLE_SIZE,
                TITLE_SIZE, Duration.seconds(0.7), 0, 5);
        animRight = new AnimationChannel(FXGL.image("enemy/enemyPontan.png"), 3, TITLE_SIZE,
                TITLE_SIZE, Duration.seconds(0.5), 6, 8);
        animLeft = new AnimationChannel(FXGL.image("enemy/enemyPontan.png"), 3, TITLE_SIZE,
                TITLE_SIZE, Duration.seconds(0.5), 3, 5);
        right_ = true;
        left_ = false;
        up_ = false;
        down_ = false;
        vir = new Position();
        texture = new AnimatedTexture(animRight);
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


    @Override
    public void move() {
        if ((int) (entity.getY() % TITLE_SIZE) != 0 || (int) (entity.getX() % TITLE_SIZE) != 0) {
            return;
        }
        if (!SmartMap.smartMap[(int) (entity.getY() / TITLE_SIZE)][(int) (entity.getX() / TITLE_SIZE)]) {
            super.move();
            return;
        }
        setSmartMap();

        chosse(MyMap.playerY * TITLE_SIZE, MyMap.playerX * TITLE_SIZE);
        //System.out.println(MyMap.playerX + " " + MyMap.playerY);
    }

    public void setSmartMap() {
        index_x_ = (int) entity.getX() / TITLE_SIZE;
        index_y_ = (int) entity.getY() / TITLE_SIZE;

        vir = vir.newPosition(index_x_, index_y_);

        if (vir.street.get(0).weight != 0 && !left_) {check.add(0);}
        if (vir.street.get(1).weight != 0 && !right_) {check.add(1);}
        if (vir.street.get(2).weight != 0 && !down_) {check.add(2);}
        if (vir.street.get(3).weight != 0 && !up_) {check.add(3);}
    }


    public void chosse(int x, int y) {
        int a ;
        int k =0;
        a = 100; //vir.street.get(check.get(0)).weight + vir.street.get(check.get(0)).getPos(vir).setReckon(x,y);
        for (int i = 0; i < check.size(); i++) {
            if ( a > vir.street.get(check.get(i)).weight
                    + vir.street.get(check.get(i)).getPos(vir).setReckon(x,y)) {
                a = vir.street.get(check.get(i)).weight
                        + vir.street.get(check.get(i)).getPos(vir).setReckon(x,y);
                k = check.get(i);
            } else if (a == vir.street.get(check.get(i)).weight
                    + vir.street.get(check.get(i)).getPos(vir).setReckon(x,y)) {
                int ranNum = ThreadLocalRandom.current().nextInt(0,2);
                if (ranNum == 0) {
                    a = vir.street.get(check.get(i)).weight
                            + vir.street.get(check.get(i)).getPos(vir).setReckon(x,y);
                    k = check.get(i);
                }
            }
        }




        //System.out.println("nạng so" + a + " " + k);

        go(k);

    }

    public void go(int k) {
        switch (k) {
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
        check.clear();
    }

    @Override
    public void setMap() {
        index_x_ = (int) entity.getX() / TITLE_SIZE;
        index_y_ = (int) entity.getY() / TITLE_SIZE;

        // set ben trai
        turn_left_ = canGoThisWay(index_x_ - 1, index_y_, "1");
        //set ben phai
        turn_right_ = canGoThisWay(index_x_ + 1, index_y_, "1");
        //set ben tren
        turn_up_ = canGoThisWay(index_x_, index_y_ - 1, "1");
        //set ben duoi
        turn_down_ = canGoThisWay(index_x_, index_y_ + 1, "1");
    }
}
