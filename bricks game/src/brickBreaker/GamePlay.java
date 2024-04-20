package brickBreaker;
import javax.swing.Timer;
import java.util.* ;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
public class GamePlay extends JPanel implements  KeyListener , ActionListener{
    public int level = 1;
    private boolean play = false;
    private int score = 0;
    private JButton pauseButton;
    private int totalBricks = (5 + level) * (7+level);
    private Timer timer; 
    private int delay = 8;
    private int playerX = 310;
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private Map map;

    public GamePlay(){
        setLayout(null);
        map = new Map(5+level, 7+level , level);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
         timer = new Timer(delay,this);
         timer.start();

         pauseButton = new JButton(play ? "Pause" : "Play");
         pauseButton.setBounds(300, 10, 80, 30); // adjust position and size as needed
         pauseButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 play = !play; // toggle play
                 pauseButton.setText(play ? "Pause" : "Play"); // update button text
             }
         });
         add(pauseButton);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //background
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        //drawing map
        map.draw((Graphics2D) g);
        //borders
        g.setColor(Color.red);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 693, 3);
        g.fillRect(691, 0, 3, 592);
        
        //levels
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Level: " + level, 10, 30);
        //scores
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(""+score, 590, 30);


        g. setColor (Color. green) ;
         g. fillRect(playerX, 550, 100, 8);
            
         g. setColor (Color. YELLOW) ;
         g. fillOval(ballposX, ballposY, 20, 20);
        
         if(totalBricks <= 0){
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You Won, Scores: "+ score , 260, 300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to go on ", 230, 350);
         }
        if (ballposY > 570) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over, Scores: "+ score , 190, 300);
            
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter restart ", 230, 350);
        }
        pauseButton.setText(play ? "Pause" : "Play"); // update button text
    }

        
        @Override
        public void keyTyped(KeyEvent e) {}
        @Override
        public void keyReleased(KeyEvent e) {}
        @Override
        public void actionPerformed(ActionEvent e) {
            timer.start();

            if(play){
                if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))){
                   ballYdir = -ballYdir;
                }
              A:  for(int i = 0; i < map.map.length; i++){
                    for(int j = 0; j < map.map[0].length; j++){
                        if(map.map[i][j] > 0){
                            int brickX = j * map.brickWidth + 80;
                            int brickY = i * map.brickHeight + 50;
                            int brickWidth = map.brickWidth;
                            int brickHeight = map.brickHeight;
                            
                            Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                            Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
                            Rectangle brickRect = rect;
                            
                            if(ballRect.intersects(brickRect)){
                                map.setBrickValue(0, i, j);
                                totalBricks--;
                                score += 5;
                                
                                if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width){
                                    ballXdir = -ballXdir;
                                }else{
                                    ballYdir = -ballYdir;
                                }
                                break A;
                            }
                        }
                    }
                }
              ballposX += ballXdir;
                ballposY += ballYdir;
                if(ballposX < 0){
                    ballXdir = -ballXdir;
                }
                if(ballposY < 0){
                    ballYdir = -ballYdir;
                }
                if(ballposX > 670){
                    ballXdir = -ballXdir;
                }
                
            }
            repaint();
        }
        @Override
        public void keyPressed(KeyEvent e) {

            if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                if (playerX >= 600) {
                playerX = 600;
                    
                }else{
                    moveRight();
                }
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT){
                if (playerX < 10) {
                playerX = 10;
                    
                }else{
                    moveleft();
                }
            }
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    if(!play){
                        play = true;
                        ballposX = 120;
                        ballposY = 350;
                        ballXdir = -1;
                        ballYdir = -2;
                        playerX = 310;
                        score = 0;
                        if (totalBricks == 0 && level < 5) { // check if all bricks are destroyed and level is less than 5
                level++; // increment level
            } else if (level == 5) {
                level = 1; // reset level or stop the game
            }
            totalBricks =  (5 + level) * (7+(level)); // calculate total bricks based on level
            map = new Map ((5 + level), (7 + level), level); // create new map based on level
            repaint();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    play = !play; // toggle play
                    pauseButton.setText(play ? "Pause" : "Play"); // update button text
                    requestFocusInWindow();
                }
            
        }
    private void moveRight() {
        play = true;
        playerX+=20;
        }
        private void moveleft() {
            play = true;
            playerX-=20;
        }
        
   
   
   

  

   
    
}
