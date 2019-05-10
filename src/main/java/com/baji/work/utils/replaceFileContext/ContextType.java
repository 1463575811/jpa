package com.baji.work.utils.replaceFileContext;

/**
 * <pre>
 * Another 元気な class!
 * </pre>
 *
 * @author baji
 * @date 2019/05/07 20:21
 */
public abstract class ContextType {
    /**
     * 目标内容, 将当行内容和此内容进行比较, 用于判断是否需要加注释
     */
    public String[] targetContext;

    /**
     * 注释开头
     */
    public String startContext;

    /**
     * 注释结尾
     */
    public String endContext;

    /**
     * 事务相关统计  partern 2 依据
     */
    public int beginTrans;
    public int commitTrans;


    public StringBuilder getContext = new StringBuilder();

    /**
     * 判断是否为需要添加注释的内容
     * @param nowLine
     * @return
     */
    public boolean isThisType(String nowLine){
        for (String targetContext : this.targetContext) {
            if (targetContext.equals(nowLine.trim())) {
                if ("g_oraDb.BeginTrans()".equals(nowLine.trim())) {
                    beginTrans++;
                } else if ("g_oraDb.CommitTrans()".equals(nowLine.trim())
                        || "g_oraSs.CommitTrans()".equals(nowLine.trim())) {
                    commitTrans++;
                }
                return true;
            }
        }
        return false;
    };

    /**
     * 执行添加注释
     * @param nowLine
     * @return
     */
    public abstract String doReplaceContext(String nowLine);

}
