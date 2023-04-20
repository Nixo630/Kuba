package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Window extends JFrame {
    JLabel content;


    PanneauDemarrage pd;

    public Window(){
        this.setTitle("Launcher KUBA");
        pd = new PanneauDemarrage(this);
        this.setSize(1020,600);
        this.setVisible(true);
        this.setResizable(false);
        Image iconImage = null;
        try {
            this.setIconImage(ImageIO.read(new File("src/ressource/icone.png")));

            iconImage = ImageIO.read(new File("src/ressource/background4.jpg"));
        } catch (IOException e) {
           System.out.println("Image non trouve dans Window");
        }
        iconImage = iconImage.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_FAST);

        ImageIcon imic = new ImageIcon(iconImage);

        this.content=new JLabel(imic);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(content);
        this.setLayout(new BorderLayout());
        this.add(pd);
        pd.anime();
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					new Window();
				}
			}
		);
    }
}