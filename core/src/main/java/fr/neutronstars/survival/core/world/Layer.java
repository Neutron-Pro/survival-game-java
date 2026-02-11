package fr.neutronstars.survival.core.world;

public class Layer<T extends Context> {

    private final World<T> world;
    private final Tile<T>[][] tiles;
    private final int id;

    public Layer(World<T> world, int id) {
        this.world = world;
        this.id = id;
        this.tiles = new Tile[world.width()][world.height()];
    }

    public int id() {
        return this.id;
    }

    public World<T> world() {
        return this.world;
    }

    public void set(int x, int y, Tile<T> tile) {
        if (this.isTitleValid(x, y)) {
            this.tiles[x][y] = tile;
        }
    }

    public Tile<T> of(int x, int y) {
        if (this.isTitleValid(x, y)) {
            return this.tiles[x][y];
        }
        return null;
    }

    private boolean isTitleValid(int x, int y) {
        return x >= 0 && x < this.world.width() && y >= 0 && y < this.world.height();
    }
}
