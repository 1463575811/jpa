package com.baji.work.utils.replaceFileContext;

import lombok.Builder;
import lombok.Data;

import java.io.File;

/**
 * @author baji
 * @date 2019/04/26 15:09
 * Another 元気な class!
 */
@Data
@Builder
public class ReplaceBean {

    private File targetFile;

    private File toFile;

    private String[] targetContext;

    private StringBuilder toContext;

    private String startContext;

    private String endContext;

}
