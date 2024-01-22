package com.kavi.demo.Utilities;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip_Folder  {
	
    public static String sourcePath = "E:\\Data_Driven_Demo\\Report"; // Replace with the path to your file or folder
    public static String zipFilePath = "E:\\Data_Driven_Demo\\Report\\Report_zipped.zip"; // Replace with the desired destination ZIP file path



    public static void Zp() {

        try {
            zip(sourcePath, zipFilePath);
            System.out.println("File or folder zipped successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zip(String sourcePath, String zipFilePath) throws IOException {
        FileOutputStream fos = new FileOutputStream(zipFilePath);
        ZipOutputStream zos = new ZipOutputStream(fos);

        File sourceFile = new File(sourcePath);

        zipFile(sourceFile, sourceFile.getName(), zos);

        zos.close();
        fos.close();
    }

    private static void zipFile(File file, String fileName, ZipOutputStream zos) throws IOException {
        if (file.isDirectory()) {
            if (fileName.endsWith("/")) {
                zos.putNextEntry(new ZipEntry(fileName));
                zos.closeEntry();
            } else {
                zos.putNextEntry(new ZipEntry(fileName + "/"));
                zos.closeEntry();
            }

            File[] children = file.listFiles();
            if (children != null) {
                for (File child : children) {
                    zipFile(child, fileName + "/" + child.getName(), zos);
                }
            }
        } else {
            FileInputStream fis = new FileInputStream(file);
            ZipEntry zipEntry = new ZipEntry(fileName);
            zos.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zos.write(bytes, 0, length);
            }

            fis.close();
        }
    }
}
