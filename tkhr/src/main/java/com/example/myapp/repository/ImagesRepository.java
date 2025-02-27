package com.example.myapp.repository;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Base64;
import javax.imageio.ImageIO;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.ByteArrayOutputStream;

public enum ImagesRepository {
    INSTANCE;

    private String title;
    private String beginner_Button;
    private String novice_Button;
    private String expert_Button;
    private String battle;
    private List<BufferedImage> monsterImages;
    private BufferedImage shop;

    private ImagesRepository() {
        Path currentDir = Paths.get(".").toAbsolutePath().normalize();
        String imageDir = currentDir + "/tkhr/src/main/resources/static/images/";
        try {
            String base = "data:image/png;base64,";
            title = getEncodeBase64(base, ImageIO.read(new File(imageDir + "Title.png")));
            BufferedImage buttons = ImageIO.read(new File(imageDir + "Button.png"));
            beginner_Button = getEncodeBase64(base, buttons.getSubimage(0, 0, 100, 100));
            novice_Button = getEncodeBase64(base, buttons.getSubimage(100, 0, 100, 100));
            expert_Button = getEncodeBase64(base, buttons.getSubimage(200, 0, 100, 100));
            battle = getEncodeBase64(base, ImageIO.read(new File(imageDir + "Battle.png")));
        } catch (IOException e) {
            System.out.println("image file not found.");
        }
        monsterImages = new ArrayList<BufferedImage>();
        String name = "teki";
        for (int i = 1; i <= 7; i++) {
            try {
                monsterImages.add(ImageIO.read(new File(imageDir + name + i + ".png")));
            } catch (IOException e) {
                System.out.println("image file not found.");
            }
        }
    }

    private String getEncodeBase64(String base, BufferedImage image) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            byte[] imageBytes = baos.toByteArray();
            return base + Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            System.out.println("URL失敗");
        }
        return "";
    }

    public String getTitle() {
        return title;
    }

    public String getBeginnerButton() {
        return beginner_Button;
    }

    public String getNoviceButton() {
        return novice_Button;
    }

    public String getExpertButton() {
        return expert_Button;
    }

    public String getBattle() {
        return battle;
    }

    public String getMonsterImage(int ID, int newSize) {
        ID--;
        BufferedImage scaledImage = new BufferedImage(newSize, newSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaledImage.createGraphics();

        // 画質を向上させるための補間設定（オプション）
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

        // 画像をスケーリングして描画
        // Graphics2Dを解放
        if (ID < 0) {
            g2d.drawImage(shop.getScaledInstance(newSize, newSize, Image.SCALE_SMOOTH), 0, 0, newSize, newSize, null);
            g2d.dispose();
            return getEncodeBase64("data:image/png;base64,", scaledImage);
        }
        BufferedImage bi = monsterImages.get(ID / 6).getSubimage(ID % 3 * 205, ID / 3 % 2 * 205, 205, 205);
        g2d.drawImage(bi.getScaledInstance(newSize, newSize, Image.SCALE_SMOOTH), 0, 0, newSize, newSize, null);
        g2d.dispose();
        return getEncodeBase64("data:image/png;base64,", scaledImage);
    }
}