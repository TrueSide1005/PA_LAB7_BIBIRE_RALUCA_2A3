/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javafx.util.Pair;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author School
 */
public class DrawingPanel extends JPanel {

    final MainFrame frame;
    final static int W = 800, H = 600;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the "tools" needed to draw in the image

    protected List<Pair<Shape, Color>> shapes = new ArrayList<>();
    protected List<Shape> shapesDirect = new ArrayList<>();

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init();
    }

    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE); //fill the image with white
        graphics.fillRect(0, 0, W, H);
    }

    private void init() {
        setPreferredSize(new Dimension(W, H)); //don’t use setSize. Why?
        setBorder(BorderFactory.createEtchedBorder()); //for fun
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (frame.configPanel.delete.isSelected() == false) {
                    drawShape(e.getX(), e.getY());
                    repaint();
                } else {
                    delete(e.getX(), e.getY());
                    repaint();
                }
            } //Can’t use lambdas, JavaFX does a better job in these cases
        });
    }

    private void drawShape(int x, int y) {
        Random rand = new Random();
        int r = rand.nextInt(128) + 128; // 128 ... 255
        int g = rand.nextInt(128) + 128; // 12 ... 255
        int b = rand.nextInt(128) + 128; // 128 ... 255

        int radius = rand.nextInt(100);
        int sides = (Integer) frame.configPanel.sidesField.getValue();

        String selected = frame.configPanel.colorCombo.getSelectedItem().toString();
        Color color = new Color(r, g, b);;
        if ("Black".equals(selected)) {
            color = new Color(0, 0, 0);
        } else if ("Random".equals(selected)) {
            color = new Color(r, g, b);
        }

        graphics.setColor(color);
        //graphics.fill(new RegularPolygon(x, y, radius, sides));

        String select = frame.configPanel.shapeCombo.getSelectedItem().toString();

        boolean ret = frame.configPanel.retained.isSelected();
        if (ret == false) {
            if ("Regular Polygon".equals(select)) {
                Shape poly = new RegularPolygon(x, y, radius, sides);
                graphics.fill(poly);
                shapesDirect.add(poly);
            } else if ("Triangle".equals(select)) {
                Shape triangle = new RegularPolygon(x, y, radius, 3);
                graphics.fill(triangle);
                shapesDirect.add(triangle);
            } else if ("Square".equals(select)) {
                Shape square = new RegularPolygon(x, y, radius, 4);
                graphics.fill(square);
                shapesDirect.add(square);
            } else if ("Rectangle".equals(select)) {
                Shape rectangle = new Rectangle(x, y, radius, (radius / 2));
                graphics.fill(rectangle);
                shapesDirect.add(rectangle);
            } else if ("Circle".equals(select)) {
                Shape circle = new Ellipse2D.Float(x, y, radius, radius);
                graphics.fill(circle);
                shapesDirect.add(circle);
            }
        } else {
            if ("Regular Polygon".equals(select)) {
                Shape poly = new RegularPolygon(x, y, radius, sides);
                shapesDirect.add(poly);
                shapes.add(new Pair(poly, color));
            } else if ("Triangle".equals(select)) {
                Shape triangle = new RegularPolygon(x, y, radius, 3);
                shapesDirect.add(triangle);
                shapes.add(new Pair(triangle, color));
            } else if ("Square".equals(select)) {
                Shape square = new RegularPolygon(x, y, radius, 4);
                shapes.add(new Pair(square, color));
                shapesDirect.add(square);
            } else if ("Rectangle".equals(select)) {
                Shape rectangle = new Rectangle(x, y, radius, (radius / 2));
                shapes.add(new Pair(rectangle, color));
                shapesDirect.add(rectangle);
            } else if ("Circle".equals(select)) {
                Shape circle = new Ellipse2D.Float(x, y, radius, radius);
                shapes.add(new Pair(circle, color));
                shapesDirect.add(circle);
            }
        }
    }

    public void delete(int x, int y) {
        for (Shape s : shapesDirect) {
            if (s.contains(x, y) == true) {
                graphics.setColor(Color.white);
                graphics.fill(s);
            }
        }
    }

    @Override
    public void update(Graphics g) {
    } //Why did I do that?

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}
