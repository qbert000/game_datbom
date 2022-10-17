package hellofx.Enemy;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import static hellofx.Constant.GameConstant.*;
import javafx.geometry.Point2D;

import java.awt.*;

import static com.almasb.fxgl.dsl.FXGL.onCollisionBegin;
import static hellofx.SpawnSystem.Enum.ENEMYHORIZONTAL;
import static hellofx.SpawnSystem.Enum.WALL;


import hellofx.Map.Mymap.*;

public abstract class Enemy extends Component {

    protected double speed = 0;

    protected boolean right_;
    protected boolean left_;
    protected boolean up_;
    protected boolean down_;

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
