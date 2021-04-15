package oop.im2020;

import javax.swing.*;

public class OperationFactory {
    ImageProcessor frame; 

    public OperationFactory(ImageProcessor frame) {
        this.frame = frame;
    } 

    public OperationInterface createOperation(PossibleOperations operationType){
        switch (operationType){
            case GRAYSCALE:{
                GrayscaleUI grayscaleUI = new GrayscaleUI();
                return new GrayScaleOp("Grayscale",frame,grayscaleUI);
            }
            case TINT:{
                TintUI tintUI = new TintUI();
                return new TintOp("Tint",frame,tintUI);
            }
            case CHROMAKEY:{
                ChromaKeyUI chromaKeyUI = new ChromaKeyUI(new JFileChooser());
                return new ChromaOp("Chromakey",frame,chromaKeyUI);
            }
            case NEGATIVE:{
                NegativeUI negativeUI = new NegativeUI();
                return new NegativeOp("Negative",frame,negativeUI);
            }
            case STRICTBW:{
                ThresholdUI thresholdUI = new ThresholdUI();
                return new ThreshOp("Threshold", frame,thresholdUI);
            }
            case BLEND:{
                BlendUI blendUI = new BlendUI(new JFileChooser());
                return new BlendOp("Blend", frame,blendUI);
            }
            
        }
        return null;
    }
}
