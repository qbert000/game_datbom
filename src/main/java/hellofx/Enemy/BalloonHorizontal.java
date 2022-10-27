package hellofx.Enemy;

import java.util.ArrayList;
import java.util.List;
import com.almasb.fxgl.texture.AnimatedTexture;

public class BalloonHorizontal extends Enemy {
    private final List<Integer> turn_ = new ArrayList<Integer>();
    private int corner;
    public BalloonHorizontal() {
        //super();
        right_ = true;
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
    public void turnBack() {
        if (right_ ) {
            if (!turn_right_) {
                turnLeft();
            } else {
                turnRight();
            }
        } else if (left_) {
            if (!turn_left_) {
                turnRight();
            } else {
                turnLeft();
            }
        }
    }

}
