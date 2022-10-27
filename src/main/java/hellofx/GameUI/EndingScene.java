package hellofx.GameUI;

import com.almasb.fxgl.scene.SubScene;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.effect.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import static com.almasb.fxgl.dsl.FXGL.*;
import static hellofx.Constant.GameConstant.*;
import javafx.scene.text.Font;
import com.almasb.fxgl.dsl.FXGLForKtKt;
import com.almasb.fxgl.ui.FontType;
import javafx.geometry.Pos;
import static hellofx.Music.SoundEffect.*;

public class EndingScene extends SubScene {
    public EndingScene(String title) {
        turnOffMusic();
        play("ending.wav");
        Font myFont = FXGLForKtKt.getUIFactoryService().newFont(FontType.GAME, 40);
        var background = new Rectangle(TITLE_SIZE * 32, TITLE_SIZE * 19, Color.color(0, 0, 0, 1));
        Label titleText = new Label();
        titleText.setTextFill(Color.WHITESMOKE);
        titleText.setFont(myFont);
        titleText.setText(title);
        titleText.setEffect(new Bloom(0.6));
        titleText.setAlignment(Pos.CENTER);
        titleText.setMinWidth(getAppWidth());
        titleText.setTranslateY(getAppHeight() / 3.0 + 60);
        titleText.setEffect(new Bloom(0.6));
        getContentRoot().getChildren().addAll(background, titleText);
        animationBuilder()
                .onFinished(() -> {
                    animationBuilder()
                            .onFinished(() -> popSubScene())
                            .duration(Duration.seconds(7))
                            .scale(titleText)
                            .from(new Point2D(1.1, 1.1))
                            .to(new Point2D(1, 1))
                            .buildAndPlay(this);
                })
                .duration(Duration.seconds(3))
                .scale(titleText)
                .from(new Point2D(1, 1))
                .to(new Point2D(1.1, 1.1))
                .buildAndPlay(this);
    }

    public void popSubScene() {
        getSceneService().popSubScene();
        getGameController().gotoMainMenu();
        turnOnMusic();
    }
}
