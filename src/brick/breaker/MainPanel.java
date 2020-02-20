package brick.breaker;

import javax.swing.JLayeredPane;

public class MainPanel extends JLayeredPane {

    public MainPanel() {
        Background background = new Background();
        Gameplay gameplay = new Gameplay();
        add(background,new Integer(0), 0);
        add(gameplay, new Integer(1), 0);

    }
}
