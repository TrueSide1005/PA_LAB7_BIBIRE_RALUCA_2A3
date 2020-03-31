/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.util.Pair;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author School
 */
public class ControlPanel extends JPanel {

    final MainFrame frame;
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton exitBtn = new JButton("Exit");
    JButton resetBtn = new JButton("Reset");
    JButton retBtn = new JButton("Draw");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        //change the default layout manager (just for fun)
        setLayout(new GridLayout(1, 4));
        add(saveBtn);
        add(loadBtn);
        add(exitBtn);
        add(resetBtn);
        add(retBtn);

        //configure listeners for all buttons
        saveBtn.addActionListener(this::save);
        loadBtn.addActionListener(this::load);
        exitBtn.addActionListener(this::exit);
        resetBtn.addActionListener(this::reset);
        retBtn.addActionListener(this::retained);
    }

    private void retained(ActionEvent e) {
//        Color color = new Color(255, 255, 255);
//        frame.canvas.graphics.setColor(color);
//        frame.canvas.graphics.fillRect(0, 0, DrawingPanel.W, DrawingPanel.H);
        for (Pair<Shape, Color> p : frame.canvas.shapes) {
            frame.canvas.graphics.setColor(p.getValue());
            frame.canvas.graphics.fill(p.getKey());
        }
        frame.canvas.shapes.clear();
    }

    private void exit(ActionEvent e) {
        frame.dispose();
    }

    private void reset(ActionEvent e) {
        Color color = new Color(255, 255, 255);
        frame.canvas.graphics.setColor(color);
        frame.canvas.graphics.fillRect(0, 0, DrawingPanel.W, DrawingPanel.H);
    }

    private void save(ActionEvent e) {
        try {
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int returnValue = jfc.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                ImageIO.write(frame.canvas.image, "PNG", new File(selectedFile.getAbsolutePath()));
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }

    private void load(ActionEvent e) {
        try {
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int returnValue = jfc.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                File file = new File(selectedFile.getAbsolutePath());
                BufferedImage image = ImageIO.read(file);
                frame.canvas.graphics.drawImage(image, 0, 0, this);
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
