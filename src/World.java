public class World {
    private final int ID;
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


    public City getCurrentCity() {
        int cityID = Player.getCurrentCityID();

        for (City city : Cities) {
            if (city.getID() == cityID) {
                return city;
            }
        }

        return null;
    }
}
