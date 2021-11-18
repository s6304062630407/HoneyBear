package project;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.*;
public class HoneyState extends JPanel implements ActionListener{
    private final ImageIcon imgstate1 = new
            ImageIcon(this.getClass().getResource("honeyS.jpg"));
    private final ImageIcon imgstate2 = new
            ImageIcon(this.getClass().getResource("honeyS2.jpg"));
    private final ImageIcon imgbear = new
            ImageIcon(this.getClass().getResource("bear1.png"));
    private final ImageIcon pause = new
            ImageIcon(this.getClass().getResource("pause.png"));
    private final ImageIcon resum = new
            ImageIcon(this.getClass().getResource("resum.png"));
    private final ImageIcon back = new
            ImageIcon(this.getClass().getResource("back.png"));

    bear b = new bear();
    homegames hg = new homegames();

    ImageIcon feildover = new ImageIcon(this.getClass().getResource("c1b298c9be13f79069a10ad2ddba7c3e.jpg"));
    ImageIcon img_paralyze = new ImageIcon(this.getClass().getResource("shy1.png"));
    ImageIcon exitover = new ImageIcon(this.getClass().getResource("exit1.png"));
    ImageIcon restart = new ImageIcon(this.getClass().getResource("start2.png"));
    JButton BStartover = new JButton(restart);
    JButton BExitover = new JButton(exitover);

    private JLabel score = new JLabel();
    public JButton BPause = new JButton(pause);
    public JButton BExithome = new JButton(back);
    public JButton Bresum = new JButton(resum);

    public ArrayList<Rockball> rockball = new ArrayList<Rockball>();
    public ArrayList<bee1> ba1 = new ArrayList<bee1>();
    public ArrayList<bee2> ba2 = new ArrayList<bee2>();
    public ArrayList<bee3> ba3 = new ArrayList<bee3>();

    public int times ;
    public int HP = 3;
    public int rs1 = 1;
    public int rs2 = 2;
    boolean timestart = true;
    boolean startbee = false;

    private OverState gover = new OverState();

    public int scor = 0;
    boolean paralyze1 = false;
    int time_paralyze = 5;

