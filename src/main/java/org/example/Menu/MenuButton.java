package hellofx.Menu;

import com.almasb.fxgl.dsl.FXGL;
import javafx.beans.binding.Bindings;
import javafx.scene.Parent;
import javafx.scene.paint.Color;

public class MenuButton extends Parent {
    MenuButton(String name, double fontSize, Runnable action) {
        var text = FXGL.getUIFactoryService().newText(name, Color.TOMATO, fontSize);
        text.setStrokeWidth(1.5);
        text.strokeProperty().bind(text.fillProperty());

        text.fillProperty().bind(
                Bindings.when(hoverProperty())
                        .then(Color.rgb(252, 209, 26))
                        .otherwise(Color.rgb(252, 136, 33))
        );

        setOnMouseClicked(e -> action.run());

        setPickOnBounds(true);
        getChildren().add(text);
    }
}
