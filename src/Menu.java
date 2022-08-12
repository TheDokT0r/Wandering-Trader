import javax.swing.*;
import java.util.Arrays;

public class Menu {
    private JPanel MenuPanel;
    private JLabel lbl_data;
    private JLabel lbl_worldData;
    World GameWorld;

    public Menu(World world) {
        GameWorld = world;
    }

    public void start() {
        showData();

        JFrame frame = new JFrame("Intro");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(MenuPanel);
        frame.setSize(1280, 720);
        //frame.pack();
        frame.setVisible(true);
    }

    public void showData() {
        Player player = GameWorld.Player;

        StringBuilder worldData = new StringBuilder("");
        worldData.append("World Name: ").append(GameWorld.WorldName);
        worldData.append("\n || Current City: ").append(GameWorld.getCurrentCity().getCityName());
        lbl_worldData.setText(worldData.toString());

        StringBuilder playerData = new StringBuilder("");
        playerData.append("Name: ").append(player.getPlayerName());
        playerData.append("\n || Balance: ").append(player.getBalance());
        playerData.append("\n || Inventory: ").append(Arrays.toString(player.getInventory()));

        lbl_data.setText(playerData.toString());
    }
}
