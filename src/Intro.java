import javax.swing.*;
import java.io.File;
import java.util.Scanner;

public class Intro {
    private JButton button1;
    private JLabel lbl_text;
    private JPanel IntroPanel;

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


    public void start() {
        JFrame frame = new JFrame("Intro");
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(IntroPanel);
        frame.pack();
        frame.setVisible(true);
    }
}