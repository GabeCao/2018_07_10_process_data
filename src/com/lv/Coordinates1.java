package com.lv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

public class Coordinates1 {
//  取出坐标和时间
    public static void main(String[] args) throws Exception{
        String inFileFolderPath = "C:\\Users\\lv\\Desktop\\数据2018_07_18\\2009-03-10\\2009-03-10补轨迹点";
        File inFileFolder = new File(inFileFolderPath);
        File[] inFiles = inFileFolder.listFiles();

        for (File inFile : inFiles) {

            FileReader fileReader = new FileReader(inFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            File outFile = new File("C:\\Users\\lv\\Desktop\\数据2018_07_18\\2009-03-10\\2009-03-10_1_(坐标+时间)\\" + inFile.getName());
            FileWriter fileWriter = new FileWriter(outFile,true);

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                try {
                    String x = data[6];
                    String y = data[7];
                    String date = data[5];

                    fileWriter.write(x + ',' + y + ',' + date + '\n');
                }catch (Exception e) {
                    System.out.println(e);
                    System.out.println(Arrays.toString(data));
                }
            }
            fileWriter.close();
            System.out.println(inFile.getName());
        }
    }
}
