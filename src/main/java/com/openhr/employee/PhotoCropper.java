package com.openhr.employee;

import static javax.imageio.ImageIO.read;

import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class PhotoCropper extends JComponent {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    static ImageProducer producer;
    static FilteredImageSource fis;
    static ImageIcon imageIcon;
    static BufferedImage croppedPhoto;
    static ImageFilter cif;
    private BufferedImage photo;
    private String photoPath;

    public PhotoCropper(String photoPath) {
        this.photoPath = photoPath;
    }

    public BufferedImage getCroppedImage() {
        InputStream in;
        System.out.println("PHOTO PATH BEFORE READ-IN");
        System.out.println(photoPath);
        try {
            in = new FileInputStream(photoPath);
            photo = read(in);
        } catch (Exception e) {
            e.printStackTrace();
        }

        croppedPhoto = photo.getSubimage(PhotoCropDimension.x, PhotoCropDimension.y,
                PhotoCropDimension.width, PhotoCropDimension.height);
        return croppedPhoto;
    }
}
