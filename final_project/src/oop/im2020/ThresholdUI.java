/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.im2020;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 *
 * @author kbto
 */
public class ThresholdUI extends JPanel {
    private final JSlider slider = new JSlider(0, 100);

    public ThresholdUI() {
        super(new BorderLayout());
        add(new JLabel("Set value for threshold"));
        add(this.slider);
    }
    public int getSlider() {
        return this.slider.getValue();
    }
}
