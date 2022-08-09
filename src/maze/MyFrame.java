
package maze;

import javax.swing.JFrame;

/**
 *
 * @author Sudip
 */
public class MyFrame extends JFrame {
        MyPanel myPanel;
        public MyFrame() throws InterruptedException{
            myPanel= new MyPanel();
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.add(myPanel);
            this.pack();
            this.setVisible(true);
        }
    }
        
