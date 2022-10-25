package hellofx.GameUI;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGL.addUINode;
import static hellofx.Constant.GameConstant.FONT_SIZE;

import java.util.List;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.FXGLForKtKt;
import com.almasb.fxgl.ui.FontType;

public class GameUIComponent {
    public static void addILabelUI(String varName, String title, double x, double y) {
        Label text = new Label();
        text.setTextFill(Color.BLACK);
        Font myFont = FXGLForKtKt.getUIFactoryService().newFont(FontType.GAME, FONT_SIZE);
        text.setFont(myFont);
        text.textProperty().bind(getip(varName).asString(title));
        addUINode(text, x, y);
    }

    public static void addDLabelUI(String varName, String title, double x, double y) {
        Label text = new Label();
        text.setTextFill(Color.BLACK);
        Font myFont = FXGLForKtKt.getUIFactoryService().newFont(FontType.GAME, FONT_SIZE);
        text.setFont(myFont);
        text.textProperty().bind(getdp(varName).asString(title));
        addUINode(text, x, y);
    }
}
