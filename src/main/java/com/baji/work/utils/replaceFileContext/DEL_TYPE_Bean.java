package com.baji.work.utils.replaceFileContext;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <pre>
 * Another 元気な class!
 * </pre>
 *
 * @author baji
 * @date 2019/05/10 11:43
 */
@SuppressWarnings("all")
public class DEL_TYPE_Bean extends ContextType{

    private DEL_TYPE_Bean() {
        this.targetContext = new String[]{
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
        };
        this.startContext = "'EDIT DEL >>> SR.劉軍豪 " + new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + " 番号：000\n";
        this.endContext = "'<<< EDIT DEL " + new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + "\n";

    }

    @Override
    public String doReplaceContext(String nowLine) {
        int count = nowLine.lastIndexOf(' ');
        String temp = "";
        this.getContext.setLength(0);
        if (count != 0 || count != -1) {
            for (int i = 0; i <= count; i++) {
                temp += " ";
            }
            return this.getContext
                    .append(temp + this.startContext)
                    .append(temp + "'" + nowLine.trim() + "\n")
                    .append(temp + this.endContext)
                    .toString();
        } else {
            count = nowLine.lastIndexOf("\t");
            for (int i = 0; i <= count; i++) {
                temp += "\t";
            }
        }
        return this.getContext
                .append(temp + this.startContext)
                .append(temp + "'" + nowLine.trim() + "\n")
                .append(temp + this.endContext)
                .toString();
    }

    public static DEL_TYPE_Bean DEL_TYPE_INIT(){
        return new DEL_TYPE_Bean();
    }

}
