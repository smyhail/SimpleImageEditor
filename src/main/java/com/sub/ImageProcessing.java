package com.sub;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;

public class ImageProcessing {


    public BufferedImage grayscale(File f) {
        BufferedImage img = null;

        try {
            img = ImageIO.read( f );
        } catch (IOException e) {
            System.out.println( e );
        }
        int width = img.getWidth();
        int height = img.getHeight();
        for (int y = 0; y < height; y++){
            for ( int x = 0 ; x < width; x++){
                int p = img.getRGB( x,y );
                int a = (p>>24) & 0xff;
                int r = (p>>16) & 0xff;
                int g = (p>>8) & 0xff;
                int b = (p) & 0xff;
                int avg = (r+g+b)/3;
                p = (a<<24)|(avg<<16)|(avg<<8)|avg;
                img.setRGB( x,y , p);
            }
        }
        return img;
    }


    public BufferedImage brightness(File f, float val) {
        BufferedImage img = null;

        Graphics2D g = null;
        try {
            img = ImageIO.read( f );
        } catch (IOException e) {
            System.out.println( e );
        }
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage bi = new BufferedImage( width, height, BufferedImage.TYPE_INT_ARGB );
        Graphics bg = bi.getGraphics();
        bg.drawImage(img, 0, 0, null);
        bg.dispose();
        RescaleOp rescaleOp = new RescaleOp(val, 0, null);
        rescaleOp.filter(bi, bi);
        return bi;
    }
    public BufferedImage rotate(File f, double angle){
       BufferedImage img = null;

       try {
           img = ImageIO.read( f );
       } catch (IOException e) {
           System.out.println( e );
       }

       int width = img.getWidth() ;
       int height = img.getHeight() ;
       int type = img.getType();

       BufferedImage rotateImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
       Graphics2D g = rotateImage.createGraphics();
       AffineTransform transform = new AffineTransform();
       transform.rotate(angle / 180 * Math.PI, width / 2 , height / 2);
       g.drawRenderedImage(img, transform);
       g.dispose();
       return rotateImage;


    }




    public BufferedImage resize(File f, int percent) throws IOException {
        BufferedImage img = null;
        try {
            img = ImageIO.read( f );
        } catch (IOException e) {
            System.out.println( e );
        }
        double width = img.getWidth() + (img.getWidth()  * percent * 0.01  );
        double  height = img.getHeight() +(img.getHeight() * percent * 0.01 );
        int type = img.getType();

         BufferedImage resizedImage = new BufferedImage( (int)width, (int)height, type);
         Graphics2D g = resizedImage.createGraphics();
         g.drawImage(img, 0, 0,(int) width, (int)height, null);
         g.dispose();
        return resizedImage;
    }

    public BufferedImage binaryy(File f){

        BufferedImage img = null;
        try {
            img = ImageIO.read( f );
        } catch (IOException e) {
            System.out.println( e );
        }

        int pixel[];
        int  k = 0;
        Color color;
        int rgb;
        int imgSize = img.getWidth()*img.getHeight();


        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {

                pixel = img.getRaster().getPixel( i,j,new int[3] );
                k += pixel[0];
            }
        }

        imgSize = k / imgSize;

        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {

                pixel = img.getRaster().getPixel( i,j,new int[3] );
                if(pixel[0]>imgSize) k = 255;
                else
                    k=0;
                color = new Color(k,k,k);
                rgb = color.getRGB();
                img.setRGB( i,j,rgb );

            }
        }
        return img;
    }


}

