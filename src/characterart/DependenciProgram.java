package characterart;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collections;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DependenciProgram {

    // A KARAKTEREK SZINMÉLYSÉGÉNEK SORRENDBE ÁLLÍTÁSÁHOZ KELL, PROGRAMNAK NEM FUTÁSI FELTÉTELE
    
    private String characters = "qwertzuiopőúasdfghjkléáűíyxcvbnm,.-0123456789öüóQWERTZUIOPŐÚASDFGHJKLÉÁŰÍYXCVBNM?:_§'\"+!%/=()\\|Ä€÷×äđĐ[]íłŁ$ß¤<>#&@{}<;>*";
    
    private JLabel label;
    
    char[] sorrend = new char[116];
    
    Vector<Character> chars = new Vector();
    
    public DependenciProgram() throws InterruptedException{
        ablak();
        Thread.sleep(3000);
        run();
        System.out.println(sorrend);
        for(char c : sorrend){
            chars.add(c);
        }
        // kiirni fajlba
        System.exit(0);
    }
    
    private void run() throws InterruptedException{
        for (int i = 0; i < characters.length(); i++) {
            label.setText(characters.charAt(i)+"");
            System.out.println("A karakter: "+characters.charAt(i));
            Thread.sleep(200);
            System.out.println(pixels(screenshot()));
            sorrend[pixels(screenshot())] = characters.charAt(i);
        }
    }
    
    private int pixels(BufferedImage image){
        int count = 0;
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if(image.getRGB(j, i) != Color.white.getRGB()){
                    count++;
                }
                
            }
        }
        
        return count;
    }
    
    private void ablak(){
        JFrame frame = new JFrame();
        frame.setSize(50,50);        
        frame.setUndecorated(true);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        label = new JLabel("SZIA");   
        label.setFont(new Font("monospaced", Font.PLAIN, 12));
        panel.add(label);
        frame.add(panel);
        frame.setVisible(true);
    }
    
    private BufferedImage screenshot(){
        BufferedImage image = null;
        try{
            image = new Robot().createScreenCapture(new Rectangle(new Dimension(50,50)));
            //File outputfile = new File("saved.png");
            //ImageIO.write(image, "png", outputfile);            
        }catch(Exception e){
            e.printStackTrace();
        }
        return image;
    }
    
}
