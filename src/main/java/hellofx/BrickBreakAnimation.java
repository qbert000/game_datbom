package hellofx;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.util.Duration;

public class BrickBreakAnimation extends Component {
    public AnimatedTexture texture;
    private final AnimationChannel unBreakedBrick;
    private final AnimationChannel brickBreak;

    public BrickBreakAnimation() {
        unBreakedBrick = new AnimationChannel(FXGL.image("mapTexture\\brick.png")
                , 1, 40, 40
                , Duration.seconds(0.5), 0,0);
        brickBreak = new AnimationChannel(FXGL.image("mapTexture\\brick_break.png")
                , 3, 40, 40
                , Duration.seconds(0.7), 0, 2);
        texture = new AnimatedTexture(unBreakedBrick);
    }

    @Override
    public void onAdded() {
        //entity.getTransformComponent().setScaleOrigin(new Point2D(16,21));
        entity.getViewComponent().addChild(texture);
    }

    @Override
    public void onUpdate(double tpf) {

    }

    /**
     * brick break animation
     */
    public void BrickBreak() {
        texture.loopAnimationChannel(brickBreak);
    }

    public void UnbreakedBrick() {
        texture.loopAnimationChannel(unBreakedBrick);
    }
}
