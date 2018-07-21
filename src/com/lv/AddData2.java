package com.lv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//(补充8点到出现第⼀个坐标点之间的数据)和最后一行到十点间的数据
public class AddData2 {

    public static void main(String[] args) throws Exception{
        String inFileFolderPath = "C:\\Users\\lv\\Desktop\\数据2018_07_18\\2009-03-10\\2009-03-10_1_(坐标+时间)";
        File inFileFolder = new File(inFileFolderPath);
        File[] inFiles = inFileFolder.listFiles();

        for (File inFile : inFiles) {

            FileReader fileReader = new FileReader(inFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            File outFile = new File("C:\\Users\\lv\\Desktop\\数据2018_07_18\\2009-03-10\\2009-03-10_2_添加八点开始_十点前的数据\\" + inFile.getName());
            FileWriter fileWriter = new FileWriter(outFile,true);

            line = bufferedReader.readLine();
            String[] data = line.split(",");

            String x = data[0];
            String y = data[1];

            String data_string = data[2];
            String start_string = "08:00:00";
            String end_string = "22:00:00";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

            Date start = simpleDateFormat.parse(start_string);
            Date data_date = simpleDateFormat.parse(data_string);
            Date end = simpleDateFormat.parse(end_string);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(start);
            while (start.before(data_date)) {
//                String outDate = dateFormat.format(start);
//                String outTime = timeFormat.format(start);
                String outTime = simpleDateFormat.format(start);
                String outStirng = x + "," + y + "," + outTime + "\n";
                fileWriter.write(outStirng);
                calendar.add(Calendar.SECOND,1);
                start = calendar.getTime();
            }

            fileWriter.flush();
            //获取最后一行
            String end_line = null;
            fileWriter.write(line + "\n");
            while ((line = bufferedReader.readLine()) != null) {
                fileWriter.write(line + "\n");
                end_line = line;
            }

            fileWriter.flush();

            Calendar calendar2 = Calendar.getInstance();
            String[] data2 = end_line.split(",");
            data_string = data2[2];
            data_date = simpleDateFormat.parse(data_string);
            calendar2.setTime(data_date);
            while (data_date.before(end)) {
                calendar2.add(Calendar.SECOND,1);
                data_date = calendar2.getTime();
//                String outDate = dateFormat.format(data_date);
                String outTime = simpleDateFormat.format(data_date);
                String outString = data2[0] + "," +data2[1] + ","  + outTime + "\n";
                fileWriter.write(outString);

            }

            fileWriter.close();
            System.out.println(inFile.getName());
        }
    }
}
