package com.sub;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main22 {


    public static  int BRIGHTNESS_FACTOR =+100;

    public static void main(String[] args) throws IOException {

        BufferedImage img;
        int pixel[];

        File input = new File("src/main/resources/seba.png");
        img = ImageIO.read(input);
        for(int i =0; i<img.getWidth();i++){
            for(int j =0;j<img.getHeight();j++)
            {
                pixel = img.getRaster().getPixel(i,j,new int[3]);

                int k = pixel[0] +BRIGHTNESS_FACTOR;
                if(k >255)k =255;
                else if(k<0) k =0;

                Color color = new Color(k,k,k);
                int rgb = color.getRGB();
                img.setRGB(i,j,rgb);
            }
        }

        File output = new File("src/main/resources/new_jasn.png");
        ImageIO.write(img,"png",output);
    }
}
