package hellofx.Enemy;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;

public abstract class Enemy extends Component {

    protected double speed = 0;

    public boolean right_;
    public boolean left_;
    public boolean up_;
    public boolean down_;

    public boolean isRight_() {
        return right_;
    }

    public boolean isLeft_() {
        return left_;
    }

    public boolean isUp_() {
        return up_;
    }

    public boolean isDown_() {
        return down_;
    }

    protected double currentPosX;

    protected double currentPosY;



    public void setCurrentPosX(double currentPosX) {
        this.currentPosX = currentPosX;
    }

    public void setCurrentPosY(double currentPosY) {
        this.currentPosY = currentPosY;
    }

    public AnimatedTexture texture;

    @Override
    public void onUpdate(double tpf) {
        // Luu vi tri cu cua Enemy
        //System.out.println((int) currentPosX / TITLE_SIZE + " " + (int) currentPosY / TITLE_SIZE + " " + currentPosX / TITLE_SIZE + " " + currentPosY / TITLE_SIZE);
        if (right_) {
            entity.translateX(speed * tpf);currentPosX = entity.getPosition().getX() - speed*tpf;
        } else if (left_) {
            entity.translateX(speed * tpf);currentPosX = entity.getPosition().getX() - speed*tpf;
        }

        if (up_) {
            entity.translateY(speed * tpf);currentPosY = entity.getPosition().getY() - speed*tpf;
        } else if (down_) {
            entity.translateY(speed * tpf);currentPosY = entity.getPosition().getY() - speed*tpf;
        }

        //System.out.println(currentPosX + " " + currentPosY);

    }

    public void turnRight() {
        speed = 150;
    }
    public void turnLeft() {
        speed = -150;
    }
    public void turnUp() {
        speed = -150;
    }
    public void turnDown() {
        speed = 150;
    }

    public abstract void turnBack();

    public double getCurrentPosX() {
        return currentPosX;
    }

    public double getCurrentPosY() {
        return currentPosY;
    }
}
