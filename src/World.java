public class World {
    private int ID;
    public final String WorldName;
    public Player Player;
    public City[] Cities;
    public Shop[] Shops;
    public Item[] Items; //All available items that can exist in the game
    public String CurrencySymbol;

    public World(String worldName, int ID, Player player, City[] cities, Shop[] shops, Item[] items, String currencySymbol) {
        this.ID = ID;
        WorldName = worldName;
        Player = player;
        Cities = cities;
        Shops = shops;
        Items = items;
        CurrencySymbol = currencySymbol;
    }
}
