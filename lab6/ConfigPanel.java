/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author School
 */
public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel label; // we’re drawing regular polygons
    JSpinner sidesField; // number of sides
    JComboBox colorCombo; // the color of the shape
    JComboBox shapeCombo; //the shape
    JRadioButton retained;
    JRadioButton direct;
    JRadioButton delete;
    JButton undo;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        //create the label and the spinner
        JLabel sidesLabel = new JLabel("Number of sides:");
        sidesField = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        sidesField.setValue(6); //default number of sides
        String color[] = {"Black", "Random"};
        colorCombo = new JComboBox(color);
        String shape[]={"Regular Polygon", "Square", "Rectangle", "Triangle", "Circle"};
        shapeCombo = new JComboBox(shape);
        retained=new JRadioButton("Retained Mode");
        direct = new JRadioButton("Direct Mode");
        undo = new JButton("Undo");
        delete=new JRadioButton("Delete");
        add(sidesLabel);
        add(sidesField);
        add(colorCombo);
        add(shapeCombo);
        add(retained);
        add(direct);
        add(delete);
        add(undo);
        direct.setSelected(true);
        direct.addActionListener(this::direct);
        retained.addActionListener(this::retained);
        undo.addActionListener(this::undo);
        delete.addActionListener(this::delete);
    }
    
    private void direct(ActionEvent e) {
        retained.setSelected(false);
        delete.setSelected(false);
    }
    
    private void retained(ActionEvent e) {
        direct.setSelected(false);
        delete.setSelected(false);
    }
    
    private void delete(ActionEvent e) {
        direct.setSelected(false);
        retained.setSelected(false);
    }
    
    private void undo(ActionEvent e){
        Shape s = frame.canvas.shapesDirect.get(frame.canvas.shapesDirect.size()-1);
        frame.canvas.graphics.setColor(Color.white);
        frame.canvas.graphics.fill(s);
        frame.canvas.shapesDirect.remove(frame.canvas.shapesDirect.size()-1);
    }
    
}
