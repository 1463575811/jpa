package com.baji.work.utils.replaceFileContext;

import lombok.Builder;
import lombok.Data;

import java.io.*;
import java.lang.annotation.Target;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author baji
 * @date 2019/04/26 14:08
 * Another 元気な class!
 * <p>
 * vb单行注释代码生成器
 * @version 1.0
 */
public class ReplaceFileContext {

    public static void main(String[] args) {

        new ReplaceFileContext().doReplaceContext(ReplaceBean.init());

    }


    /**
     * 生成注释内容
     * @param replaceBean
     * @param nowLine
     * @return
     */
    private String doGetContext(ReplaceBean replaceBean, String nowLine) {
        int count = nowLine.lastIndexOf("\t");
        String temp = "";
        for (int i = 0; i <= count; i++) {
            temp += "\t";
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
     * @param replaceBean
     */
    private void doReplaceContext(ReplaceBean replaceBean) {

        try (InputStream is = new FileInputStream(replaceBean.getTargetFile());
             InputStreamReader isReader = new InputStreamReader(is);
             BufferedReader reader = new BufferedReader(isReader);
             OutputStream os = new FileOutputStream(replaceBean.getToFile());
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

    /**
     * 判断是否为需要添加注释的内容
     * @param targetContexts
     * @param nowLine
     * @return
     */
    private boolean isTargetContext(String[] targetContexts, String nowLine) {
        if (null != targetContexts) {
            for (String targetContext : targetContexts) {
                if (targetContext.equals(nowLine.trim())) {
                    return true;
                }
            }
        }
        return false;
    }

}




















