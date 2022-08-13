import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class ShopsList {
    private JPanel ShopsPanel;
    private JLabel lbl_title;
    private JList<String> lst_shops;
    City city;
    World world;

    public ShopsList(City city, World world) throws Exception {
        this.city = city;
        this.world = world;

        setShopsList();
    }


    public void start() throws Exception {
        Settings settings = new Settings();
        int W = settings.getRes()[0];
        int H = settings.getRes()[1];

        JFrame frame = new JFrame("Shops List");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(ShopsPanel);
        frame.setSize(W, H);

        if (settings.fullscreen()) {
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setUndecorated(true);
            frame.setVisible(true);
        }

        frame.setVisible(true);
    }


    public void setShopsList() {
        Shop[] shops = city.getShops();
        final DefaultListModel<String> model = new DefaultListModel<>();

        for (Shop shop : shops) {
            if(!Objects.equals(shop.getShopName(), "NULL")) {
                model.addElement(shop.getShopName());
            }
        }

        lst_shops.setModel(model);
    }
}
