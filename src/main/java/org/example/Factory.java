package org.example;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.pathfinding.CellMoveComponent;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static org.example.Enum.*;

public class Factory implements EntityFactory {

    @Spawns("player")
    public Entity newEnemy(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(Main.EntityType.PLAYER)
                //.at(200, 200)
                .with(new AnimationComponent())
                // de va cham phai co ham nay
                .with(new CollidableComponent(true))
                // point2d la chinh vi tri box so vs vi tri ban dau
                // Point la vi tri cua hop so vs ban dau la (0,0)
                // chieu cao vs chieu rong cua hop
                .bbox(new HitBox(new Point2D(7, 7), BoundingShape.box(30, 30)))
                .build();
    }

    @Spawns("coin")
    public Entity newCoin(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(COIN)
                .viewWithBBox(new Rectangle(45, 45, Color.BLACK))
                .with(new CollidableComponent(true))
                .build();
    }
}

//file file = new file(filename);

