import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    private JPanel MenuPanel;
    private JLabel lbl_data;
    private JLabel lbl_worldData;
    private JButton btn_moveCity;
    private JButton btn_shops;
    private JList<String> lst_inventory;
    private JLabel lbl_inv;
    private JLabel lbl_stuts;
    private JButton btn_sleep;
    World GameWorld;
    int W, H; //Resolution
    boolean fullscreen;


    public Menu(World world) throws Exception {
        GameWorld = world;

        Settings settings = new Settings();
        W = settings.getRes()[0];
        H = settings.getRes()[1];

        fullscreen = settings.fullscreen();
    }

    public void start() {
        showData();
        setLst_inventory();

        JFrame frame = new JFrame("Menu");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(MenuPanel);
        frame.setSize(W, H);

        if (fullscreen) {
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setUndecorated(true);
            frame.setVisible(true);
        }

        frame.setVisible(true);

        btn_shops.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ShopsList shopsList = new ShopsList(GameWorld.getCurrentCity(), GameWorld);
                    shopsList.start();
                    frame.dispose();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    public void showData() {
        Player player = GameWorld.Player;

        StringBuilder worldData = new StringBuilder("");
        worldData.append("World Name: ").append(GameWorld.WorldName);
        worldData.append("\n || Current City: ").append(GameWorld.getCurrentCity().getCityName());
        lbl_worldData.setText(worldData.toString());

        String playerData = "" + "Name: " + player.getPlayerName() +
                "\n || Balance: " + player.getBalance();

        lbl_data.setText(playerData);
    }


    public void setLst_inventory() {
        final DefaultListModel<String> model = new DefaultListModel<>();

        Item randomItem = GameWorld.Items[0];
        Item randomItem2 = GameWorld.Items[1];
        GameWorld.Player.addItem(randomItem, 20);
        GameWorld.Player.addItem(randomItem2, 80);

        Item[] inv = GameWorld.Player.getInventory();
        int[] invQuantity = GameWorld.Player.getInvQuantity();

        for (int i = 0; i < inv.length; i++) {
            model.addElement(inv[i].getItemName() + " || " + invQuantity[i]);
        }
        lst_inventory.setModel(model);
    }
}
