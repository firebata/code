package com.cnfwsy.freemarker.creator.impl;

import com.cnfwsy.freemarker.bean.Conf;
import com.cnfwsy.freemarker.bean.TableInfo;
import com.cnfwsy.freemarker.creator.AbstractFileCreator;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 创建mapperClass
 *
 * @author zhangjh
 */
public class DaoClassCreator extends AbstractFileCreator {
    private static DaoClassCreator creator;

    private DaoClassCreator() {
        super();
    }

    private DaoClassCreator(Conf conf) {
        super();
        init(conf);
    }

    public static synchronized DaoClassCreator getInstance(Conf conf) {
        if (null == creator) {
            creator = new DaoClassCreator(conf);
        }
        return creator;
    }

    @Override
    public void createFile(TableInfo tableInfo) throws IOException, TemplateException {

        String ftl = "mapper.ftl";
        String fileName = tableInfo.getBeanName() + "Dao.java";
        String selfPath = conf.getDaoPackage();
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("table", tableInfo);
        root.put("conf", conf);
        Template temp = cfg.getTemplate(ftl);
        String filePath = javaPath + selfPath;
		if (conf.isPrefix()) {
			filePath = filePath + separator + tableInfo.getPrefix();
		}
		fileName = filePath + separator + fileName;
        createFile(fileName, root, temp);
    }


}