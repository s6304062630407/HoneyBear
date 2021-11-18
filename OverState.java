package project;
import java.awt.*;
import javax.swing.*;
public class OverState extends JPanel {
    private ImageIcon feildover = new ImageIcon("gameover.png");
    private ImageIcon exitover = new ImageIcon("exit1.png");
    private ImageIcon restart = new ImageIcon("start2.png");
    public JButton BStartover = new JButton(restart);
    public JButton BExitover = new JButton(exitover);

    OverState(){
        this.setLayout(null);
        BExitover.setBounds(500, 650, 150, 90);
        add(BExitover);
        add(BStartover);
        BStartover.setBounds(750, 650, 150, 90);
        add(BStartover);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(feildover.getImage(), 0, 0, 1000, 800, this);
    }
}
