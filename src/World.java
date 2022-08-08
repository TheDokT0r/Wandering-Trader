public class World {
    private int ID;
    public Player Player;
    public City[] Cities;
    public Shop[] Shops;

    public World(int ID, Player player, City[] cities, Shop[] shops) {
        this.ID = ID;
        Player = player;
        Cities = cities;
        Shops = shops;
    }
}
