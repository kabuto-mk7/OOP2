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
public class NegativeOp implements OperationInterface{
    private final  String name;
    private final  ImageProcessor frame;
    private final  NegativeUI negativeUI;

    
    public NegativeOp(String name,ImageProcessor frame,NegativeUI negativeUI){
        this.name = name;
        this.frame = frame;
        this.negativeUI = negativeUI;
    }
    
    @Override
    public BufferedImage doOperation(BufferedImage inputImage) {
        final OperationDialog dialog = new OperationDialog( frame, negativeUI);
        dialog.setVisible( true);
        if (!dialog.wasCancelled()) {
            for (int x = 0; x < inputImage.getWidth(); x++) {
                for (int y = 0; y < inputImage.getHeight(); y++) {
                    final int inputRGB = OperationUtilities.getRGB(x, y, inputImage);
                    final int outputRGB = OperationUtilities.negative(inputRGB);
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
