package com.example.media.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
    /**
     * 压缩
     * @param srcFileString 文件位置
     * @param zipFileString 压缩位置
     */
    public static void ZipFolder(String srcFileString, String zipFileString) {
        //创建ZIP
        try {
            ZipOutputStream outZip = new ZipOutputStream(new FileOutputStream(new File(zipFileString + "" + System.currentTimeMillis() + ".zip")));
            //创建文件
            File file = new File(srcFileString);
            //压缩
            ZipFiles(file.getParent() + File.separator, file.getName(), outZip);
            //完成和关闭
            outZip.finish();
            outZip.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解压
     * @param zipFileString 压缩文件path
     * @param srcFileString 解压位置
     */
    public static void unZipFolder(String zipFileString, String srcFileString) {
        ZipInputStream zipInputStream = null;
        try {
            zipInputStream = new ZipInputStream(new FileInputStream(zipFileString));//压缩输入流
            ZipFile zipFile = new ZipFile(zipFileString);
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry())!=null){
                //读取并写入
                BufferedInputStream inputStream = new BufferedInputStream(zipFile.getInputStream(entry));//输入流
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(srcFileString));//输出流
                int len;
                byte[] buffer = new byte[4096];
                while ((len = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }
                outputStream.flush();
                outputStream.flush();
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (zipFileString!=null){
                    zipInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static void ZipFiles(String folderString, String fileString, ZipOutputStream zipOutputSteam) {
        try {
            if (zipOutputSteam == null)
                return;
            File file = new File(folderString + fileString);
            if (file.isFile()) {
                ZipEntry zipEntry = new ZipEntry(fileString);
                FileInputStream inputStream = new FileInputStream(file);
                zipOutputSteam.putNextEntry(zipEntry);
                int len;
                byte[] buffer = new byte[4096];
                while ((len = inputStream.read(buffer)) != -1) {
                    zipOutputSteam.write(buffer, 0, len);
                }
                zipOutputSteam.closeEntry();
            } else {
                //文件夹
                String fileList[] = file.list();
                if (fileList == null || fileList.length == 0) {
                    //空文件处理
                    ZipEntry zipEntry = new ZipEntry(fileString + File.separator);
                    zipOutputSteam.putNextEntry(zipEntry);
                    zipOutputSteam.closeEntry();
                } else {
                    //递归目录
                    for (int i = 0; i < fileList.length; i++) {
                        ZipFiles(folderString + fileString + "/", fileList[i], zipOutputSteam);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
