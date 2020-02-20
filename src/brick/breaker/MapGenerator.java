package brick.breaker;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

public class MapGenerator {
    public int map[][];
    public int brickWidth;
    public int brickHeight;
    public Color c;


    public MapGenerator(int row, int col) {

        map = new int[row][col];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 1;

            }
        }

        brickWidth = 540 / col;
        brickHeight = 150/ row;

    }
    //function to create a brighter color of that color
    public static Color brighten(Color color, double fraction) {

        int red = (int) Math.round(Math.min(255, color.getRed() + 255 * fraction));
        int green = (int) Math.round(Math.min(255, color.getGreen() + 255 * fraction));
        int blue = (int) Math.round(Math.min(255, color.getBlue() + 255 * fraction));

        int alpha = color.getAlpha();

        return new Color(red, green, blue, alpha);

    }

    //draw 2D map
    public void draw(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    c=new Color((int)(Math.random() * 0x1000000));
                    g.setColor(brighten(c,0.25));
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth,brickHeight);

                    //give outline
                    g.setColor(Color.BLACK);
                    g.setStroke(new BasicStroke(3));
                    g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }

    public void setBrickValue(int value, int row, int col) {
        map[row][col] = value;
    }


}
