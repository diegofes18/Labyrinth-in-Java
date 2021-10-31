//Diego Bermejo y Cristian Cardona
//https://youtu.be/kQ8dhso9Mjo
/*
 Clase laberinto
 */
package practicafinal2;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Laberinto extends JPanel {
    //atributos
    //dimension,posicion ficha y salida
    public  int DIMENSIONY ;
    public  int DIMENSIONX ;
    private  int YSALIDA;
    private  int XSALIDA;
    private  int XFICHA;
    private  int YFICHA;
    //tamaño casillas
    public static final int LADO=50;
    //tamaño total
    public  int MAXIMY;
    public  int MAXIMX;
    //color constante de cada casilla
    private Color amarillo;
    //aray bidimensional de objetos casilla
    private  Casilla t[][];
    //stream para leer fichero
    private FileInputStream f;
    private BufferedReader b;
    private String file;
    
    public Laberinto() throws IOException{
        try {
            file="maze1.txt";
            amarillo=Color.YELLOW;
            //inicializacion stream con string del fichero
            f=new FileInputStream("maze1.txt");
            b=new BufferedReader(new InputStreamReader(f));
            //lectura dos primeras lineas y inicializaciones
            DIMENSIONY=Integer.parseInt(b.readLine());
            DIMENSIONX=Integer.parseInt(b.readLine());
            MAXIMY=DIMENSIONY*LADO;
            MAXIMX=DIMENSIONX*LADO;
            t=new Casilla[DIMENSIONY][DIMENSIONX];
            //doble bucle con lectura linea 
            int y = 0;
            for (int i = 0; i < DIMENSIONY; i++) {
                int x = 0;
                String infoline=b.readLine();
                for (int j = 0; j < DIMENSIONX; j++) {
                    Rectangle2D.Float r =
                            new Rectangle2D.Float(x, y, LADO, LADO);
                    Rectangle2D.Float rarriba =
                            new Rectangle2D.Float(x, y, LADO, 5);
                    Rectangle2D.Float rderecha =
                            new Rectangle2D.Float(x+45, y,5 , LADO);
                    Rectangle2D.Float rizquierda =
                            new Rectangle2D.Float(x, y,5 , LADO);
                    Rectangle2D.Float rabajo =
                            new Rectangle2D.Float(x, y+45, LADO, 5);
                    //particion del string entero 
                    String codcasilla = infoline.substring((j*4), (j*4)+4);
                    //inicializacion casilla
                    t[i][j]=new Casilla(r,rarriba,rabajo,rderecha,rizquierda,amarillo,false,false,codcasilla);
                    x += LADO;
                }
                y += LADO;
            }
            //inicializacion ultimos atributos coordenadas salida
            YSALIDA=Integer.parseInt(b.readLine());
            XSALIDA=10-1; 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Laberinto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //pintar cada casilla
    @Override
    public void paintComponent(Graphics g) {
        for (int i = 0; i < DIMENSIONY; i++) {
            for (int j = 0; j < DIMENSIONX; j++) {                
                t[i][j].paintComponent(g);
            }
        }
    }
    
     @Override
    public Dimension getPreferredSize() {
        return new Dimension(MAXIMX, MAXIMY);
    }
    //getters y setters
    public  int getYSALIDA() {
        return YSALIDA;
    }

    public  int getXSALIDA() {
        return XSALIDA;
    }

    public  int getXFICHA() {
        return XFICHA;
    }

    public  int getYFICHA() {
        return YFICHA;
    }
    //ponemos ficha o salida 
    public void putExit(){
        t[YSALIDA][XSALIDA].setExit(new Salida());
    }
    public void putFicha(){
        t[YFICHA][XFICHA].setFicha(new Ficha());
    }
    //metodo que pone los objetos en sitio asignado
    public void putAll(){
        t[YSALIDA][XSALIDA].setExit(new Salida());
        t[YFICHA][XFICHA].setFicha(new Ficha());
    }
    //getters y setters
    public void setXFICHA(int XFICHA) {
        this.XFICHA = XFICHA;
    }

    public void setYFICHA(int YFICHA) {
        this.YFICHA = YFICHA;
    }

    public void setYSALIDA(int YSALIDA) {
        this.YSALIDA = YSALIDA;
    }

    public void setXSALIDA(int XSALIDA) {
        this.XSALIDA = XSALIDA;
    }
    boolean dinsCasella(int i, int j, int x, int y) {
        return t[i][j].getRec().contains(x, y);
    }
    Rectangle getRectangle(int i, int j) {
        return t[i][j].getRec().getBounds();
    }
    //vaciamos la casilla de la ficha
    public void vaciaFicha(){
        t[YFICHA][XFICHA].setOcupadaFicha(false);
    }
    //getters y setters
    public  int getDIMENSIONY() {
        return DIMENSIONY;
    }

    public  int getDIMENSIONX() {
        return DIMENSIONX;
    }
    public Casilla getCasilla(int i,int j){
        return t[i][j];
    }
 
    public  int getMAXIMY() {
        return MAXIMY;
    }

    public  int getMAXIMX() {
        return MAXIMX;
    }
    //booleanos para saber disponibilidad para movimiento ficha
    public boolean Nortedisponible(){
        return !t[YFICHA][XFICHA].isNorte();
    }
    public boolean Surdisponible(){
        return !t[YFICHA][XFICHA].isSur();
    }
    public boolean Estedisponible(){
        return !t[YFICHA][XFICHA].isEste();
    }
    public boolean Oestedisponible(){
        return !t[YFICHA][XFICHA].isOeste();
    }
    //metodos para cambiar ficha de sitio
    public void movimientoNorte(){
        t[YFICHA][XFICHA].setOcupadaFicha(false);
        YFICHA--;
        t[YFICHA][XFICHA].setFicha(new Ficha());
    } 
    public void movimientoSur(){
        t[YFICHA][XFICHA].setOcupadaFicha(false);
        YFICHA++;
        t[YFICHA][XFICHA].setFicha(new Ficha());
    } 
    public void movimientoEste(){
        t[YFICHA][XFICHA].setOcupadaFicha(false);
        XFICHA++;
        t[YFICHA][XFICHA].setFicha(new Ficha());
    } 
    public void movimientoOeste(){
        t[YFICHA][XFICHA].setOcupadaFicha(false);
        XFICHA--;
        t[YFICHA][XFICHA].setFicha(new Ficha());
    }
    public void setFile(String file1) throws IOException{
        try {
            file=file1;
            //inicializacion stream con string del fichero
            f=new FileInputStream(file1);
            b=new BufferedReader(new InputStreamReader(f));
            //lectura dos primeras lineas y inicializaciones
            DIMENSIONY=Integer.parseInt(b.readLine());
            DIMENSIONX=Integer.parseInt(b.readLine());
            MAXIMY=DIMENSIONY*LADO;
            MAXIMX=DIMENSIONX*LADO;
            t=new Casilla[DIMENSIONY][DIMENSIONX];
            //doble bucle con lectura linea 
            int y = 0;
            for (int i = 0; i < DIMENSIONY; i++) {
                int x = 0;
                String infoline=b.readLine();
                for (int j = 0; j < DIMENSIONX; j++) {
                    Rectangle2D.Float r =
                            new Rectangle2D.Float(x, y, LADO, LADO);
                    Rectangle2D.Float rarriba =
                            new Rectangle2D.Float(x, y, LADO, 5);
                    Rectangle2D.Float rderecha =
                            new Rectangle2D.Float(x+45, y,5 , LADO);
                    Rectangle2D.Float rizquierda =
                            new Rectangle2D.Float(x, y,5 , LADO);
                    Rectangle2D.Float rabajo =
                            new Rectangle2D.Float(x, y+45, LADO, 5);
                    //particion del string entero 
                    String codcasilla = infoline.substring((j*4), (j*4)+4);
                    //inicializacion casilla
                    t[i][j]=new Casilla(r,rarriba,rabajo,rderecha,rizquierda,amarillo,false,false,codcasilla);
                    x += LADO;
                }
                y += LADO;
            }
            //inicializacion ultimos atributos coordenadas salida
            YSALIDA=Integer.parseInt(b.readLine());
            XSALIDA=10-1; 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Laberinto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getFile() {
        return file;
    }
    
    public void changecolor(Color col,String s) throws IOException{
        try {
            amarillo=col;
            f=new FileInputStream(s);
            b=new BufferedReader(new InputStreamReader(f));
            //lectura dos primeras lineas y inicializaciones
            DIMENSIONY=Integer.parseInt(b.readLine());
            DIMENSIONX=Integer.parseInt(b.readLine());
            MAXIMY=DIMENSIONY*LADO;
            MAXIMX=DIMENSIONX*LADO;
            t=new Casilla[DIMENSIONY][DIMENSIONX];
            //doble bucle con lectura linea 
            int y = 0;
            for (int i = 0; i < DIMENSIONY; i++) {
                int x = 0;
                String infoline=b.readLine();
                for (int j = 0; j < DIMENSIONX; j++) {
                    Rectangle2D.Float r =
                            new Rectangle2D.Float(x, y, LADO, LADO);
                    Rectangle2D.Float rarriba =
                            new Rectangle2D.Float(x, y, LADO, 5);
                    Rectangle2D.Float rderecha =
                            new Rectangle2D.Float(x+45, y,5 , LADO);
                    Rectangle2D.Float rizquierda =
                            new Rectangle2D.Float(x, y,5 , LADO);
                    Rectangle2D.Float rabajo =
                            new Rectangle2D.Float(x, y+45, LADO, 5);
                    //particion del string entero 
                    String codcasilla = infoline.substring((j*4), (j*4)+4);
                    //inicializacion casilla
                    t[i][j]=new Casilla(r,rarriba,rabajo,rderecha,rizquierda,amarillo,false,false,codcasilla);
                    x += LADO;
                }
                y += LADO;
            }
            //inicializacion ultimos atributos coordenadas salida
            YSALIDA=Integer.parseInt(b.readLine());
            XSALIDA=10-1; 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Laberinto.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}

