package brickBreaker;
import java.awt.*;

public class Map {
     public int map[][];
        public int brickWidth;
        public int brickHeight;
        public Map(int row, int col, int level){
            map = new int[row][col];
            for(int i = 0; i < map.length; i++){
                for(int j = 0; j < map[0].length; j++){
                    map[i][j] = 1;
                }
            }
            double reductionFactor = 1 - 0.05 * (level - 1); // calculate reduction factor based on level
        brickWidth = (int) ((540/col) * reductionFactor); // apply reduction factor
        brickHeight = (int) ((250/row) * reductionFactor); // apply reduction factor
        }
        public void draw(Graphics2D g){
            for(int i = 0; i < map.length; i++){
                for(int j = 0; j < map[0].length; j++){
                    if(map[i][j] > 0){
                        g.setColor(Color.white);
                        g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                        
                        g.setStroke(new BasicStroke(3));
                        g.setColor(Color.black);
                        g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                    }
                }
            }
        }
        public void setBrickValue(int value, int row, int col){
            map[row][col] = value;
        }
}
