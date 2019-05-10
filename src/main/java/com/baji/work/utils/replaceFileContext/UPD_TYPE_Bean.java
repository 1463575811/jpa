package com.baji.work.utils.replaceFileContext;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <pre>
 * Another 元気な class!
 * </pre>
 *
 * @author baji
 * @date 2019/05/10 17:12
 */
@SuppressWarnings("all")
public class UPD_TYPE_Bean extends ContextType {

    private UPD_TYPE_Bean() {
        this.targetContext = new String[]{
            "lngRet = NIT_OraCreateDynaset(objRec, strSQL, g_oraDb)"
        };
        this.startContext = "'EDIT >>> SR.劉軍豪 " + new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + " 番号：000\n";
        this.endContext = "'<<< EDIT " + new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + "\n";

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
                    .append(temp + nowLine.trim().replaceAll("strSQL", "requestDTO") + "\n")
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

    public static UPD_TYPE_Bean UPD_TYPE_INIT(){
        return new UPD_TYPE_Bean();
    }

}
