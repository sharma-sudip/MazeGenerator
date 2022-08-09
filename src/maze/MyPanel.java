
package maze;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import javax.swing.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;


/**
 *
 * @author Sudip
 */
public class MyPanel extends JPanel {
     private final Integer BLOCK_SIZE = 10;
     private boolean[] sides;
    ArrayList<Point> arrayList;
    Timer timer;
     
    MyPanel() throws InterruptedException{
       this.setPreferredSize(new Dimension(500,500));
       sides =new boolean[20000];
       arrayList= new ArrayList<>();
       arrayList.add(new Point(0,0));
       for(int i=0; i<sides.length; i++){
            sides[i]=true;
       } 
        generateMaze(0,0);
        for(int i = 0; i<sides.length; i++){
            System.out.println(i+" "+sides[i]);
        }
    }
  
   
    
    @Override
    public void paint(Graphics g){
        Random random = new Random();
//        int random1 = random.nextInt(255);
//        int random2 = random.nextInt(255);
//        int random3 = random.nextInt(255);
        g.setColor(Color.GREEN);
         g.fillRect(0, 0, BLOCK_SIZE, BLOCK_SIZE);
        for(Point point : arrayList){
            g.setColor(Color.GREEN);
            g.fillRect(point.x, point.y, BLOCK_SIZE, BLOCK_SIZE);  
        }
        g.setColor(Color.BLACK);
        for(int i =0; i<500; i+=BLOCK_SIZE){
            for(int j=0; j<500; j+=BLOCK_SIZE){
                if(sides[BLOCK_SIZE*5*4*Math.floorDiv(i, BLOCK_SIZE)+(Math.floorDiv(j, BLOCK_SIZE)*4)]){
                    g.drawLine(j,i,j+BLOCK_SIZE,i);    
                }
                if(sides[BLOCK_SIZE*5*4*Math.floorDiv(i, BLOCK_SIZE)+(Math.floorDiv(j, BLOCK_SIZE)*4+1)]){
                     g.drawLine(j+BLOCK_SIZE,i,j+BLOCK_SIZE,i+BLOCK_SIZE);
                }
                if(sides[BLOCK_SIZE*5*4*Math.floorDiv(i, BLOCK_SIZE)+(Math.floorDiv(j, BLOCK_SIZE)*4+2)]){
                    g.drawLine(j+BLOCK_SIZE, i+BLOCK_SIZE, j, i+BLOCK_SIZE);
                }
                if(sides[BLOCK_SIZE*5*4*Math.floorDiv(i, BLOCK_SIZE)+(Math.floorDiv(j, BLOCK_SIZE)*4+3)]){
                    g.drawLine(j, i+BLOCK_SIZE, j, i);
                }
            }
        }
    }

    
     public  void generateMaze(int x, int y ) throws InterruptedException{
        recursiveUtility(x,y); 
    }
      
    public  void recursiveUtility(int x, int y) throws InterruptedException{
        if(x == 500 && y == 500){
            return;
        }
        Point p = checkNeighbours(x,y);
        if(p != null){
            System.out.println(x+" "+y);
            System.out.println("the next lado called from here point is"+p.toString());
            recursiveUtility(p.x, p.y);
            Point p1 = checkNeighbours(x,y);
            if(p1 != null){
                recursiveUtility(p1.x, p1.y);
                System.out.println("the next puti called from here point is"+p.toString());
            }
        }
    }
    
    
    public static boolean checkEdges(int x, int y){
        return x>=0 && y>=0 && x<500 && y<500; 
    }
    
    public Point checkNeighbours(Integer x, Integer y)
    {
        Point neighbours[] ={new Point(x-BLOCK_SIZE,y), new Point(x+BLOCK_SIZE,y), new Point(x, y-BLOCK_SIZE), new Point(x,y+BLOCK_SIZE)};
        Collections.shuffle(Arrays.asList(neighbours));
        for(Point point : neighbours ){
            if(!arrayList.contains(point) && checkEdges(point.x, point.y)){
                arrayList.add(point);
                removeWalls(new Point(x,y), point);
                System.out.println("Walls removed between "+x+" "+y+"and "+point.x+" "+point.y );
                return point;  
            }
        }
        return null;
    }
            
    
    public void removeWalls(Point p1, Point p2){
          Integer p1_x = p1.x;
          Integer p2_x = p2.x;
          Integer p1_y = p1.y;
          Integer p2_y = p2.y;
          if(p2_x > p1_x){
              int index1 = BLOCK_SIZE*5*4*Math.floorDiv(p1_y, BLOCK_SIZE)+Math.floorDiv(p2_x, BLOCK_SIZE)*4+3;
              int index2 = BLOCK_SIZE*5*4*Math.floorDiv(p1_y, BLOCK_SIZE)+Math.floorDiv(p2_x, BLOCK_SIZE)*4-3;
              sides[index1] = false;
              sides[index2] = false;
            }
          if(p2_x < p1_x){
              int index1 = BLOCK_SIZE*5*4*Math.floorDiv(p1_y, BLOCK_SIZE)+Math.floorDiv(p1_x, BLOCK_SIZE)*4+3;
              int index2 = BLOCK_SIZE*5*4*Math.floorDiv(p1_y, BLOCK_SIZE)+Math.floorDiv(p1_x, BLOCK_SIZE)*4-3;
              sides[index1] = false;
              sides[index2] = false;
            }
          if(p2_y > p1_y){
              int index1 = BLOCK_SIZE*5*4*Math.floorDiv(p1_y, BLOCK_SIZE)+Math.floorDiv(p1_x, BLOCK_SIZE)*4+2;
              int index2 = index1 + BLOCK_SIZE*5*4-2;
              sides[index1] = false;
              sides[index2] = false;
            }
          if(p2_y < p1_y){
              int index1 = BLOCK_SIZE*5*4*Math.floorDiv(p1_y, BLOCK_SIZE)+Math.floorDiv(p1_x, BLOCK_SIZE)*4;
              int index2 = index1 - (BLOCK_SIZE*5*4-2);
              sides[index1] = false;
              sides[index2] = false;
            }
        }
}
