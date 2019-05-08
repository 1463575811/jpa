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
    private File targetFilePath;

    /**
     * 生成的文件
     */
    private File toFilePath;

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

    private class DEL_TYPE extends contextType{

        public DEL_TYPE(String[] targetContext){
            super.targetContext = targetContext;
        }

        @Override
        public boolean isThisType(String nowLine) {
            return false;
        }

        @Override
        public String doReplaceContext(String nowLine, ReplaceBean bean) {
            return null;
        }
    }

    private class ADD_TYPE extends contextType{

        public ADD_TYPE(String[] targetContext){
            super.targetContext = targetContext;
        }

        @Override
        public boolean isThisType(String nowLine) {
            return false;
        }

        @Override
        public String doReplaceContext(String nowLine, ReplaceBean bean) {
            return null;
        }
    }

    private class UPD_TYPE extends contextType{

        public UPD_TYPE(String[] targetContext){
            super.targetContext = targetContext;
        }

        @Override
        public boolean isThisType(String nowLine) {
            return false;
        }

        @Override
        public String doReplaceContext(String nowLine, ReplaceBean bean) {
            return null;
        }
    }

    /**
     * 初始化数据
     * @return
     */
    public static ReplaceBean init() {
        return ReplaceBean.builder()
                .targetFilePath(new File("E:/autoUpdateFileContext/skShenMeShenMeList"))
                .toFilePath(new File("E:/autoUpdateFileContext/skShenMeShenMeList2"))
                .targetContext(new String[]{
                        "Dim strSql As String = String.Empty",
                        "Dim strSql2 As String = String.Empty",
                        "Dim strSql As String = String.Empty 'SQL生成用",
                        "Dim strSQL As String = String.Empty",
                        "Call DbConn()",
                        "Call CloseDBLink()",
                        "g_oraSs.Rollback()",
                        "Call NIT_OraRemoveParam(g_oraDb)",
                        "Call NIT_OraRemoveParam(g_oraDb) ' バインド変数の設定",
                        "g_oraSs.BeginTrans()",
                        "Call DbDisConn()",
                        "Call NIT_OraRemoveParam(g_oraDb) 'ﾊﾞｲﾝﾄﾞ変数 ｸﾘｱ",
                        "Call NIT_OraRemoveParam(g_oraSs)",
                        "Call DbLinkDisConn() 'DBLink切断",
                        "g_oraDb.BeginTrans()",
                        "g_oraDb.CommitTrans()",
                        "Call DbLinkDisConn()",
                        "g_oraSs.CommitTrans()"
                })
                .toContext(new StringBuilder())
                .startContext("'EDIT >>> DEL SR.劉軍豪 " + new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + " 番号：000\n")
                .endContext("'<<< EDIT DEL " + new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + "\n")
                .build();
    }


}
