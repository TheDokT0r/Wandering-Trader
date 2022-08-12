import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

public class Intro {
    int current;
    String introPath;

    private JButton button1;
    private JLabel lbl_text;
    private JPanel IntroPanel;
    private World world;
    private JFrame frame;

    public Intro(World world) {
        this.world = world;
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
        frame.setSize(1280, 720);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        introPath = "./data/.intro";
        String[] parts = readIntro(introPath);
        current = 0;
        setIntro(parts, current);

        button1.addActionListener(e -> {
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
        frame.pack();
        frame.setVisible(true);
    }
}