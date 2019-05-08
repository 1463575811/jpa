package com.baji.work.utils.replaceFileContext;

import java.io.*;

/**
 * <pre>
 * Another 元気な class!
 * </pre>
 *
 * @author baji
 * @date 2019/04/30 10:54
 */
public class sqlRemove {

    /**
     * 要被操作的目标文件
     */
    private static File targetFile = new File("E:\\autoUpdateFileContext\\sql.txt");

    /**
     * 生成的文件
     */
    private static File toFile = new File("E:\\autoUpdateFileContext\\sql2.txt");
    ;

    public static void main(String[] args) {
        try (InputStream is = new FileInputStream(targetFile);
             InputStreamReader isReader = new InputStreamReader(is);
             BufferedReader reader = new BufferedReader(isReader);
             OutputStream os = new FileOutputStream(toFile);
             OutputStreamWriter osWriter = new OutputStreamWriter(os);
             BufferedWriter writer = new BufferedWriter(osWriter)) {
            StringBuilder sb = new StringBuilder();
            StringBuilder temp = new StringBuilder();
            while (true) {
                String now = reader.readLine();
                sb.setLength(0);
                if (now != null) {
                    String str = now.trim().replaceAll("'strSql = strSql & \"", "");
                    temp.setLength(0);
                    int i = str.lastIndexOf("\"");
                    System.out.println(i + "---" + str.length());
                    if (i != -1) {
                        temp.append(str).setCharAt(i, '\u0000');
                    }
                    writer.write(temp.toString() + "\n");
                } else {
                    break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

