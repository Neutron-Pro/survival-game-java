package fr.neutronstars.survival.core.world;

public class Layer {

    private final World world;
    private final Tile[][] tiles;
    private final int id;

    public Layer(World world, int id) {
        this.world = world;
        this.id = id;
        this.tiles = new Tile[world.width()][world.height()];
    }

    public int id() {
        return this.id;
    }

    public World world() {
        return this.world;
    }

    public void set(int x, int y, Tile tile) {
        if (this.isTitleValid(x, y)) {
            this.tiles[x][y] = tile;
        }
    }

    public Tile of(int x, int y) {
        if (this.isTitleValid(x, y)) {
            return this.tiles[x][y];
        }
        return null;
    }

    private boolean isTitleValid(int x, int y) {
        return x >= 0 && x < this.world.width() && y >= 0 && y < this.world.height();
    }
}
