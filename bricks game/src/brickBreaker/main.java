package brickBreaker;
import java.awt.Component;

import javax.swing.*;
public class main {
    public static void main(String[] args)  {
        JFrame obj = new JFrame();
       GamePlay gamePlay = new GamePlay();
        obj.setBounds(10, 10, 700, 600);
        obj.setTitle("Brick Hit");
           obj.setResizable(false);
           obj.setVisible(true);
             obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             obj.add(gamePlay);
 }
}


