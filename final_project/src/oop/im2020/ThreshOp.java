/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.im2020;

import java.awt.image.BufferedImage;

/**
 *
 * @author kbto
 */

public class ThreshOp implements OperationInterface{

    private final String name;
    private final ImageProcessor frame;
    private final ThresholdUI thresholdUI;

    public ThreshOp(String name, ImageProcessor frame, ThresholdUI thresholdUI){ 
        this.name = name;
        this.frame = frame;
        this.thresholdUI = thresholdUI;
    }

    @Override
    public BufferedImage doOperation(BufferedImage inputImage) {
        final OperationDialog dialog = new OperationDialog( frame, thresholdUI);
        dialog.setVisible( true);
        if (!dialog.wasCancelled()) {
            int threshold = thresholdUI.getSlider();
            for (int x = 0; x < inputImage.getWidth(); x++) {
                for (int y = 0; y < inputImage.getHeight(); y++) {
                    final int inputRGB = OperationUtilities.getRGB(x, y, inputImage);
                    final int outputRGB = OperationUtilities.threshold(inputRGB, threshold);
                    OperationUtilities.setRGB(x, y, outputRGB, inputImage);
                }
            }
        }
        return inputImage;
    }

    @Override
    public String getName() {
        return name;
    }

}