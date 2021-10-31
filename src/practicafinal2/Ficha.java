//Diego Bermejo y Cristian Cardona
//https://youtu.be/kQ8dhso9Mjo
package practicafinal2;


import java.awt.Graphics;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;

public class Ficha {

    private static final String fichaimage = "bolapng.png";
    private BufferedImage img;

    public Ficha() {
        try {
            img = ImageIO.read(new File(fichaimage));
            
        } catch (IOException e) {
        }
    }

    void paintComponent(Graphics g, float x, float y) {
        g.drawImage(img,(int) x , (int) y , 47,47,null);
    }

    public static String getFichaimage() {
        return fichaimage;
    }
    
    
}
