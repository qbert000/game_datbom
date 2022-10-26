package hellofx.GameUI;

import com.almasb.fxgl.scene.SubScene;
import javafx.scene.effect.Bloom;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import static com.almasb.fxgl.dsl.FXGL.*;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.FXGLForKtKt;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import com.almasb.fxgl.ui.FontType;
import static hellofx.Constant.GameConstant.*;
import static hellofx.Main.myLevel;
import static hellofx.Music.SoundEffect.*;

public class StartScene extends SubScene{
    public StartScene() {
        play("stage_start.wav");
        Font myFont = FXGLForKtKt.getUIFactoryService().newFont(FontType.GAME, 80);
        var background = new Rectangle(TITLE_SIZE * 32, TITLE_SIZE * 19, Color.color(0, 0, 0, 1));
        Label title = new Label();
        title.setTextFill(Color.WHITESMOKE);
        title.setFont(myFont);
        title.setText("STAGE " + myLevel);
        // title.setFont(myFont);
        // title.setStroke(Color.WHITESMOKE);
        // title.setStrokeWidth(1.5);
        title.setEffect(new Bloom(0.6));
        title.setMinWidth(getAppWidth());
        title.setTranslateY(getAppHeight() / 2.0 - 50);
        title.setAlignment(Pos.CENTER);
        // title.setX(TITLE_SIZE * 32 / 2 - 70);   
        // title.setY(TITLE_SIZE * 19 / 2);
        getContentRoot().getChildren().addAll(background, title);

        animationBuilder()
                .onFinished(() -> popSubScene())
                .duration(Duration.seconds(2))
                .fade(getContentRoot())
                .from(1)
                .to(1)
                .buildAndPlay(this);
    }

    public void popSubScene() {
        getGameTimer().runOnceAfter(() ->{
            turnOnMusic();
        }, Duration.seconds(1.5));
        getSceneService().popSubScene();
    }
}
