package hellofx.GameEntity.DynamicEntity;

import com.almasb.fxgl.texture.AnimationChannel;
import hellofx.GameEntity.GameEntity;

public abstract class DynamicEntity extends GameEntity{
    public boolean isDead = false;

    public boolean up_ = false;
    public boolean down_ = false;
    public boolean left_ = false;
    public boolean right_ = true;
    public boolean isStay = true;

    public AnimationChannel animDead;
    public AnimationChannel animUp;
    public AnimationChannel animDown;
    public AnimationChannel animLeft;
    public AnimationChannel animRight;

    public double speed = 0;
}