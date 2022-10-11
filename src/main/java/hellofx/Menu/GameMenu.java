package hellofx.Menu;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import javafx.geometry.Pos;
// import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
// import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGL.centerTextBind;
import static hellofx.Constant.GameConstant.*;

public class GameMenu extends FXGLMenu {
    public GameMenu() {
        super(MenuType.GAME_MENU);
        // opacity background
        Shape shape = new Rectangle(TITLE_SIZE*WIDTH_TITLE, TITLE_SIZE*HEIGHT_TITLE, Color.GRAY);
        shape.setOpacity(0.5);

        ImageView background = new ImageView();
        background.setOpacity(0.6);
        background.setImage(new Image("assets/textures/menu/bg.jpg"));
        background.setEffect(new DropShadow(5, 3.5, 3.5, Color.WHITE));

        // Drop Shadow of Text in menu
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        dropShadow.setHeight(7);
        dropShadow.setWidth(7);
        dropShadow.setOffsetX(6);
        dropShadow.setOffsetY(8);
        dropShadow.setSpread(8);

        var title = getUIFactoryService().newText(getSettings().getTitle(),Color.rgb(248, 185, 54), 90);
        title.setEffect(dropShadow);
        centerTextBind(title, getAppWidth() / 2.0, 310);

        MenuButton first = new MenuButton("RETURN", fontSize, () -> fireResume());
        first.setEffect(dropShadow);

        MenuButton second = new MenuButton("MAIN MENU", fontSize, () -> fireExitToMainMenu());
        second.setEffect(dropShadow);

        MenuButton third = new MenuButton("EXIT", fontSize, () -> fireExit());
        third.setEffect(dropShadow);

        var menuBox = new VBox(first, second, third);

        menuBox.setAlignment(Pos.CENTER_LEFT);
        menuBox.setTranslateX(getAppWidth() / 2.0 - 110);
        menuBox.setTranslateY(getAppHeight() / 2.0);
        menuBox.setSpacing(20);

        getContentRoot().getChildren().addAll(shape, background, title, menuBox);
    }
}
