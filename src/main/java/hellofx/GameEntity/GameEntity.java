package hellofx.GameEntity;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;

public abstract class GameEntity extends Component {
    public Entity getMyEntity() {
        return entity;
    }

    public double currentPosX;
    public double currentPosY;

    public abstract void onUpdate(double tpf);
}
