package com.baji.work.utils.replaceFileContext;

import lombok.Builder;
import lombok.Data;

import java.io.File;

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
     * 注释生成的内容
     */
    private StringBuilder toContext;

    /**
     * 删除相关的注释的判断/执行
     */
    private DEL_TYPE_Bean delTypeBean;

    /**
     * 添加相关的注释的判断/执行
     */
    private ADD_TYPE_Bean addTypeBean;

    /**
     * 变更相关的注释的判断/执行
     */
    private UPD_TYPE_Bean updTypeBean;

    //待升级
    //private List<String> allContext;


    /**
     * 初始化数据
     * @return
     */
    public static ReplaceBean init() {
        return ReplaceBean.builder()
                .targetFilePath(new File("E:/autoUpdateFileContext/cds_S_SHIP_DIFF_DATA"))
                .toFilePath(new File("E:/autoUpdateFileContext/cds_S_SHIP_DIFF_DATA2"))
                .toContext(new StringBuilder())
                .delTypeBean(DEL_TYPE_Bean.DEL_TYPE_INIT())
                .addTypeBean(ADD_TYPE_Bean.ADD_TYPE_INIT())
                .updTypeBean(UPD_TYPE_Bean.UPD_TYPE_INIT())
                .build();

    }


}
