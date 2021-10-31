//Diego Bermejo y Cristian Cardona
//https://youtu.be/kQ8dhso9Mjo
/*
 * Classe casilla
 *
 */

package practicafinal2;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

class Casilla {
    //atributos de una casilla
    //5 rectangulos (4 son las barreras)
    private Rectangle2D.Float rec;
    private Rectangle2D.Float recnorte;
    private Rectangle2D.Float recsur;
    private Rectangle2D.Float receste;
    private Rectangle2D.Float recoeste;
    //color
    private Color col;
    //booleanos para saber si el objeto esta ocupado por 
    //una ficha o una salida
    private Boolean ocupadaficha;
    private Boolean ocupadasalida;
    //Objeto ficha y salida
    private Ficha pieza;
    private Salida exit;
    //booleanos que nos indican si existe 
    //una barrera segun lo que diga el fichero
    private boolean norte;
    private boolean este;
    private boolean sur;
    private boolean oeste;
    //color de las barreras que siempre es el mismo
    private final Color colbarrera=Color.BLACK;
    //constructor con parametros y el string de length 4 del fichero
    public Casilla(Rectangle2D.Float r,Rectangle2D.Float rnorth,
            Rectangle2D.Float rsur,Rectangle2D.Float reste,Rectangle2D.Float roeste ,
            Color amarillo, boolean f,Boolean b,String s) {
        this.rec = r;
        this.recnorte = rnorth;
        this.recsur = rsur;
        this.receste = reste;
        this.recoeste = roeste;
        this.col = amarillo;
        this.ocupadaficha = f;
        this.ocupadasalida = b;
        this.pieza = null;
        this.norte = (s.charAt(0)=='1');
        this.este = (s.charAt(1)=='1');
        this.sur = (s.charAt(2)=='1');
        this.oeste =( s.charAt(3)=='1');
    }
    //paint Component que comprueba los booleanos ocupado y direcciones de barreras
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.col);
        g2d.fill(this.rec);
        if (this.ocupadaficha) {
            this.pieza.paintComponent(g, this.rec.x, this.rec.y);
        }
        if (this.ocupadasalida){
            this.exit.paintComponent(g,this.rec.x, this.rec.y);
        }
        if(norte){
            Graphics2D g2dnorte = (Graphics2D) g;
            g2dnorte.setColor(this.colbarrera);
            g2dnorte.fill(this.recnorte);
        }
        if(sur){
            Graphics2D g2dnorte = (Graphics2D) g;
            g2dnorte.setColor(this.colbarrera);
            g2dnorte.fill(this.recsur);
        }
        if(este){
            Graphics2D g2dnorte = (Graphics2D) g;
            g2dnorte.setColor(this.colbarrera);
            g2dnorte.fill(this.receste);
        }
        if(oeste){
            Graphics2D g2dnorte = (Graphics2D) g;
            g2dnorte.setColor(this.colbarrera);
            g2dnorte.fill(this.recoeste);
        }
    }
    //setters y getters
    void setFicha(Ficha s) {
        this.ocupadaficha = true;
        this.pieza = s;
    }
    void setExit(Salida s) {
        this.ocupadasalida = true;
        this.exit = s;
    }

    public Rectangle2D.Float getRec() {
        return rec;
    }

    boolean isOcupadaFicha() {
        return ocupadaficha;
    }

    void setOcupadaFicha(boolean b) {
        ocupadaficha = b;
    }
    boolean isOcupadaSalida() {
        return ocupadasalida;
    }

    void setOcupadaSalida(boolean b) {
        ocupadasalida = b;
    }

    public boolean isNorte() {
        return norte;
    }

    public boolean isEste() {
        return este;
    }

    public boolean isSur() {
        return sur;
    }

    public boolean isOeste() {
        return oeste;
    }
    
}

