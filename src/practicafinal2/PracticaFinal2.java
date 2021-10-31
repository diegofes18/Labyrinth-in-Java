//Diego Bermejo y Cristian Cardona
//https://youtu.be/kQ8dhso9Mjo
//clase con metodo main

package practicafinal2;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class PracticaFinal2 extends JFrame implements KeyListener,ActionListener{
    //atributos laberinto y menu
    static Laberinto tauler;
    private JMenuBar mb;
    private JMenu menu1,submenu;
    private JMenuItem mi1,mi2,mi3,mblanco,mverde,mrosa,mrojo,
                      mazul,mnaranja,mamarillo,mlila;
    public Color col;

    public PracticaFinal2() {
    Random rnd =new Random();
        try {
            //inicializar laberinto
            tauler = new Laberinto();
            //añadir escuchador po teclado
            addKeyListener(this);
            this.getContentPane().add(tauler);
            this.setSize(tauler.getMAXIMX(),tauler.getMAXIMY());
            this.pack();
            this.setResizable(false);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //coordenadas de ficha aleatorias
            tauler.setXFICHA((int)(rnd.nextDouble()*tauler.getDIMENSIONX())-1);
            tauler.setYFICHA((int)(rnd.nextDouble()*tauler.getDIMENSIONY())-1);
            this.setTitle("Laberinto");
            //ponemos ficha y sallida en sus coordenaddas en el tablero
            tauler.putAll();
            setLayout(null);
            //inicializamos menu y sus componentes
            
            mb=new JMenuBar();
            setJMenuBar(mb);
            menu1=new JMenu("Opciones");
            mb.add(menu1);
            mi1=new JMenuItem("Cambiar laberinto");
            mi1.addActionListener(this);
            menu1.add(mi1);
            mi2=new JMenuItem("Reiniciar");
            mi2.addActionListener(this);
            menu1.add(mi2);
            mi3=new JMenuItem("Acabar partida");
            mi3.addActionListener(this);
            menu1.add(mi3);
            submenu=new JMenu("Cambiar color");
            mblanco=new JMenuItem("BLANCO");
            mblanco.addActionListener(this);
            submenu.add(mblanco);
            mverde=new JMenuItem("VERDE");
            mverde.addActionListener(this);
            submenu.add(mverde);
            mrosa=new JMenuItem("ROSA");
            mrosa.addActionListener(this);
            submenu.add(mrosa);
            mrojo=new JMenuItem("ROJO");
            mrojo.addActionListener(this);
            submenu.add(mrojo);
            mazul=new JMenuItem("AZUL");
            mazul.addActionListener(this);
            submenu.add(mazul);
            mnaranja=new JMenuItem("NARANJA");
            mnaranja.addActionListener(this);
            submenu.add(mnaranja);
            mamarillo=new JMenuItem("AMARILLO");
            mamarillo.addActionListener(this);
            submenu.add(mamarillo);
            mlila=new JMenuItem("LILA");
            mlila.addActionListener(this);
            submenu.add(mlila);
            menu1.add(submenu);
            
        } catch (IOException ex) {
            Logger.getLogger(PracticaFinal2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
        //metodo main
        PracticaFinal2 juego=new PracticaFinal2();
        juego.setVisible(true);
    }
    //respuestas a eventos de teclado
    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {
    //antes de reallizar un movimiento se comprueba la tecla pulsada y si
    //no hay barrera
        if((ke.getKeyCode()==KeyEvent.VK_W)&&(tauler.Nortedisponible())){
          tauler.movimientoNorte();
          tauler.repaint();
        }
        if((ke.getKeyCode()==KeyEvent.VK_D)&&(tauler.Estedisponible())){
          tauler.movimientoEste();
          tauler.repaint();
        }
        if((ke.getKeyCode()==KeyEvent.VK_A)&&(tauler.Oestedisponible())){
          tauler.movimientoOeste();
          tauler.repaint();
        }
        if((ke.getKeyCode()==KeyEvent.VK_S)&&(tauler.Surdisponible())){
          tauler.movimientoSur();
          tauler.repaint();
        }
        //pintamos despues de movimiento
        //Jdialog de victoria si las coordenadas de salida y ficha coinciden
        if((tauler.getYFICHA()==tauler.getYSALIDA())&&(tauler.getXFICHA()==tauler.getXSALIDA())){
          Toolkit.getDefaultToolkit().beep();
          JOptionPane.showMessageDialog(null, "VICTORIA",
          "Has ganado!", JOptionPane.INFORMATION_MESSAGE);
          
        }
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        //Jfilechooser si se clica en el primer item del menu
        if (ae.getSource()==mi1) {
            Random rnd =new Random();
             mi1.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent evt) {
                 JFileChooser fileChooser = new JFileChooser();
                 
                 fileChooser.setDialogTitle("Especificar archivo a abrir");
                 
                 String ruta = fileChooser.getSelectedFile().getAbsolutePath();
                 try {
                     tauler.setFile(ruta);
                     tauler.setXFICHA((int)(rnd.nextDouble()*tauler.getDIMENSIONX())-1);
                     tauler.setYFICHA((int)(rnd.nextDouble()*tauler.getDIMENSIONY())-1);
                     tauler.putAll();
                     tauler.repaint();
                 } catch (IOException ex) {
                     Logger.getLogger(PracticaFinal2.class.getName()).log(Level.SEVERE, null, ex);
                 }
            }
        });
        }
        //se vuelve a colocar la ficha en un lugar aleatorio 
        if (ae.getSource()==mi2) {
           Random rnd2=new Random();
           tauler.vaciaFicha();
           tauler.setXFICHA((int)(rnd2.nextDouble()*tauler.getDIMENSIONX())-1);
           tauler.setYFICHA((int)(rnd2.nextDouble()*tauler.getDIMENSIONY())-1);
           tauler.putFicha();
           tauler.repaint();
        }
        //paramos ejecucion
        if (ae.getSource()==mi3) {
            System.exit(0);
        } 
        if(ae.getSource()==mblanco){
            try {
                col=Color.WHITE;
                tauler.changecolor(col,tauler.getFile());
                tauler.putAll();
                tauler.repaint();
                
            } catch (IOException ex) {
                Logger.getLogger(PracticaFinal2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(ae.getSource()==mverde){
            try {
                col=Color.GREEN;
                tauler.changecolor(col,tauler.getFile());
                tauler.putAll();
                tauler.repaint();
                
            } catch (IOException ex) {
                Logger.getLogger(PracticaFinal2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(ae.getSource()==mrosa){
            try {
                col=Color.PINK;
                tauler.changecolor(col,tauler.getFile());
                tauler.putAll();
                tauler.repaint();
                
            } catch (IOException ex) {
                Logger.getLogger(PracticaFinal2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(ae.getSource()==mrojo){
            try {
                col=Color.RED;
                tauler.changecolor(col,tauler.getFile());
                tauler.putAll();
                tauler.repaint();
                
            } catch (IOException ex) {
                Logger.getLogger(PracticaFinal2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(ae.getSource()==mazul){
            try {
                col=Color.BLUE;
                tauler.changecolor(col,tauler.getFile());
                tauler.putAll();
                tauler.repaint();
                
            } catch (IOException ex) {
                Logger.getLogger(PracticaFinal2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(ae.getSource()==mnaranja){
            try {
                col=Color.ORANGE;
                tauler.changecolor(col,tauler.getFile());
                tauler.putAll();
                tauler.repaint();
                
            } catch (IOException ex) {
                Logger.getLogger(PracticaFinal2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(ae.getSource()==mamarillo){
            try {
                col=Color.YELLOW;
                tauler.changecolor(col,tauler.getFile());
                tauler.putAll();
                tauler.repaint();
                
            } catch (IOException ex) {
                Logger.getLogger(PracticaFinal2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(ae.getSource()==mlila){
            try {
                col=Color.MAGENTA;
                tauler.changecolor(col,tauler.getFile());
                tauler.putAll();
                tauler.repaint();
                
            } catch (IOException ex) {
                Logger.getLogger(PracticaFinal2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
