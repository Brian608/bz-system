package com.feather.bz.common.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.common.utils
 * @className: ImageUtils
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2023-04-10 14:27
 * @version: 1.0
 */

public class ImageUtils {
    public static String  saveBase64ImageToFile(String base64Data, String filePath) throws IOException {

        // Remove base64 string prefix if present
        if (base64Data.contains(",")) {
            base64Data = base64Data.substring(base64Data.indexOf(",") + 1);
        }

        // Decode base64 string
        byte[] decodedBytes = Base64.decodeBase64(base64Data);

        // Create target directory if it doesn't exist
        Path targetDirectory = Paths.get(filePath);
        if (!Files.exists(targetDirectory)) {
            try {
                Files.createDirectories(targetDirectory);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        // Generate a random file name with the specified extension
        String fileName =  UUID.randomUUID().toString() + ".png";
        Path targetFilePath = targetDirectory.resolve(fileName);

        // Save decoded bytes to a file
        try (FileOutputStream fos = new FileOutputStream(targetFilePath.toFile())) {
            fos.write(decodedBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return fileName;
    }
}
