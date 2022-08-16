import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Shop {
    private final int ID;
    private final String ShopName;
    private final String TraderName;
    private final String ShopType;

    private final Item[] AcceptedItems; //Items that this shop can have
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
    public Item[] getInventory() {return Inventory;}
    public int[] getInvQuantity() {return InvQuantity;}
    public int[] getInvPrices() {return InvPrices;}


    /*public void setInventory() { //Should be set daily
        Inventory = new Item[0];
        InvQuantity = new int[0];
        InvPrices = new int[0];

        Random rand = new Random();
        for (int i = 0; i < AcceptedItems.length; i++) {
            int amount = rand.nextInt(AcceptedItems[i].getScarcity());

            if (amount < 1) { //Don't add items with quantity of 0
                //Inventory
                Item[] temp = new Item[Inventory.length + 1]; //Item doesn't exist in inventory
                System.arraycopy(Inventory, 0, temp, 0, temp.length - 1);
                temp[Inventory.length] = AcceptedItems[i];
                Inventory = temp;

                //Quantity
                int[] tempQ = new int[InvQuantity.length + 1];
                System.arraycopy(InvQuantity, 0, tempQ, 0, tempQ.length - 1);
                tempQ[InvQuantity.length] = amount;
                InvQuantity = tempQ;

                //Prices
                int minPrice = AcceptedItems[i].getMinValue();
                int maxPrice = AcceptedItems[i].getMaxValue();

                int[] tempP = new int[InvPrices.length + 1]; //Item doesn't exist in inventory
                System.arraycopy(InvPrices, 0, tempP, 0, tempP.length - 1);

                System.out.println("Max price: " + maxPrice);
                System.out.println("Min price: " + minPrice);

                if (maxPrice - minPrice < 1) { //I don't even know at this point
                    maxPrice++;
                }

                tempP[InvPrices.length] = rand.nextInt(maxPrice - minPrice) + minPrice;
                InvPrices = tempP;
            }
        }
    }*/

    //Now new and better
    public void setInventory() { //Should be set daily
        List<Item> invLst = new ArrayList<Item>();
        List<Integer> quantityLst = new ArrayList<Integer>();
        List<Integer> pricesLst = new ArrayList<Integer>();

        Random rnd = new Random();
        for (Item acceptedItem : AcceptedItems) {
            int quantity = rnd.nextInt(acceptedItem.getScarcity());

            if (quantity > 1) {
                invLst.add(acceptedItem);
                quantityLst.add(quantity);

                int maxPrice = acceptedItem.getMaxValue();
                int minPrice = acceptedItem.getMinValue();

                if(maxPrice == minPrice) {
                    pricesLst.add(maxPrice);
                }
                else {
                    pricesLst.add(rnd.nextInt(maxPrice - minPrice) + minPrice);
                }
            }
        }


        //Can't convert those with toArray for some reason
        Item[] invArr = new Item[invLst.size()];
        for (int i = 0; i < invLst.size(); i++) {
            invArr[i] = invLst.get(i);
        }
        Inventory = invArr;

        InvQuantity = listToIntArr(quantityLst);
        InvPrices = listToIntArr(pricesLst);
    }

    private int[] listToIntArr(List<Integer> lst) {
        int[] arr = new int[lst.size()];

        for (int i = 0; i < lst.size(); i++) {
            arr[i] = lst.get(i);
        }

        return arr;
    }


    public void removeFromInv(Item item, int quantity) {
        for (int i = 0; i < Inventory.length; i++) {
            if (item.getID() == Inventory[i].getID()) {
                InvQuantity[i] = InvQuantity[i] - quantity;

                return;
            }
        }
    }
}