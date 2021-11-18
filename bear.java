package project;

import javax.swing.*;

public class bear {
    public ImageIcon[] im = new ImageIcon[3];
    public int x;
    public int count = 0;
    bear(){
        for (int i=0; i<im.length; i++){
            im[i] = new ImageIcon(this.getClass().getResource("1.png"));

        }
    }
}
