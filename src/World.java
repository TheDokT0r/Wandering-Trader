public class World {
    private int ID;
    public final String WorldName;
    public Player Player;
    public City[] Cities;
    public int StartingCityID;
    public Shop[] Shops;
    public Item[] Items; //All available items that can exist in the game

    public World(String worldName, int ID, Player player, City[] cities, Shop[] shops, Item[] items, int startingCityID) {
        this.ID = ID;
        WorldName = worldName;
        Player = player;
        Cities = cities;
        Shops = shops;
        Items = items;
        StartingCityID = startingCityID;
    }
}
