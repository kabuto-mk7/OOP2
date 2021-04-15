package oop.im2020;
import java.awt.image.BufferedImage;

public class TintOp implements OperationInterface {
    private final String name;
    private final ImageProcessor frame;
    private final TintUI tintUI;

    public TintOp(String name,ImageProcessor frame,TintUI tintUI){
        this.name = name;
        this.frame = frame;
        this.tintUI = tintUI;
    }

    @Override
    public BufferedImage doOperation(BufferedImage inputImage) {
        final OperationDialog dialog = new OperationDialog( frame, tintUI);
        dialog.setVisible( true);
        if (!dialog.wasCancelled()) {
            final ColourComponent band = tintUI.getChosenColor();
            final double alpha = tintUI.getAlpha() / 100.0;
            for (int x = 0; x < inputImage.getWidth(); x++) {
                for (int y = 0; y < inputImage.getHeight(); y++) {
                    final int inputRGB = OperationUtilities.getRGB(x, y, inputImage);
                    final int outputRGB = OperationUtilities.tint(inputRGB, alpha, band);
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
