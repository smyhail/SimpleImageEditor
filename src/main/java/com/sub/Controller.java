package com.sub;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public FileChooser fileChooser;
    public File file;
    private Stage stage;
    public String typeFile;
    public static String fDir;

    @FXML
    TextField txtDir;

    @FXML
    TabPane tabPane;

    @FXML
    ImageView window;

    @FXML
    Slider sliderJasnosc, sliderR, sliderSize;

    public void initialize(URL location, ResourceBundle resources) {

    }

    public void unDisable(){
        tabPane.setDisable( false );
    }

    public static void saveFilehhhh(BufferedImage img, String typeFile) {
        File f = new File( "src/main/resources/newx.png" );
        try {
            ImageIO.write( img, typeFile, f );
        } catch (IOException e) {
            System.out.println( e );
        }
    }


    public static void saveFile(BufferedImage img, String typeFile){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files...", "*.png"  ,"*.jpg" );
        fileChooser.getExtensionFilters().add(extFilter);
        String pp = Paths.get(".").toAbsolutePath().normalize().toString();
        fileChooser.initialDirectoryProperty().set( new File( pp ) );
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try {
                ImageIO.write( img, typeFile, file );
            } catch (IOException e) {
                System.out.println( e );
            }
            System.out.println("Save");
        }
    }

    public void loadImage(ActionEvent actionEvent) throws FileNotFoundException {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        file = fileChooser.showOpenDialog(stage);
        fDir = file.getPath();
        System.out.println(fDir);
        String e = getExt( file );
        System.out.println(e);
        txtDir.setText( fDir );
        unDisable();
    }

    public static String getExt(File file)
    {
        String fileExtension="";
        // Get file Name first
        String fileName=file.getName();

        // If fileName do not contain "." or starts with "." then it is not a valid file
        if(fileName.contains(".") && fileName.lastIndexOf(".")!= 0)
        {
            fileExtension=fileName.substring(fileName.lastIndexOf(".")+1);
        }
        return fileExtension;
    }


    public void doGrayscale(ActionEvent actionEvent) {
        File f = new File( fDir );

        ImageProcessing immage = new ImageProcessing();
        BufferedImage img = immage.grayscale(f);
        saveFile( img , getExt( f ) );
    }

    public void doJasnosc(ActionEvent actionEvent) {
        File f = new File( fDir );
        ImageProcessing immage = new ImageProcessing();

        BufferedImage img = immage.brightness(f, (float) sliderJasnosc.getValue() );
        saveFile( img , getExt( f ) );
    }

    public void doRotate(ActionEvent actionEvent) {
        File f = new File( fDir );
        ImageProcessing immage = new ImageProcessing();

        System.out.println(sliderR.getValue());
        BufferedImage img = immage.rotate(f,  sliderR.getValue() );
        saveFile( img , getExt( f ) );
    }

    public void doSize(ActionEvent actionEvent) throws IOException {
        File f = new File( fDir );
        ImageProcessing immage = new ImageProcessing();

        if (sliderSize.getValue() !=0) {
            BufferedImage img = immage.resize( f, (int) sliderSize.getValue() );
            saveFile( img, getExt( f ) );
        }

    }

    public void doBinary(ActionEvent actionEvent) {
        File f = new File( fDir );

        ImageProcessing immage = new ImageProcessing();
        BufferedImage img = immage.binaryy(f);
        saveFile( img , getExt( f ) );
    }
}
