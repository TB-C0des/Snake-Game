
package Snakegame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
public class Outer_Panel extends JPanel implements ActionListener  {
    static final int SCREEN_WIDTH=600;
    static final int SCREEN_HEIGHT=600;
    static final int  UNIT_SIZE=25;
    static final int  GAME_UNITS=(SCREEN_WIDTH)*(SCREEN_HEIGHT)/(UNIT_SIZE);
    static final int DELAY=50;
    final int  x[]=new int[GAME_UNITS];
    final int  y[]=new int[GAME_UNITS];
    int Bodyparts=5;
    int applesEaten;
    int appleX;
    int appleY;
    char Direction='R';
    boolean running=false;
    Timer timer;
     Random random;
    Outer_Panel()
    {
        random =new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT ));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
        
    }
    public void startGame()
    {
        newApple();
        running = true;
        timer = new Timer(DELAY,this);
        timer.start();
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g)
    {
         if(running)
         {
            for(int i=0;i<SCREEN_HEIGHT;++i)
          {
             g.drawLine(i*UNIT_SIZE, 0, UNIT_SIZE*i ,SCREEN_HEIGHT);
          }
          for(int i=0;i<SCREEN_WIDTH;++i)
          {
              g.drawLine(0, UNIT_SIZE*i, SCREEN_WIDTH ,i*UNIT_SIZE);
          }
          g.setColor(Color.red);
          g.fillOval(appleX , appleY, UNIT_SIZE, UNIT_SIZE);
          for(int i=0;i<Bodyparts;++i)
          {
              if(i==0)
              {
                  g.setColor(Color.green);
                  g.fillOval(x[i], y[i],UNIT_SIZE, UNIT_SIZE);
              }
              else
              {
                  //g.setColor(new Color(45,100,100));
                 // g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                    g.setColor(Color.blue);
                  g.fillOval(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
              }
           }
              g.setColor(Color.red);
          g.setFont(new Font("Ink Free",Font.BOLD,40));
          FontMetrics metrics = getFontMetrics(g.getFont());
          g.drawString("Score:"+applesEaten, (SCREEN_WIDTH-metrics.stringWidth("Score"))/2,(int)(g.getFont().getSize()));
         }
         else {
             gameOver(g);
         }
    }
    public void newApple()
    {
         appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
         appleY = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
    }
    public void move()
    {
        for(int i=Bodyparts;i>0;i--)
        {
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        switch(Direction){
            case 'U':
                y[0]=y[0]-UNIT_SIZE;
                break;
            case 'D':
                y[0]=y[0]+UNIT_SIZE;
                break;
            case 'L':
                x[0]=x[0]-UNIT_SIZE;
                break;
            case 'R':
                x[0]=x[0]+UNIT_SIZE;
                break;
        }
    }
    public void checkApple()
    {
          if((x[0]==appleX) && (y[0]==appleY))
          {    Bodyparts++;
          applesEaten++;
          newApple();}
    }
    public void checkcollisions()
    {
        for(int i=Bodyparts;i>0;i--)
        {
            if((x[0]==x[i])&&(y[0]==y[i]))
            {
                running=false;
            }
            if(x[0]<0)
            {
                running=false;
            }
            if(x[0]>SCREEN_WIDTH)
            {
                running=false;
            }
            if(y[0]<0)
            {
                running=false;
            }
             if(y[0]>SCREEN_HEIGHT)
            {
                running=false;
            }
            if(!running)
            {
                timer.stop();
            }
        }
    }
    public void gameOver(Graphics g)
    {
          g.setColor(Color.red);
          g.setFont(new Font("Ink Free",Font.BOLD,75));
          FontMetrics metrics = getFontMetrics(g.getFont());
          g.drawString("Game Over", (SCREEN_WIDTH-metrics.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
             g.setColor(Color.red);
          g.setFont(new Font("Ink Free",Font.BOLD,40));
          g.drawString("Score:"+applesEaten, (SCREEN_WIDTH-metrics.stringWidth("Score"))/2,(int)(g.getFont().getSize()));

    }
    public void actionPerformed(ActionEvent e)
    {
         if(running)
         {
             move();
             checkApple();
             checkcollisions();
         }
         repaint();
    }
    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e)
        {
               switch(e.getKeyCode())
               {
                   case KeyEvent.VK_LEFT:
                       if(Direction!='R')
                       {
                           Direction='L';
                       }
                       break;
                       case KeyEvent.VK_RIGHT:
                       if(Direction!='L')
                       {
                           Direction='R';
                       }
                       break;
                       case KeyEvent.VK_UP:
                       if(Direction!='D')
                       {
                           Direction='U';
                       }
                       break;
                       case KeyEvent.VK_DOWN:
                       if(Direction!='U')
                       {
                           Direction='D';
                       }
                       break;
               }
        }
    }
    }


