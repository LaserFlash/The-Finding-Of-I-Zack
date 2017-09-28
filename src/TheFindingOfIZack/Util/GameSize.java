package TheFindingOfIZack.Util;

public class GameSize {
    public static final int WINDOW_WIDTH    = 900;
    public static final int WINDOW_HEIGHT   = 600;
    public static final int MENU_HEIGHT = 50;
    public static final int GAME_HEIGHT = WINDOW_HEIGHT-MENU_HEIGHT;

    public static final int WALL_WIDTH = 40;
    public static final int LEFT_WALL = WALL_WIDTH;
    public static final int RIGHT_WALL = WINDOW_WIDTH-WALL_WIDTH;
    public static final int TOP_WALL = WALL_WIDTH;
    public static final int BOTTOM_WALL = GAME_HEIGHT-WALL_WIDTH;

}
