//Diego Bermejo y Cristian Cardona
//https://youtu.be/kQ8dhso9Mjo
package practicafinal2;


import java.awt.Graphics;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;

public class Salida {

    public static final String exitimage = "salidapng.png";
    private BufferedImage img;

    public Salida() {
        try {
            img = ImageIO.read(new File(exitimage));
        } catch (IOException e) {
        }
    }

    void paintComponent(Graphics g, float x, float y) {
        g.drawImage(img,(int) x , (int) y ,47,47, null);
    }
    public static String getSalidaimage() {
        return exitimage;
    }
    
    
}
