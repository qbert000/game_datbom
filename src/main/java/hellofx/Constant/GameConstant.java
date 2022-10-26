package hellofx.Constant;

import static hellofx.SpawnSystem.Enum.*;

public class GameConstant {
    public static double CONST_SPEED = 1.5;
    public static final double CONST_SPEED_BEGIN = 1.5;
    public static final int TITLE_SIZE = 40;
    public static final int WIDTH_TITLE = 32;
    public static final int HEIGHT_TITLE = 18;
    public static final int starterPosX = 200;
    public static final int starterPosY = 200;
    public static final int flame4dirSize = 38;
    public static final int MAX_LEVEL = 2;
    public static final int fontSize = 36;
    public static final int menuButtonFontSize = 45;
    public static final int ENEMY_SIZE = 36;
    public static final double FONT_SIZE = 36.0;
    public static final double GAME_TIME = 180.0;
    public static int ENEMY_NUMBER = 0;
    public static int TOTAL_ENEMY = 0;
    public static Enum[] myFlameList = new Enum[] { FLAME, FLAMERIGHT, FLAMELEFT, FLAMEUP, FLAMEDOWN };
    public static Enum[] myItemList = new Enum[] { SPEED_ITEM, FLAME_POWER_ITEM, BOMB_ITEM, FLAME_ITEM };
    public static Enum[] enemyType = new Enum[] {BALLOONHORIZONTAL, BALLOONVERTICAL, ENEMYPONTAN, ENEMYPASS, ENEMYDAHL, ENEMYDORIA };
}