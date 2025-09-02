package com.example.myapp.repository;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

public enum ImagesRepository {
    INSTANCE;

    private String beginner_Button;
    private String novice_Button;
    private String expert_Button;
    private List<BufferedImage> monsterImages;
    private BufferedImage shop;

    private ImagesRepository() {
        monsterImages = new ArrayList<BufferedImage>();
        String base = "data:image/png;base64,";
        String resourceDir = "static/images";
        try {
            URI uri = ImagesRepository.class.getClassLoader().getResource(resourceDir).toURI();
            System.out.println("uri:" + uri);
            System.out.println("uriのスキーム:" + uri.getScheme());// File or jar
            if (uri.getScheme().equals("jar")) {
                // JAR内の場合
                FileSystem fs = FileSystems.newFileSystem(uri, Collections.emptyMap());// ファイルシステムの作成
                Path dirPath = fs.getPath(resourceDir);
                for (Path path : Files.walk(dirPath, 1).filter(Files::isRegularFile).toList()) {
                    int slash = path.toString().lastIndexOf("/") + 1;
                    processImageFromStream(path.toString(), path.toString().substring(slash), base);
                }
            } else {
                // 開発環境（ファイルシステム）の場合
                Path dirPath = Paths.get(uri);
                for (Path path : Files.walk(dirPath, 1).filter(Files::isRegularFile).toList()) {
                    processImageFromStream("static/images/" + path.getFileName().toString(),
                            path.getFileName().toString(), base);
                }
            }
        } catch (Exception e) {
            System.out.println("image file not found.");
        }
    }

    private void processImageFromStream(String filePath, String fileName, String base) {
        System.out.println("パス:" + filePath);
        System.out.println("ファイル名:" + fileName);
        try (InputStream is = ImagesRepository.class.getClassLoader().getResourceAsStream(filePath)) {
            if (is == null) {
                System.out.println("画像が見つかりません: " + fileName);
                return;
            }
            BufferedImage image = ImageIO.read(is);
            if (image != null) {
                System.out.println("読み込み成功: " + fileName);
                // ここで image を使用
                analyzeFileName(image, fileName, base);
            } else {
                System.out.println("画像の読み込みに失敗: " + fileName);
            }
        } catch (Exception e) {
            System.err.println("エラー: " + fileName + " - " + e.getMessage());
        }
    }

    private void analyzeFileName(BufferedImage image, String fileName, String base) {
        if (fileName.contains("Button")) {
            beginner_Button = getEncodeBase64(base, image.getSubimage(0, 0, 100, 100));
            novice_Button = getEncodeBase64(base, image.getSubimage(100, 0, 100, 100));
            expert_Button = getEncodeBase64(base, image.getSubimage(200, 0, 100, 100));
        } else if (fileName.contains("teki")) {
            int i = 4;
            int number = 0;
            while (Character.isDigit(fileName.charAt(i))) {
                number *= 10;
                number += Character.getNumericValue(fileName.charAt(i));
                i++;
            }
            while (monsterImages.size() < number) {
                System.out.print("サイズ" + monsterImages.size() + "No." + number);
                monsterImages.add(null);
                System.out.println("add");
            }
            monsterImages.set(number - 1, image);
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

    public String getBeginnerButton() {
        return beginner_Button;
    }

    public String getNoviceButton() {
        return novice_Button;
    }

    public String getExpertButton() {
        return expert_Button;
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