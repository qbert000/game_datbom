package hellofx;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.util.Duration;

public class FlameAnimation extends Component {
    private int speed = 0;

    public AnimatedTexture texture;
    private final AnimationChannel center;
    private final AnimationChannel wingHorizontal;
    private final AnimationChannel wingVertical;
    private final AnimationChannel wingCentral;
    private final AnimationChannel brickBreak;

    public FlameAnimation() {
        center = new AnimationChannel(FXGL.image("character\\bomb.png")
                , 3, 40, 40
                , Duration.seconds(0.5), 0,2);
        wingHorizontal = new AnimationChannel(FXGL.image("flame\\left_flame.png")
                , 3 , 40, 40
                , Duration.seconds(0.5), 0,2);
        wingVertical = new AnimationChannel(FXGL.image("flame\\up_flame.png")
                , 3 , 40, 40
                , Duration.seconds(0.5), 0,2);
        wingCentral = new AnimationChannel(FXGL.image("flame\\central_flame.png")
                , 3 , 40, 40
                , Duration.seconds(0.5), 0,2);
        brickBreak = new AnimationChannel(FXGL.image("mapTexture\\brick_break.png")
                , 3, 40, 40
                , Duration.seconds(0.5), 0, 2);
        texture = new AnimatedTexture(center);
    }

    @Override
    public void onAdded() {
        //entity.getTransformComponent().setScaleOrigin(new Point2D(16,21));
        entity.getViewComponent().addChild(texture);
    }

    @Override
    public void onUpdate(double tpf) {

    }

    /**.
     * boom.
     */
    public void AnimationCenter() {
        texture.loopAnimationChannel(center);
    }

    /**.
     * flame horizontal
     */
    public void AnimationWingHorizontal() {
        texture.loopAnimationChannel(wingHorizontal);
    }

    /**.
     * flame vertical.
     */
    public void AnimationWingVertical() {
        texture.loopAnimationChannel(wingVertical);
    }

    /**.
     * flame central.
     */
    public void AnimationWingCentral() {
        texture.loopAnimationChannel(wingCentral);
    }

    /**
     * brick break animation
     */
    public void BrickBreak() {
        texture.loopAnimationChannel(brickBreak);
    }
}
