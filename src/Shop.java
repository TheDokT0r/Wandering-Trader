import java.util.Random;

public class Shop {
    private final int ID;
    private final String ShopName;
    private final String TraderName;
    private final String ShopType;

    private Item[] AcceptedItems; //Items that this shop can have
    private Item[] Inventory; //Note: Inventory changes each day according to the scarcity of the item
    private int[] InvQuantity;
    private int[] InvPrices;

    public Shop(int ID, String shopName, String traderName, String shopType, Item[] acceptedItems) {
        this.ID = ID;
        ShopName = shopName;
        TraderName = traderName;
        ShopType = shopType;
        AcceptedItems = acceptedItems;
    }

    public int getID() {
        return ID;
    }
    public String getShopName() {
        return ShopName;
    }
    public String getTraderName() {
        return TraderName;
    }
    public String getShopType() { return ShopType; }

    public void setInventory() { //Should be set daily
        Inventory = new Item[0];
        InvQuantity = new int[0];
        InvPrices = new int[0];

        Random rand = new Random();
        for (int i = 0; i < AcceptedItems.length; i++) {
            int amount = rand.nextInt(AcceptedItems[i].getScarcity());

            if (amount != 0) {
                //Inventory
                Item[] temp = new Item[Inventory.length + 1]; //Item doesn't exist in inventory
                System.arraycopy(Inventory, 0, temp, 0, temp.length);
                temp[Inventory.length] = AcceptedItems[i];
                Inventory = temp;

                //Quantity
                int[] tempQ = new int[InvQuantity.length + 1];
                System.arraycopy(InvQuantity, 0, tempQ, 0, tempQ.length);
                tempQ[InvQuantity.length] = amount;
                InvQuantity = tempQ;

                //Prices
                int maxPrice = Inventory[i].getMaxValue();
                int minPrice = Inventory[i].getMinValue();

                int[] tempP = new int[InvPrices.length + 1]; //Item doesn't exist in inventory
                System.arraycopy(InvPrices, 0, tempP, 0, tempP.length);
                tempP[InvPrices.length] = rand.nextInt(maxPrice - minPrice) + minPrice;
                InvPrices = tempP;
            }
        }
    }
}
