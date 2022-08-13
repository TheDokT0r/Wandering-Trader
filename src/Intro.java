import javax.swing.*;
import java.io.File;
import java.util.Scanner;

public class Intro {
    int current;
    String introPath;

    private JButton btn_next;
    private JLabel lbl_text;
    private JPanel IntroPanel;
    private World world;
    private JFrame frame;
    int W, H; //Resolution
    boolean fullscreen; //Is the game in full screen

    public Intro(World world) throws Exception {
        this.world = world;

        Settings settings = new Settings();
        W = settings.getRes()[0];
        H = settings.getRes()[1];

        fullscreen = settings.fullscreen();
    }

    public void setIntro(String[] parts, int currentIndex) {
        lbl_text.setText(parts[currentIndex]);
    }

    public String[] readIntro(String path) throws Exception {
        StringBuilder txt = new StringBuilder();

        File file = new File(path);
        Scanner sc = new Scanner(file);

        while(sc.hasNextLine()) {
            txt.append(sc.nextLine());
        }

        return txt.toString().split("//"); //Splits the intro into parts
    }



    public void start() throws Exception { //Sets data on start
        frame = new JFrame("Intro");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        introPath = "./data/.intro";
        String[] parts = readIntro(introPath);
        current = 0;
        setIntro(parts, current);

        btn_next.addActionListener(e -> {
            try {
                String[] parts1 = readIntro(introPath);

                if (current < parts1.length - 1) {
                    current++;
                    setIntro(parts1, current);
                    return;
                }

                Menu menu = new Menu(world);
                menu.start();
                frame.dispose();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        frame.setContentPane(IntroPanel);
        frame.setSize(W, H);
        //frame.pack();
        if (fullscreen) {
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setUndecorated(true);
            frame.setVisible(true);
        }

        frame.setVisible(true);
    }
}