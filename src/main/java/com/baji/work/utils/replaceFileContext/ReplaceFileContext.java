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

        var replaceBean = ReplaceBean.builder()
                .targetFile(new File("E:/autoUpdateFileContext/mdlMain.vb"))
                .toFile(new File("E:/autoUpdateFileContext/mdlMain2.vb"))
                .targetContext(new String[]{
                        "Dim strSql As String = String.Empty",
                        "call DbConn()",
                        "Call CloseDBLink()"
                })
                .toContext(new StringBuilder())
                .startContext("'EDIT >>> DEL SR.劉軍豪 " + new SimpleDateFormat("yyyy/mm/dd").format(new Date()) + " 番号：000\n")
                .endContext("'<<< EDIT DEL " + new SimpleDateFormat("yyyy/mm/dd").format(new Date()) + "\n")
                .build();

        new ReplaceFileContext().doReplaceContext(replaceBean);

    }

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




















