package hellofx.Bomb_Flame;

// import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
// import javafx.geometry.Point2D;
import javafx.util.Duration;

import static hellofx.Constant.GameConstant.*;

public class FlameAnimation extends Component {
    public AnimatedTexture texture;
    private final AnimationChannel center;
    private final AnimationChannel wingHorizontal;
    private final AnimationChannel wingVertical;
    private final AnimationChannel wingCentral;
    private final AnimationChannel brickBreak;

    public FlameAnimation() {
        center = new AnimationChannel(FXGL.image("character\\bomb.png")
                , 3, TITLE_SIZE, TITLE_SIZE
                , Duration.seconds(0.5), 0,2);
        wingHorizontal = new AnimationChannel(FXGL.image("flame\\left_flame.png")
                , 3 , TITLE_SIZE, TITLE_SIZE
                , Duration.seconds(0.5), 0,2);
        wingVertical = new AnimationChannel(FXGL.image("flame\\up_flame.png")
                , 3 , TITLE_SIZE, TITLE_SIZE
                , Duration.seconds(0.5), 0,2);
        wingCentral = new AnimationChannel(FXGL.image("flame\\central_flame.png")
                , 3 , TITLE_SIZE, TITLE_SIZE
                , Duration.seconds(0.5), 0,2);
        brickBreak = new AnimationChannel(FXGL.image("mapTexture\\brick_break.png")
                , 3, TITLE_SIZE, TITLE_SIZE
                , Duration.seconds(0.5), 0, 2);
        texture = new AnimatedTexture(center);
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
    }

    @Override
    public void onUpdate(double tpf) {

    }

    /**.
     * Boom.
     */
    public void AnimationCenter() {
        texture.loopAnimationChannel(center);
    }

    /**.
     * Flame horizontal
     */
    public void AnimationWingHorizontal() {
        texture.loopAnimationChannel(wingHorizontal);
    }

    /**.
     * Flame vertical.
     */
    public void AnimationWingVertical() {
        texture.loopAnimationChannel(wingVertical);
    }

    /**.
     * Flame central.
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
