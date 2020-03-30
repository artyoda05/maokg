package sample;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class Main extends JPanel implements ActionListener {

    private static int maxWidth;
    private static int maxHeight;

    Timer timer;

    private double angle = 0;
    private double scale = 1;
    private double delta = 0.01;

    public Main() {
        timer = new Timer(10, this);
        timer.start();
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("lab2");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(1300, 1000);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.add(new Main());
        jFrame.setVisible(true);
        Dimension size = jFrame.getSize();
        Insets insets = jFrame.getInsets();
        maxWidth = size.width - insets.left - insets.right - 1;
        maxHeight = size.height - insets.top - insets.bottom - 1;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if ( scale < 0.35 ) {
            delta = -delta;
        } else if (scale > 0.99) {
            delta = -delta;
        }

        scale += delta;
        angle += 0.007;
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D)g;

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHints(rh);

        graphics2D.setBackground(Color.BLUE);
        graphics2D.clearRect(0,0, maxWidth, maxHeight);

        drawBorder(graphics2D);

        graphics2D.translate(maxWidth/2, maxHeight/2);

        graphics2D.rotate(-angle, 0,0);

        graphics2D.scale(scale, scale);

        drawRect(graphics2D,
                new int[] {-25, -25, 25, 25},
                new int[] {100, 0, 0, 100},
                new Color(128, 64, 0));

        drawPine(graphics2D);

        drawRect(graphics2D,
                new int[] {0, 0, 15, 15},
                new int[] {-175, -160, -160, -175},
                new Color(255,255,0));
        drawRect(graphics2D,
                new int[] {-25, -25, -10, -10},
                new int[] {-125, -110, -110, -125},
                new Color(255,255,0));
        drawRect(graphics2D,
                new int[] {7, 7, 22, 22},
                new int[] {-75, -60, -60, -75},
                new Color(255,255,0));
    }

    private void drawRect(Graphics2D graphics2D, int[] xRect, int[] yRect, Color color) {
        graphics2D.setColor(color);
        Polygon rect = new Polygon(xRect, yRect,4);
        graphics2D.drawPolygon(rect);
        graphics2D.fillPolygon(rect);
    }

    private void drawPine(Graphics2D graphics2D) {
        GradientPaint gp = new GradientPaint(10, 10,
                new Color(0,128,0), -10, -10, new Color(255,255,0), true);
        graphics2D.setPaint(gp);
        Polygon pine = new Polygon();
        pine.addPoint( -80, 0);
        pine.addPoint( -45, -90);
        pine.addPoint( -70, -90);
        pine.addPoint( -35, -180);
        pine.addPoint( -60, -180);
        pine.addPoint( 0, -270);
        pine.addPoint( 60, -180);
        pine.addPoint( 25, -185);
        pine.addPoint( 65, -90);
        pine.addPoint( 40, -90);
        pine.addPoint( 80, 0);
        graphics2D.drawPolygon(pine);
        graphics2D.fillPolygon(pine);
    }

    private void drawBorder(Graphics2D graphics2D) {
        BasicStroke basicStroke = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        graphics2D.setStroke(basicStroke);
        GradientPaint gp = new GradientPaint(5, 20,
                Color.GREEN, 20, 2, Color.BLUE, true);
        graphics2D.setPaint(gp);
        graphics2D.drawRect(10, 10, maxWidth - 20, maxHeight - 20);
    }
}
