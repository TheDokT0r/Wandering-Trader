import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ShopsList {
    private JPanel ShopsPanel;
    private JLabel lbl_title;
    private JList<String> lst_shops;
    private JButton btn_selectShop;
    private JButton btn_back;
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
        btn_selectShopClick(frame);
    }


    public void setShopsList() {
        Shop[] shops = city.getShops();
        final DefaultListModel<String> model = new DefaultListModel<>();

        for (Shop shop : shops) {
            /*if(!Objects.equals(shop.getShopName(), "NULL")) {
                model.addElement(shop.getShopName());
            }*/

            model.addElement(shop.getShopName());
        }

        lst_shops.setModel(model);
    }

    void btn_selectShopClick(JFrame frame) {
        btn_selectShop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Shop[] shops = city.getShops();

                int index = lst_shops.getSelectedIndex();
                Shop shop = city.getShops()[index];
                //System.out.println(shop.getShopName()); //Debug

                ShopData shopData = new ShopData(world, shop);
                try {
                    shopData.start();
                    frame.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
