public class City {
    private final int ID;
    private final String CityName;
    private int Population;
    private final int MinLevel; //Minimum level to access the city

    private int[] ConnectedCitiesID; //ID of the cities that can be went to from current city (Don't connect to current city)
    private Shop[] Shops;

    public City(int ID, String cityName, int population, int minLevel, Shop[] shops, int[] connectedCitiesID) {
        this.ID = ID;
        CityName = cityName;
        Population = population;
        MinLevel = minLevel;
        Shops = shops;
        ConnectedCitiesID = connectedCitiesID;
    }

    public int getID()
    {
        return ID;
    }
    public String getCityName() {
        return CityName;
    }
    public int getPopulation() {
        return Population;
    }
    public int getMinLevel() {
        return MinLevel;
    }
    Shop[] getShops() {
        return Shops;
    }
    int[] getConnectedCitiesID() { return ConnectedCitiesID; }

    public void addShop(Shop shop) {
        Shop[] temp = new Shop[Shops.length + 1]; //Item doesn't exist in inventory
        System.arraycopy(Shops, 0, temp, 0, temp.length);
        temp[Shops.length] = shop;
        Shops = temp;
    }
}
