package pakiet;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        });
    }
    //ustawienia GUI
    private static void createAndShowGUI() {
        JFrame f = new JFrame("Obrazek 2D");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new MyPanel());
        f.pack();
        f.setVisible(true);
    }
}

class MyPanel extends JPanel {
    private BufferedImage roof;
    private TexturePaint rooftp;
    private BufferedImage house;
    private TexturePaint housetp;
    private BufferedImage door;
    private TexturePaint doortp;
    private BufferedImage car;
    private BufferedImage sun;
    private TexturePaint suntp;
    private BufferedImage cloud;
    private TexturePaint cloudtp;
    private BufferedImage trash;
    private TexturePaint trashtp;
    private BufferedImage tree;
    private TexturePaint treetp;
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1920,1080);
    }
    
    //konstruktor ustawiający rozdzielczość i wywołujący metodę, która wczytuję obrazy.
    public MyPanel() {
        loadImages();
        setPreferredSize(new Dimension(1920, 1080));
    }

    //metoda do wczytywania obrazów
    private void loadImages() {

        try {
            roof = ImageIO.read(new File("roof.jpg"));
            house = ImageIO.read(new File("house.jpg"));
            car = ImageIO.read(new File("car.png"));
            door = ImageIO.read(new File("door.png"));
            sun = ImageIO.read(new File("sun.png"));
            cloud = ImageIO.read(new File("cloud.png"));
            trash = ImageIO.read(new File("trash.png"));
            tree = ImageIO.read(new File("tree.png"));
        } catch (IOException ex) {

            Logger.getLogger(MyPanel.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    //metoda do rysowania
    public void paintComponent(Graphics g) {
        super.paintComponent(g);         
        Graphics2D g2d = (Graphics2D) g;
        
        GradientPaint sky = new GradientPaint(0, 0, new Color(102,102,204), 1080, 1920, new Color(153,255,255)); //gradient nieba
        g2d.setPaint(sky);
        g2d.fill(new Rectangle(0,0,1920,760));
        
        GradientPaint grass = new GradientPaint(0, 720, new Color(0,102,0), 1920, 1080, new Color(0,153,0)); //gradient trawy
        g2d.setPaint(grass);
        g2d.fillRect(0, 720, 1920, 360); // trawa     
        
        housetp = new TexturePaint(house, new Rectangle(0, 0, 360, 360));
        g2d.setPaint(housetp);
        g2d.fillRect(500, 360, 800, 450); //domek      
        
        rooftp = new TexturePaint(roof, new Rectangle(0, 0, 400, 360));
        g2d.setPaint(rooftp);
        g2d.fillPolygon(new int[] {500, 870, 1300}, new int[] {360, 150, 360}, 3); //dach      
        
        g2d.setColor(Color.black); 
        g2d.drawLine(500, 360, 1300, 360); //linia przy dachu     
        
        doortp = new TexturePaint(door, new Rectangle(0, 0, 200, 400));//ustawianie tekstury drzwi
        g2d.setPaint(doortp);
        g2d.fillRect(800, 650, 100, 160); //drzwi     
        g2d.setColor(Color.DARK_GRAY); 
        g2d.fillRect(880, 730, 10, 10); //klamka
        g2d.setColor(Color.CYAN); 
        g2d.fillRect(600, 420, 100, 100); //1 okno
        g2d.setColor(Color.black); 
        g2d.drawLine(650, 420, 650, 520); //
        g2d.drawLine(600, 470, 700, 470); //ramki w oknach
        g2d.setColor(Color.CYAN); 
        g2d.fillRect(1100, 420, 100, 100); //2 okno
        g2d.setColor(Color.black); 
        g2d.drawLine(1150, 420, 1150, 520); 
        g2d.drawLine(1100, 470, 1200, 470); // ramki w oknach
        
        g.drawImage(car, 1400, 650, 500, 400, this); //samochod
        
        suntp = new TexturePaint(sun, new Rectangle(1250, 100, 200, 200)); 
        g2d.setPaint(suntp);
        g2d.fillRect(1250, 100, 200, 200);  // slonce
        
        cloudtp = new TexturePaint(cloud, new Rectangle(50, 120, 500, 200));
        g2d.setPaint(cloudtp);
        g2d.fillRect(50, 120, 500, 200); //chmury
        
        trashtp = new TexturePaint(trash, new Rectangle(1320, 700, 80, 100));
        g2d.setPaint(trashtp);
        g2d.fillRect(1320, 700, 80, 100); //smietnik
        
        treetp = new TexturePaint(tree, new Rectangle(100, 360, 400, 500));
        g2d.setPaint(treetp);
        g2d.fillRect(100, 360, 400, 600); //drzewo

        g2d.dispose();
    }  

}
