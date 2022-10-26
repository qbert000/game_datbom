package hellofx.GameUI;

import com.almasb.fxgl.scene.SubScene;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import static com.almasb.fxgl.dsl.FXGL.*;
import static hellofx.Constant.GameConstant.*;
import javafx.scene.text.Font;
import static hellofx.Main.myLevel;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.control.Label;
import com.almasb.fxgl.dsl.FXGLForKtKt;
import com.almasb.fxgl.dsl.FXGLForKtKt;
import com.almasb.fxgl.ui.FontType;
import com.almasb.fxgl.scene.SubScene;
import javafx.scene.effect.Bloom;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import static com.almasb.fxgl.dsl.FXGL.*;

import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Pos;
import com.almasb.fxgl.dsl.FXGLForKtKt;

public class EndingScene extends SubScene {
    public EndingScene(String title) {
        Font myFont = FXGLForKtKt.getUIFactoryService().newFont(FontType.GAME, 80);
        var background = new Rectangle(TITLE_SIZE * 32, TITLE_SIZE * 19, Color.color(0, 0, 0, 1));
        Label titleText = new Label();
        titleText.setTextFill(Color.WHITESMOKE);
        titleText.setFont(myFont);
        titleText.setText(title);
        // titleText.setFont(myFont);
        // titleText.setStroke(Color.WHITESMOKE);
        // titleText.setStrokeWidth(1.5);
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
    }
}
