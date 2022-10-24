package hellofx.Enemy;

import hellofx.SmartMap.SmartMap;

import static hellofx.Constant.GameConstant.TITLE_SIZE;

public class Enemy8 extends EnemyRandom {


    @Override
    public void onUpdate(double tpf) {
        if (right_) {
            entity.translateX(1);
        } else if (left_) {
            entity.translateX(-1);
        } else if (up_) {
            entity.translateY(-1);
        } else if (down_) {
            entity.translateY(1);
        }
    }

    @Override
    public void move() {
        super.move();

    }

    public void moveForMap() {
        if (!SmartMap.smartMap[(int) (entity.getY() / TITLE_SIZE)][(int) (entity.getX() / TITLE_SIZE)]) {
            return;
        } 
        
    }
}
