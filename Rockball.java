package project;

import javax.swing.*;
import java.awt.geom.Rectangle2D;
public class Rockball extends JPanel{
    public ImageIcon[] imrock = new ImageIcon[2];
    public int y;
    public int x;
    public int count=0;
    Rockball(int x, int y){
        for(int i=0;i<imrock.length;i++){
            String imageLocation = "h"+(i+1)+".png";
            imrock[i] = new ImageIcon(this.getClass().getResource(imageLocation));
        }
        this.x=x;
        this.y=y;
    }

    public void move(){
        this.y-=1;
    }
    public Rectangle2D getbound(){
        return (new Rectangle2D.Double(x,y,25,25));
    }
}
