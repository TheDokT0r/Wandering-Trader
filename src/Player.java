import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
    private final String PlayerName;
    private Item[] Inventory;
    private int[] InvQuantity;
    private final int CurrentCityID;
    private final int Balance;

    public Player(String name, int startingCityID, int balance) {
        PlayerName = name;
        Inventory = new Item[0];
        InvQuantity = new int[0];
        CurrentCityID = startingCityID;
        Balance = balance;
    }

    String getPlayerName() {
        return PlayerName;
    }

    Item[] getInventory() {
        return Inventory;
    }

    int[] getInvQuantity() {
        return InvQuantity;
    }

    int getCurrentCityID() {
        return CurrentCityID;
    }

    int getBalance() {
        return Balance;
    }

    public void addItem(@NotNull Item item, int quantity) {
        if (isItemInInventory(item.getID())) { //Set quantity to the item in the array, as it already exists in the inventory
            int i = itemIndex(item.getID());
            InvQuantity[i] = quantity;
            return;
        }

        Item[] temp = new Item[Inventory.length + 1]; //Item doesn't exist in inventory
        System.arraycopy(Inventory, 0, temp, 0, temp.length - 1);
        temp[Inventory.length] = item;
        Inventory = temp;

        int[] quantityTemp = new int[InvQuantity.length + 1];
        System.arraycopy(InvQuantity, 0, quantityTemp, 0, quantityTemp.length - 1);
        quantityTemp[InvQuantity.length] = quantity;
        InvQuantity = quantityTemp;
    }

    public void setQuantity(int itemID, int quantity) {
        for (int i = 0; i < Inventory.length; i++) {
            if (Inventory[i].getID() == itemID) {
                InvQuantity[i] = quantity;

                if (InvQuantity[i] == 0) { //Removes item from the inventory to save memory
                    removeFromInv(itemID);
                }

                return;
            }
        }
    }

    public void removeFromInv(int itemID) {
        for (int i = 0; i < Inventory.length; i++) {
            if (Inventory[i].getID() == itemID) {
                List<Item> invList = Arrays.asList(Inventory); //Initialize the arrays as list (easier to remove items that way)
                List<Integer> quantityList = new ArrayList<>();
                for (int j = 0; j < InvQuantity.length; j++) {
                    quantityList.add(i, InvQuantity[i]);
                }

                invList.remove(i);
                quantityList.remove(i);

                Inventory = (Item[]) invList.toArray();

                InvQuantity = new int[quantityList.size()];
                for (int j = 0; j < quantityList.size(); j++) { //Adds them one-by-one to the array as yo ucan't initialize a premitive-type list
                    InvQuantity[i] = quantityList.get(i);
                }

                return;
            }
        }
    }

    public void resetInventory() {
        Inventory = new Item[0];
    }

    boolean isItemInInventory(int itemID) {
        for (Item item : Inventory) {
            if (item.getID() == itemID) {
                return true;
            }
        }

        return false;
    }

    int itemIndex(int itemID) { //Returns the position of the item in the inventory
        for (int i = 0; i < Inventory.length; i++) {
            if (Inventory[i].getID() == itemID) {
                return i;
            }
        }

        return -1; //Doesn't exists
    }
}
