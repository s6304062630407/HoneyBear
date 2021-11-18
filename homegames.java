package project;
import javax.swing.*;
import java.awt.*;

public class homegames extends JPanel {
    private ImageIcon feild = new ImageIcon(this.getClass().getResource("start.jpg"));
    private ImageIcon exit = new ImageIcon(this.getClass().getResource("exit.png"));
    private ImageIcon starts = new ImageIcon(this.getClass().getResource("start1.png"));
    public JButton BStart = new JButton(starts);
    public JButton BExit1 = new JButton(exit);

    public homegames() {
        setLayout(null);
        BExit1.setBounds(70, 320, 250, 100);
        add(BExit1);
        BStart.setBounds(70, 200, 250, 100);
        add(BStart);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(feild.getImage(), 0, 0, 1000, 750, this);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Terminal", Font.CENTER_BASELINE, 90));
        g.drawString("Honey Bear", 70, 150);
    }


    private void setLocationRelativeTo(Object o) {
    }

    private void setDefaultCloseOperation(int exitOnClose) {
    }
}

class HoneyBear extends JFrame {
     HoneyBear() {
        setTitle("HoneyBear");
        add(new homegames()); }
    
    public static void main(String[] args) {
        HoneyBear fram =new HoneyBear();
        fram.setSize(900,650);
        fram.setLocationRelativeTo(null);
        fram.setDefaultCloseOperation(EXIT_ON_CLOSE);
        fram.setVisible(true);
    }
}

