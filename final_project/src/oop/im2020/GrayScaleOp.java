package oop.im2020;


import java.awt.image.BufferedImage;

public class GrayScaleOp implements OperationInterface {
    private final String name;
    private final ImageProcessor frame;
    private final GrayscaleUI grayscaleUI;

    public GrayScaleOp(String name,ImageProcessor frame,GrayscaleUI grayscaleUI){
        this.name = name;
        this.frame = frame;
        this.grayscaleUI = grayscaleUI;
    }

    @Override
    public BufferedImage doOperation(BufferedImage inputImage) {
        final OperationDialog dialog = new OperationDialog( frame, grayscaleUI);
        dialog.setVisible( true);
        if (!dialog.wasCancelled()) {
            for (int x = 0; x < inputImage.getWidth(); x++) {
                for (int y = 0; y < inputImage.getHeight(); y++) {
                    final int inputRGB = OperationUtilities.getRGB(x, y, inputImage);
                    final int outputRGB = OperationUtilities.grayscale(inputRGB);
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
