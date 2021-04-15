/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.im2020;

/**
 *
 * @author kbto
 */
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BlendOp implements OperationInterface{
    private final String name;
    private final ImageProcessor frame;
    private final BlendUI blendUI;

    public BlendOp(String name,ImageProcessor frame,BlendUI blendUI){
        this.name = name;
        this.frame = frame;
        this.blendUI = blendUI;
    }
    @Override
    public BufferedImage doOperation(BufferedImage inputImage) {
        final OperationDialog dialog = new OperationDialog( frame, blendUI);
        dialog.setVisible( true);
        if (!dialog.wasCancelled()) {
            try {
                double sensitivity = blendUI.getSensitivity();
                BufferedImage otherImage = ImageIO.read(blendUI.getOtherImagePath());

                BufferedImage output = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), inputImage.getType());
                for (int x = 0; x < output.getWidth(); x++) {
                    for (int y = 0; y < output.getHeight(); y++) {
                        int inputRGB = OperationUtilities.getRGB(x, y, inputImage);
                        int otherRGB = OperationUtilities.getRGB(x, y, otherImage);
                        int outputRGB = OperationUtilities.blend(inputRGB, otherRGB, sensitivity);
                        OperationUtilities.setRGB(x, y, outputRGB, output);
                    }
                }
                return output;
            } catch (IOException ex) {
                return inputImage;
            }
        } else {
            return inputImage;
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
