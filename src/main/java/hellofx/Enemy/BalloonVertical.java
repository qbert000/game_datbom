package hellofx.Enemy;

import com.almasb.fxgl.texture.AnimatedTexture;

public class BalloonVertical extends Enemy {

    public BalloonVertical() {
        super();
        right_ = false;
        left_ = false;
        up_ = false;
        down_ = true;
        texture = new AnimatedTexture(animRight);
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
    }

    @Override
    public void turnBack() {
        if (down_ ) {
            if (!turn_down_) {
                turnUp();
            } else {
                turnDown();
            }
        } else if (up_) {
            if (!turn_up_) {
                turnDown();
            } else {
                turnUp();
            }
        }
    }

}
