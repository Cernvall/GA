package ga;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import static java.lang.Math.*;
import java.util.*;

class Pendel extends Panel{
    double  fi= -PI/4; // + random()*PI/2
    double vfi=0;
    double k= 0.002;
    
    BufferedImage img= new BufferedImage(1920,1080, BufferedImage.TYPE_INT_ARGB);
    
    TimerTask task= new TimerTask(){
        @Override
        public void run() {
            fi += vfi;
            vfi += k*cos(fi);
            repaint();
        }
      
    };

    @Override
    public void update(Graphics g) {
        img.getGraphics().clearRect(0,0,1920,1080);
        paint( img.getGraphics());
        g.drawImage( img, 0,0, null);
    }

    
    
    
    
    public void paint(Graphics g, ArrayList<Point> trj) {
        double x= 400+ 200*cos(fi);
        double y= 400+ 200*sin(fi);
        g.drawLine(400,400, (int)x,(int)y);
        g.fillOval((int) x-3, (int)y-3, 6, 6);
        
       for (int i = 0; i < trj.size(); i++) {
            g.fillOval(trj.get(i).x-3, trj.get(i).y-3, 6, 6);
        }
     
    }
    
    
    
    
    public Pendel(){
        
        setPreferredSize(new Dimension(800,600));
        setBackground(Color.pink);
        
        Timer timer= new Timer();
        timer.scheduleAtFixedRate(task, 0, 12);
    }
    
    public void trajectory (ArrayList<Point> trj ){
        double x= 400+ 200*cos(fi);
        double y= 400+ 200*sin(fi);
        
        trj.add(new Point( (int) x, (int) y));
        
        Timer timer= new Timer();
        timer.scheduleAtFixedRate(task, 0, 12);
        
       
    }
}