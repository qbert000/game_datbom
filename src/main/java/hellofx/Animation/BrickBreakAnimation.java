package hellofx.Animation;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

import static hellofx.Constant.GameConstant.*;

public class BrickBreakAnimation extends Component {
    public AnimatedTexture texture;
    private final AnimationChannel unBreakedBrick;
    private final AnimationChannel brickBreak;

    public BrickBreakAnimation() {
        unBreakedBrick = new AnimationChannel(FXGL.image("mapTexture\\brick.png")
                , 1, TITLE_SIZE, TITLE_SIZE
                , Duration.seconds(0.5), 0,0);
        brickBreak = new AnimationChannel(FXGL.image("mapTexture\\brick_break.png")
                , 3, TITLE_SIZE, TITLE_SIZE
                , Duration.seconds(0.7), 0, 2);
        texture = new AnimatedTexture(unBreakedBrick);
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
    }

    @Override
    public void onUpdate(double tpf) {

    }
    
    public void BrickBreak() {
        texture.loopAnimationChannel(brickBreak);
    }

    public void UnbreakedBrick() {
        texture.loopAnimationChannel(unBreakedBrick);
    }
}
