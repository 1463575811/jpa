package com.baji.work.utils.replaceFileContext;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @author baji
 * @version 1.0
 * @date 2019/04/26 14:08
 * Another 元気な class!
 * <p>
 * vb单行注释代码生成器
 */
@Slf4j
public class ReplaceFileContext {

    public static void main(String[] args) {

        log.info(new ReplaceFileContext().doReplaceContext(ReplaceBean.init()));

    }

    /**
     * 读取文件, 判断是否需要注释, 如果是添加注释, 写出新文件
     *
     * @param replaceBean
     */
    private String doReplaceContext(ReplaceBean replaceBean) {

        //用于记录每个动作的发现的事务相关代码
        StringBuilder transReport = new StringBuilder();

        //将生成前和生成后的代码放在不同的文件夹, 新地方可能没有创建, 在此处进行创建
        if (!replaceBean.getToFilePath().exists()) {
            replaceBean.getToFilePath().mkdir();
        }

        //遍历目标文件夹下的所有文件, 进行判断和加注释
        for (File file : replaceBean.getTargetFilePath().listFiles()) {

            //获取输入输出流
            try (InputStream is = new FileInputStream(file);
                 InputStreamReader isReader = new InputStreamReader(is);
                 BufferedReader reader = new BufferedReader(isReader);
                 OutputStream os = new FileOutputStream(replaceBean.getToFilePath() + "/" + file.getName());
                 OutputStreamWriter osWriter = new OutputStreamWriter(os);
                 BufferedWriter writer = new BufferedWriter(osWriter)) {

                //循环读取该文件每一行代码
                while (true) {

                    //now  当前行内容
                    String now = reader.readLine();
                    if (now != null) {

                        //判断是哪个类型的内容. 如果都不是, 原样输出
                        if (replaceBean.getDelTypeBean().isThisType(now)) {
                            DEL_TYPE_Bean delete = replaceBean.getDelTypeBean();
                            writer.write(delete.doReplaceContext(now));

                            transReport.append("DEL操作发现的开启事务次数:-- " + delete.beginTrans + ", 提交事务次数:-- " + delete.commitTrans + "\n");
                        } else if (replaceBean.getAddTypeBean().isThisType(now)) {
                            writer.write(replaceBean.getAddTypeBean().doReplaceContext(now));
                        } else if (replaceBean.getUpdTypeBean().isThisType(now)) {
                            log.info("upd也进来了!!");
                            writer.write(replaceBean.getUpdTypeBean().doReplaceContext(now));
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

        //返回事务信息
        return transReport.toString();
    }

}




















