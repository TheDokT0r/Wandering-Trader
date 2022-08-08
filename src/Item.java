public class Item {
    private final int ID;
    private final String ItemName;

    private final int MinValue;
    private final int MaxValue;
    private final boolean IsTradable;
    private int Scarcity; //The lower, the more rare an item is

    public Item(int ID, String itemName, int minValue, int maxValue, boolean isTradable, int scarcity) {
        this.ID = ID;
        ItemName = itemName;
        MaxValue = maxValue;
        MinValue = minValue;
        IsTradable = isTradable;
        Scarcity = scarcity;
    }

    public int getID()
    {
        return ID;
    }
    public String getItemName() { return ItemName; }
    public int getMinValue()
    {
        return MinValue;
    }
    public int getMaxValue()
    {
        return MaxValue;
    }
    public int getScarcity() { return Scarcity;  }
}
