package characterart;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class CharacterArt {

    BufferedImage  image;
    
    int width, height, pixels;
    int booster = 2;
    
    String result ="";
    
    public CharacterArt(){
        convert("kep.jpg");
        //convert("car.png");
    }
    
    private String characterMatch(int index){
        String re=" ";
        if(index <= 25)
            re = "M";
        else if(index > 25 && index <= 50)
            re = "$";
        else if(index > 50 && index <= 67)
            re = "o";
        else if(index > 25 && index <= 102)
            re = "|";
        else if(index > 25 && index <= 127)
            re = "*";
        else if(index > 25 && index <= 152)
            re = ":";
        else if(index > 25 && index <= 178)
            re = "=";
        else if(index > 25 && index <= 204)
            re = "\\";
        else if(index > 25 && index <= 230)
            re = ".";
        
        return re;
    }
    
    private void convert(String file){
        try {
            File input = new File(file);
            image = ImageIO.read(input);
            width = image.getWidth();
            height = image.getHeight();
            pixels = width/booster * height/booster;
            int count=0;
         
            for(int i=0; i<height; i+=booster) {
                
                for(int j=0; j<width; j+=booster) {

                    Color c = new Color(image.getRGB(j, i));
                    int red = (int)(c.getRed() * 0.299);
                    int green = (int)(c.getGreen() * 0.587);
                    int blue = (int)(c.getBlue() *0.114);

                    int index = (int)(red + green + blue)/3;

                    result += characterMatch(index);
                    count++;
                    System.out.println(((double)count / (double)pixels)*100 + " %");
                }
                result += "\n";
            }           
        } catch (Exception e) {
            e.printStackTrace();
        }        
        System.out.println(result);        
    }
    
    public static void main(String[] args) {
        new CharacterArt();
    }
    
}