    Thread time = new Thread(new Runnable(){
        public void run(){
            while(true){
                try{
                    Thread.sleep(10);
                }catch(Exception e){ }

                if(timestart == false){
                    repaint();
                }
            }
        }
    });
    Thread actor = new Thread(new Runnable(){
        public void run(){
            while(true){
                try{
                    Thread.sleep(1);
                }catch(Exception e){}
                repaint();
            }
        }
    });
    Thread tbee1 = new Thread(new Runnable(){
        public void run() {
            while(true){
                try{
                    if(startbee == false){
                        Thread.sleep((long)(Math.random()*10000)+2000);
                    }
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                if(startbee == false){
                    ba1.add(new bee1());
                }
            }
        }
    });
    Thread tbee2 = new Thread(new Runnable(){
        public void run() {
            while(true){
                try{
                    if(startbee == false){
                        Thread.sleep((long)(Math.random()*10000)+2000);
                    }
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                if(startbee == false){
                    ba2.add(new bee2());
                }
            }
        }
    });
    Thread tbee3 = new Thread(new Runnable(){
        public void run() {
            while(true){
                try{
                    if(startbee == false){
                        Thread.sleep((long)(Math.random()*10000)+2000);
                    }
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                if(startbee == false){
                    ba3.add(new bee3());
                }
            }
        }
    });
    Thread paralyze = new Thread(new Runnable() {
        public void run() {
            while (true) {
                if (time_paralyze < 1) {
                    paralyze1 = false;
                    time_paralyze = 5;
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });
    Thread t = new Thread(new Runnable(){
        public void run() {
            while(true){
                if(timestart == false){
                    times = (times-1) ;
                    if(paralyze1){
                        time_paralyze--;
                    }
                }
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    });
    HoneyState(){
        this.setFocusable(true);
        this.setLayout(null);
        BPause.setBounds(850,120,90,98);
        BExithome.setBounds(850,20,90,98);
        Bresum.setBounds(850, 220, 90,98);
        BPause.addActionListener(this);
        BExithome.addActionListener(this);
        Bresum.addActionListener(this);
        BExithome.addActionListener(this);
        this.add(BPause);
        this.add(BExithome);
        this.add(score);
        this.add(Bresum);
        this.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                int a = e.getKeyCode();
                if(!paralyze1){
                    if(a==KeyEvent.VK_A){
                        b.x-=10;
                        b.count++;
                    }
                    else if(a == KeyEvent.VK_D){
                        b.x+=10;
                        b.count++;
                    }
                    if(b.count>3){
                        b.count=0;
                    }
                    else if(a == KeyEvent.VK_UP){
                        b.count=5;
                        rockball.add(new Rockball(b.x,550));
                    }
                }
            }
            public void keyReleased(KeyEvent e){
                b.count=0;
            }
        });
        b.x = 400;
        time.start();
        actor.start();
        t.start();
        tbee1.start();
        tbee2.start();
        tbee3.start();
        paralyze.start();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(times <= 0 || HP<=0){
            this.remove(BPause);
            this.remove(Bresum);
            this.remove(BExithome);
            this.setLayout(null);
            g.drawImage(feildover.getImage(),0,0,1000,800,this);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Hobo Std",Font.HANGING_BASELINE,40));
            g.drawString("SCORE is "+scor,300,550);
            g.setFont(new Font("Hobo Std",Font.HANGING_BASELINE,70));
            g.drawString("GAME OVER",150,500);
            g.drawImage(imgbear.getImage(), 580, 360, 400, 400, this);
        }else if(times <= 50){
            g.drawImage(imgstate2.getImage(),0,0,1000,800,this);
            if(paralyze1){
                g.setColor(Color.WHITE);
                g.setFont(new Font("Hobo Std",Font.BOLD,50));
                g.drawImage(img_paralyze.getImage(), b.x, 550,100,150, this);
                g.drawString("AHHHH !!!", b.x+100, 560);
            }else{
                g.drawImage(b.im[b.count%3].getImage(), b.x, 550,110,160, this);
            }
            if(b.x<0){
                b.x=this.getWidth()-10;
            }
            if(b.x>this.getWidth()){
                b.x=20;
            }
            for(int i=0;i<rockball.size();i++){
                Rockball ba = rockball.get(i);
                g.drawImage(ba.imrock[ba.count%2].getImage(), ba.x, ba.y,50,50, null);
                ba.move();
                ba.count++;
                if(ba.y<0){
                    rockball.remove(i);
                }
            }
//===========ball1================
            for(int i=0 ; i<ba1.size();i++){
                g.drawImage( ba1.get(i).getImage(),ba1.get(i).getX(),ba1.get(i).getY(),100,100,this);
            }
            for(int i=0 ; i<rockball.size();i++){
                for(int j=0 ; j<ba1.size();j++){
                    if(Intersect(rockball.get(i).getbound(),ba1.get(j).getbound())){
                        ba1.remove(j);
                        rockball.remove(i);
                        scor += 10;
                        g.drawString("+10",b.x+100,650);
                    }
                }
            }
            //===========ball2================
            for(int i=0 ; i<ba2.size();i++){
                g.drawImage( ba2.get(i).getImage(),ba2.get(i).getX(),ba2.get(i).getY(),100,100,this);
            }
            for(int i=0 ; i<rockball.size();i++){
                for(int j=0 ; j<ba2.size();j++){
                    if(Intersect(rockball.get(i).getbound(),ba2.get(j).getbound())){
                        ba2.remove(j);
                        rockball.remove(i);
                        scor += 20;
                        g.drawString("+10",b.x+100,650);
                    }
                }
            }
            //===========ball3================
            for(int i=0 ; i<ba3.size();i++){
                g.drawImage( ba3.get(i).getImage(),ba3.get(i).getX(),ba3.get(i).getY(),100,100,this);
            }
            for(int i=0 ; i<rockball.size();i++){
                for(int j=0 ; j<ba3.size();j++){
                    if(Intersect(rockball.get(i).getbound(),ba3.get(j).getbound())){
                        ba3.remove(j);
                        rockball.remove(i);
                        scor -= 20;
                        HP = HP-1;
                        g.drawString("-HP",b.x+100,580);
                        g.drawString("-20",b.x+100,650);
                    }
                }
            }

            g.setFont(new Font("Hobo Std",Font.HANGING_BASELINE,30));
            g.setColor(Color.WHITE);
            g.drawString("SCORE = "+scor,50, this.getHeight()-10);
            g.setFont(new Font("Hobo Std",Font.HANGING_BASELINE,50));
            g.drawString("Time "+times,this.getWidth()-200,this.getHeight()-50);
            g.drawString("STATE "+rs2,350,80);
            g.setColor(Color.WHITE);
            g.drawString("HP "+HP,50,this.getHeight()-50);

        }else if(times <= 0 || HP<=0){
            this.remove(BPause);
            this.remove(Bresum);
            this.remove(BExithome);
            this.setLayout(null);
            g.drawImage(feildover.getImage(),0,0,1000,800,this);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Hobo Std",Font.HANGING_BASELINE,40));
            g.drawString("SCORE "+scor,380,200);
            g.setFont(new Font("Hobo Std",Font.HANGING_BASELINE,70));
            g.drawString("GAME OVER",290,150);
            g.drawImage(imgbear.getImage(), 580, 360, 400, 400, this);
        }else{
            g.drawImage(imgstate1.getImage(),0,0,1000,800,this);
            if(paralyze1){
                g.setColor(Color.RED);
                g.setFont(new Font("Hobo Std",Font.BOLD,50));
                g.drawImage(img_paralyze.getImage(), b.x, 550,100,150, this);
                g.drawString("-10",b.x+100,650);
                g.drawString("AHHHH !!!", b.x+100, 560);
            }else{
                g.drawImage(b.im[b.count%3].getImage(), b.x, 550,110,160, this);
            }
            if(b.x<0){
                b.x=this.getWidth()-10;
            }
            if(b.x>this.getWidth()){
                b.x=20;
            }
            for(int i=0;i<rockball.size();i++){
                Rockball ba = rockball.get(i);
                g.drawImage(ba.imrock[ba.count%2].getImage(), ba.x,
                        ba.y,50,50, null);
                ba.move();
                ba.count++;
                if(ba.y<0){
                    rockball.remove(i);
                }
            }
//========================================ball1=================
            for(int i=0 ; i<ba1.size();i++){
                g.drawImage(ba1.get(i).getImage(),ba1.get(i).getX(),ba1.get(i).getY(),100,100,this);
            }
            for(int i=0 ; i<rockball.size();i++){
                for(int j=0 ; j<ba1.size();j++){
                    if(Intersect(rockball.get(i).getbound(),ba1.get(j).getbound())){
                        ba1.remove(j);
                        rockball.remove(i);
                        scor += 10;
                        g.drawString("+10",b.x+100,650);
                    }
                }
            }
            //===========ball2================
            for(int i=0 ; i<ba2.size();i++){
                g.drawImage( ba2.get(i).getImage(),ba2.get(i).getX(),ba2.get(i).getY(),100,100,this);
            }
            for(int i=0 ; i<rockball.size();i++){
                for(int j=0 ; j<ba2.size();j++){
                    if(Intersect(rockball.get(i).getbound(),ba2.get(j).getbound())){
                        ba2.remove(j);
                        rockball.remove(i);
                        scor += 20;
                        g.drawString("+10",b.x+100,650);
                    }
                }
            }
            //===========ball3================
            for(int i=0 ; i<ba3.size();i++){
                g.drawImage( ba3.get(i).getImage(),ba3.get(i).getX(),ba3.get(i).getY(),100,100,this);
            }
            for(int i=0 ; i<rockball.size();i++){
                for(int j=0 ; j<ba3.size();j++){
                    if(Intersect(rockball.get(i).getbound(),ba3.get(j).getbound())){
                        ba3.remove(j);
                        rockball.remove(i);
                        scor -= 20;
                        HP = HP-1;
                        g.drawString("-HP",b.x+100,580);
                        g.drawString("-20",b.x+100,650);
                    }
                }
            }
            g.setFont(new Font("Hobo Std",Font.HANGING_BASELINE,30));
            g.setColor(Color.WHITE);
            g.drawString("SCORE = "+scor,50, this.getHeight()-10);
            g.setFont(new Font("Hobo Std",Font.HANGING_BASELINE,50));
            g.drawString("STATE "+rs1,350,80);
            g.drawString("Time "+times,this.getWidth()-200,this.getHeight()-50);
            g.setColor(Color.WHITE);
            g.drawString("HP "+HP,50,this.getHeight()-50);
        }
    }

    public boolean Intersect(Rectangle2D a, Rectangle2D b){
        return (a.intersects(b));
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== BStartover){
            this.setSize(1000,800);
            this.add(hg);
            this.setLocation(null);
            timestart = true;
            startbee = true;
        }else if(e.getSource() == BExitover){
            System.exit(0);
        }
    }
}