package hellofx;

import com.almasb.fxgl.pathfinding.CellMoveComponent;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;
import com.almasb.fxgl.entity.component.Component;

public class PlayerComponent extends Component {
    public double speed = 0;

    public CellMoveComponent cell;
    public AStarMoveComponent aster;


    public PlayerComponent() {

    }

    public void moveRight() {
        aster.moveToRightCell();
    }

    public void moveLeft() {
        aster.moveToLeftCell();
    }

    public void moveUp() {
        aster.moveToUpCell();
    }

    public void moveDown() {
        aster.moveToDownCell();
    }
}
