package org.example;

import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

public class Factory implements EntityFactory {

    @Spawns("enemy")
    public Entity newEnemy(SpawnData data) {
        return entityBuilder(data)
                .view(new Rectangle(40, 40, Color.RED))
                //.view("brick.png")
                .with(new ProjectileComponent(new Point2D(1, 0), 10))
                .build();
    }
}
