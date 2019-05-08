package com.baji.work.utils.replaceFileContext;

import lombok.Builder;
import lombok.Data;

import javax.servlet.ServletOutputStream;
import java.io.*;

/**
 * @author baji
 * @version 1.0
 * @date 2019/04/26 14:08
 * Another 元気な class!
 * <p>
 * vb单行注释代码生成器
 */
public class ReplaceFileContext {

    /**
     * 事务相关统计  partern依据
     */
    private static int beginTrans;
    private static int commitTrans;

    public static void main(String[] args) {

        System.out.println(new ReplaceFileContext().doReplaceContext(ReplaceBean.init()));

    }


    /**
     * 生成注释内容
     *
     * @param replaceBean
     * @param nowLine
     * @return
     */
    private String doGetContext(ReplaceBean replaceBean, String nowLine) {
        int count = nowLine.lastIndexOf(' ');
        String temp = "";
        if (count != 0 || count != -1) {
            System.out.println(count);
            for (int i = 0; i <= count; i++) {
                temp += " ";
            }
            replaceBean.getToContext().setLength(0);
            return replaceBean.getToContext()
                    .append(temp + replaceBean.getStartContext())
                    .append(temp + "'" + nowLine.trim() + "\n")
                    .append(temp + replaceBean.getEndContext())
                    .toString();
        } else {
            count = nowLine.lastIndexOf("\t");
            for (int i = 0; i <= count; i++) {
                temp += "\t";
            }
        }
        replaceBean.getToContext().setLength(0);
        return replaceBean.getToContext()
                .append(temp + replaceBean.getStartContext())
                .append(temp + "'" + nowLine.trim() + "\n")
                .append(temp + replaceBean.getEndContext())
                .toString();
    }

    /**
     * 读取文件, 判断是否需要注释, 如果是添加注释, 写出新文件
     *
     * @param replaceBean
     */
    private String doReplaceContext(ReplaceBean replaceBean) {
        for (File file : replaceBean.getTargetFilePath().listFiles()) {
            try (InputStream is = new FileInputStream(file);
                 InputStreamReader isReader = new InputStreamReader(is);
                 BufferedReader reader = new BufferedReader(isReader);
                 OutputStream os = new FileOutputStream(replaceBean.getToFilePath() + "/" + file.getName());
                 OutputStreamWriter osWriter = new OutputStreamWriter(os);
                 BufferedWriter writer = new BufferedWriter(osWriter)) {

                while (true) {
                    String now = reader.readLine();
                    replaceBean.getToContext().setLength(0);
                    if (now != null) {

                        if (isTargetContext(replaceBean.getTargetContext(), now)) {
                            writer.write(doGetContext(replaceBean, now));
                        } else {
                            writer.write("" + now + "\n");
                        }
                    } else {
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "开启事务次数:-- " + beginTrans + "/n 提交事务次数:-- " +commitTrans;
    }

    /**
     * 判断是否为需要添加注释的内容
     *
     * @param targetContexts
     * @param nowLine
     * @return
     */
    private boolean isTargetContext(String[] targetContexts, String nowLine) {
        if (null != targetContexts) {
            for (String targetContext : targetContexts) {
                if (targetContext.equals(nowLine.trim())) {
                    if ("g_oraDb.BeginTrans()".equals(nowLine.trim())) {
                        beginTrans++;
                    } else if ("g_oraDb.CommitTrans()".equals(nowLine.trim())
                    ||"g_oraSs.CommitTrans()".equals(nowLine.trim())) {
                        commitTrans++;
                    }
                    return true;
                }
            }
        }
        return false;
    }


}




















