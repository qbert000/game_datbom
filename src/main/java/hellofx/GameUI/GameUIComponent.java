package hellofx.GameUI;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGL.addUINode;
import com.almasb.fxgl.dsl.FXGLForKtKt;
import com.almasb.fxgl.ui.FontType;
import javafx.scene.effect.MotionBlur;
import javafx.geometry.Pos;

public class GameUIComponent {
    public static void addILabelUI(String varName, String title, double x, double y) {
        Label text = new Label();
        text.setTextFill(Color.BLACK);
        Font myFont = FXGLForKtKt.getUIFactoryService().newFont(FontType.GAME, 26);
        text.setFont(myFont);
        text.textProperty().bind(getip(varName).asString(title));
        addUINode(text, x, y);
    }

    public static void addDLabelUI(String varName, String title, double x, double y) {
        Label text = new Label();
        text.setTextFill(Color.BLACK);
        Font myFont = FXGLForKtKt.getUIFactoryService().newFont(FontType.GAME, 26);
        text.setFont(myFont);
        text.textProperty().bind(getdp(varName).asString(title));
        addUINode(text, x, y);
    }

    public static Label scoreOnScreen(int score) {
        Font myFont = FXGLForKtKt.getUIFactoryService().newFont(FontType.GAME, 22);
        Label titleText = new Label();
        titleText.setTextFill(Color.WHITE);
        titleText.setFont(myFont);
        titleText.setText("+" + score);
        titleText.setAlignment(Pos.CENTER);
        return titleText;
    }
}
