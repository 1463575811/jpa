package com.baji.work.utils.replaceFileContext;

/**
 * <pre>
 * Another 元気な class!
 * </pre>
 *
 * @author baji
 * @date 2019/05/07 20:21
 */
public abstract class contextType {
    public String[] targetContext;

    public abstract boolean isThisType(String nowLine);

    public abstract String doReplaceContext(String nowLine, ReplaceBean bean);
}
