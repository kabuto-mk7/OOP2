package oop.im2020;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class ChromaOp implements OperationInterface {
    private final String name;
    private final ImageProcessor frame;
    private final ChromaKeyUI chromaKeyUI;

    public ChromaOp(String name,ImageProcessor frame,ChromaKeyUI chromaKeyUI){
        this.name = name;
        this.frame = frame;
        this.chromaKeyUI = chromaKeyUI;
    }

    @Override
    public BufferedImage doOperation(BufferedImage inputImage) {
        final OperationDialog dialog = new OperationDialog( frame, chromaKeyUI);
        dialog.setVisible( true);
        if (!dialog.wasCancelled()) {
            try {
                double sensitivity = chromaKeyUI.getSensitivity();
                BufferedImage otherImage = ImageIO.read(chromaKeyUI.getOtherImagePath());

                int targetRGB = chromaKeyUI.getTargetColor().getRGB();

                BufferedImage output = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), inputImage.getType());
                for (int x = 0; x < output.getWidth(); x++) {
                    for (int y = 0; y < output.getHeight(); y++) {
                        int inputRGB = OperationUtilities.getRGB(x, y, inputImage);
                        int otherRGB = OperationUtilities.getRGB(x, y, otherImage);
                        int outputRGB = OperationUtilities.chromaKey(inputRGB, otherRGB, targetRGB, sensitivity);
                        OperationUtilities.setRGB(x, y, outputRGB, output);
                    }
                }
                return output;
            } catch (IOException ex) {
                ex.printStackTrace();
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
