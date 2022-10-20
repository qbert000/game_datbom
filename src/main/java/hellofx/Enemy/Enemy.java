package hellofx.Enemy;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.entity.Entity;



public abstract class Enemy extends Component {

    protected double speed = 0;

    public boolean right_;
    public boolean left_;
    public boolean up_;
    public boolean down_;
    
    protected double currentPosX;
    protected double currentPosY;

    public int speedX;
    public int speedY;
    
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

    public Entity getMyEntity() {
        return entity;
    }

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
            entity.translateX(speedX * tpf);currentPosX = entity.getPosition().getX() - speedX*tpf;
            entity.translateY(speedY * tpf);currentPosY = entity.getPosition().getY() - speedY*tpf;
        //System.out.println(currentPosX + " " + currentPosY);

    }

    public void turnRight() {
        speedX = 70;
        speedY = 0;
    }
    public void turnLeft() {
        speedX = -70;
        speedY = 0;
    }
    public void turnUp() {
        speedY = -70;
        speedX =  0;
    }
    public void turnDown() {
        speedY = 70;
        speedX = 0;
    }

    public abstract void turnBack();

    public double getCurrentPosX() {
        return currentPosX;
    }

    public double getCurrentPosY() {
        return currentPosY;
    }
}
