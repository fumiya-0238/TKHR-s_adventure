package com.example.myapp.repository;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.nio.file.Paths;
import java.nio.file.Path;

import java.io.ByteArrayOutputStream;
public enum ImagesRepository {
	INSTANCE;
    private BufferedImage title;
    private BufferedImage beginner_Button;
    private BufferedImage novice_Button;
    private BufferedImage expert_Button;
    private List<BufferedImage> monsterImages;
    private BufferedImage shop;
    
        private ImagesRepository() {
            Path currentDir = Paths.get(".").toAbsolutePath().normalize();
            String imageDir = currentDir+"/tkhr/src/main/resources/static/images/";
            try {
                title=ImageIO.read(new File(imageDir+"Title.png"));
                BufferedImage buttons=ImageIO.read(new File(imageDir+"Button.png"));
                beginner_Button= buttons.getSubimage(0,0, 100, 100);
                novice_Button = buttons.getSubimage(100,0, 100, 100);
                expert_Button = buttons.getSubimage(200,0, 100, 100);
            } catch (IOException e) {
                System.out.println("image file not found.");
            }
            monsterImages = new ArrayList<BufferedImage>();
            String name = "teki";
            for (int i = 1; i <= 7; i++) {
                try {
                    monsterImages.add(ImageIO.read(new File(imageDir+name + i + ".png")));
                } catch (IOException e) {
                    System.out.println("image file not found.");
                }
            }
        }
        private String getURL(BufferedImage image){
            StringBuilder url = new StringBuilder("data:image/png;base64,");
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] imageBytes = baos.toByteArray();
                ImageIO.write(image, "png", baos);
                url.append(Base64.getEncoder().encodeToString(imageBytes));
            } catch (IOException e) {
                System.out.println("image file not found.");
            }
            return url.toString();
        }

        public String getTitle(){
            return getURL(title);
    }

    public String getBeginnerButton(){
        return getURL(beginner_Button);
    }

    public String getNoviceButton(){
        return getURL(novice_Button);
    }

    public String getExpertButton(){
        return getURL(expert_Button);
    }

	public ImageIcon getMonsterImage(int ID, int newSize) {
		ID--;
		if (ID < 0) {
			return new ImageIcon(shop.getScaledInstance(newSize, newSize, Image.SCALE_SMOOTH));
		}
		BufferedImage bi = monsterImages.get(ID / 6).getSubimage(ID % 3 * 205, ID / 3 % 2 * 205, 205, 205);
		return new ImageIcon(bi.getScaledInstance(newSize, newSize, Image.SCALE_SMOOTH));
	}
}