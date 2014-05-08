package areacreator;

/**
 *
 * @author Peixoto
 */

public enum Tile {
    GRASS(0),
    DIRT(1),
    SAND(2),
    SNOW(3),
    STONE(4),
    TALL_GRASS(5);

    static Tile getTile(int i) {
        switch(i) {
            case 0:
                return GRASS;
            case 1:
                return DIRT;
            case 2:
                return SAND;
            case 3:
                return SNOW;
            case 4:
                return STONE;
            case 5:
                return TALL_GRASS;
            default: return null;    
        } 
    }
    
    private Tile(int id) {
        this.id = id;
    }
    
    private final int width = 32;
    private final int height = 32;
    private final int id;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int integer() {
        return id;
    }
    
    
}
