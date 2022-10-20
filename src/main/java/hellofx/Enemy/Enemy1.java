package hellofx.Enemy;

// import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;
import static hellofx.Constant.GameConstant.TITLE_SIZE;
// import static hellofx.Main.g_map;

public class Enemy1 extends Enemy {
    protected final AnimationChannel animation;

    private final AnimationChannel animDead;

    public PathFinding findPlayer;

    public int firstX;
    public int firstY;

    public Enemy1() {
        right_ = false;
        left_ = false;
        up_ = false;
        down_ = false;
        animation = new AnimationChannel(FXGL.image("enemy/oneal40.png"), 3, TITLE_SIZE, TITLE_SIZE,
                Duration.seconds(0.5), 6, 8);
        animDead = new AnimationChannel(FXGL.image("enemy/oneal40.png"), 5, TITLE_SIZE, TITLE_SIZE,
                Duration.seconds(0.3), 0, 4);
        texture = new AnimatedTexture(animation);
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
    }

    public void setUp() {
        double tileX = this.entity.getPosition().getX();
        double tileY = this.entity.getPosition().getY();
        firstX = (int) tileX;
        firstY = (int) tileY;
        findPlayer = new PathFinding((int) (tileY / TITLE_SIZE), (int) (tileX / TITLE_SIZE));
        findPlayer.setUpPath();
        texture.loopAnimationChannel(animation);
    }

    public void dead() {
        right_ = false;
        left_ = false;
        texture.loopAnimationChannel(animDead);
    }

    public void findAgain() {
        // System.out.println(findPlayer.resetFinding);
        if (findPlayer.resetFinding) {
            double tileX = this.entity.getPosition().getX();
            double tileY = this.entity.getPosition().getY();
            findPlayer.resetPathFinding((int) (tileY / TITLE_SIZE), (int) (tileX / TITLE_SIZE));
            setUp();
            System.out.println("RESET MAP !!!");
            findPlayer.resetFinding = false;
        }
    }

    @Override
    public void onUpdate(double tpf) {
        // System.out.println(findPlayer.resetFinding);
        String direction = "";
        if (!findPlayer.st.empty()) {
            direction = findPlayer.st.peek();
        }
        if (direction.equals("LEFT")) {
            if ((int) getXPos() == firstX - TITLE_SIZE) {
                findPlayer.st.pop();
                firstX -= TITLE_SIZE;
                findAgain();
            } else {
                turnLeft();
            }
        }
        if (direction.equals("RIGHT")) {
            if ((int) getXPos() == firstX + TITLE_SIZE) {
                findPlayer.st.pop();
                firstX += TITLE_SIZE;
                findAgain();
            } else {
                turnRight();
            }
        }
        if (direction.equals("UP")) {
            if ((int) getYPos() == firstY - TITLE_SIZE) {
                findPlayer.st.pop();
                firstY -= TITLE_SIZE;
                findAgain();
            } else {
                turnUp();
            }
        }
        if (direction.equals("DOWN")) {
            if ((int) getYPos() == firstY + TITLE_SIZE) {
                findPlayer.st.pop();
                firstY += TITLE_SIZE;
                findAgain();
            } else {
                turnDown();
            }
        }
        // System.out.println(getXPos() + " " + getYPos());
    }

    @Override
    public void turnBack() {

    }

    public double getXPos() {
        return entity.getPosition().getX();
    }

    public double getYPos() {
        return entity.getPosition().getY();
    }

    public void turnLeft() {
        entity.translateX(-1);
    }

    public void turnRight() {
        entity.translateX(1);
    }

    public void turnUp() {
        entity.translateY(-1);
    }

    public void turnDown() {
        entity.translateY(1);
    }

}
