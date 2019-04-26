package com.baji.work.utils.replaceFileContext;

import lombok.Builder;
import lombok.Data;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author baji
 * @date 2019/04/26 15:09
 * Another 元気な class!
 * 对于关于替换动作的一系列关键点的定义
 */
@Data
@Builder
public class ReplaceBean {

    /**
     * 要被操作的目标文件
     */
    private File targetFile;

    /**
     * 生成的文件
     */
    private File toFile;

    /**
     * 要被注释的内容
     */
    private String[] targetContext;

    /**
     * 注释生成的内容
     */
    private StringBuilder toContext;

    /**
     * 注释开头
     */
    private String startContext;

    /**
     * 注释结尾
     */
    private String endContext;

    /**
     * 初始化数据
     * @return
     */
    public static ReplaceBean init() {
        return ReplaceBean.builder()
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
    }


}
