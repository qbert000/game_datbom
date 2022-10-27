package hellofx.Menu;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.input.view.KeyView;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import static hellofx.Constant.GameConstant.*;

import static com.almasb.fxgl.dsl.FXGL.*;
import static javafx.scene.input.KeyCode.*;

public class MainMenu extends FXGLMenu {
    public MainMenu() {
        super(MenuType.MAIN_MENU);
        ImageView background = new ImageView();
        background.setImage(new Image("assets/textures/menu/bg.jpg"));
        background.setOpacity(0.9);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        dropShadow.setHeight(8);
        dropShadow.setWidth(8);
        dropShadow.setOffsetX(8);
        dropShadow.setOffsetY(10);
        dropShadow.setSpread(10);

        var title = getUIFactoryService().newText(getSettings().getTitle(), Color.rgb(248, 185, 54), 130);
        title.setEffect(dropShadow);
        centerTextBind(title, getAppWidth() / 2.0, 300);

        MenuButton first = new MenuButton("NEW GAME", menuButtonFontSize, () -> newGame());
        first.setEffect(dropShadow);

        MenuButton second = new MenuButton("CONTROL", menuButtonFontSize, () -> instruct());
        second.setEffect(dropShadow);

        MenuButton third = new MenuButton("EXIT", menuButtonFontSize, () -> fireExit());
        third.setEffect(dropShadow);

        var menuBox = new VBox(first, second, third);

        menuBox.setAlignment(Pos.CENTER);
        menuBox.setMinWidth(getAppWidth());
        menuBox.setTranslateY(getAppHeight() / 2.0);
        menuBox.setSpacing(50);
        getContentRoot().getChildren().addAll(background, title, menuBox);
    }

    private void instruct() {
        GridPane pane = new GridPane();

        pane.addRow(0, getUIFactoryService().newText(" Movement      "),
                new HBox(new KeyView(W), new KeyView(S), new KeyView(A), new KeyView(D)));
        pane.addRow(1, getUIFactoryService().newText(" Placed Bomb      "),
                new KeyView(SPACE));

        getDialogService().showBox("How to Play", pane, getUIFactoryService().newButton("OK"));
    }

    public void newGame() {
        fireNewGame();
    }
}
