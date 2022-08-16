import org.json.simple.ItemList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopData {
    private JPanel ShopPanel;
    private JLabel lbl_shop;
    private JList<String> lst_items;
    private JTextField txt_quantity;
    private JLabel lbl_quantity;
    private JButton btn_buy;
    private JLabel lbl_price;
    World world;
    Shop shop;

    public ShopData(World world, Shop shop) {
        this.world = world;
        this.shop = shop;
    }


    public void start() throws Exception{
        set_data();
        selectItem();
        buyItem();

        Settings settings = new Settings();
        int w = settings.getRes()[0];
        int h = settings.getRes()[1];

        JFrame frame = new JFrame("Menu");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(ShopPanel);
        frame.setSize(w, h);

        if (settings.fullscreen()) {
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setUndecorated(true);
            frame.setVisible(true);
        }

        frame.setVisible(true);
    }


    void set_data() {
        lbl_shop.setText(shop.getShopName()); //Set title

        //Set list
        final DefaultListModel<String> model = new DefaultListModel<>();
        for (int i = 0; i < shop.getInventory().length; i++) {
            Item item = shop.getInventory()[i];
            int itemQ = shop.getInvQuantity()[i];

            model.addElement(item.getItemName() + " || Q: " + itemQ);
        }

        lst_items.setModel(model);

        //Set quantity
        lbl_quantity.setText("/0");
        txt_quantity.setText("0");
    }

    void selectItem() { //Select an item from the list
        lst_items.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = lst_items.getSelectedIndex();
                lbl_quantity.setText("/" + shop.getInvQuantity()[index]);
                txt_quantity.setText("0");

                lbl_price.setText(shop.getInvPrices()[index] + world.CurrencySymbol);
            }
        });
    }

    void buyItem() { //Buy an item from the list
        btn_buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = lst_items.getSelectedIndex();

                int quantity = Integer.parseInt(txt_quantity.getText());
                if(quantity > shop.getInvQuantity()[index]) { //If over max quantity
                    return;
                }

                int price = shop.getInvPrices()[index];

                if(price * quantity > world.Player.getBalance()) { //If the player don't have enough money
                    return;
                }

                world.Player.addItem(shop.getInventory()[index], quantity);
                shop.removeFromInv(shop.getInventory()[index], quantity);

                for (int i = 0; i < world.Shops.length; i++) { //Parses the shop to the world object
                    if(world.Shops[i].getID() == shop.getID()) {
                        world.Shops[i] = shop;
                    }
                }

                //set_data(); //Refresh displayed data
            }
        });
    }
}
