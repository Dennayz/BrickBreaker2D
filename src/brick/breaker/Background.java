package brick.breaker;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Background extends JLabel {
    JLabel imageLabel = new JLabel();
    public Background() {

        try {
            setLayout(new BorderLayout());
            setSize(new Dimension(700, 600));
            // add the image label
            ImageIcon ii = new ImageIcon(this.getClass().getResource(
                    "guy.gif"));
            imageLabel.setIcon(ii);
            add(imageLabel, java.awt.BorderLayout.CENTER);
            // show it
            this.setVisible(true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }


}

