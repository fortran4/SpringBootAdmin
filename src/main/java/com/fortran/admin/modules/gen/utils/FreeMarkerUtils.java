package com.fortran.admin.modules.gen.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

/**
 * @author: lin
 * @Date: 2016-08-01 Time: 13:51
 * @description: freemarker工具类，用于代码生成
 */
@Slf4j
public class FreeMarkerUtils {

    public static String renderString(String templateString, Map<String, ?> model) throws Exception {
        StringWriter result = new StringWriter();
        try {
            Template t = new Template("name", new StringReader(templateString), new Configuration());
            t.process(model, result);
        } catch (Exception e) {
            log.error("freemark render error", e);
        }
        return result.toString();
    }

    public static String renderTemplate(Template template, Object model) throws Exception {
        StringWriter result = new StringWriter();
        try {
            template.process(model, result);
        } catch (Exception e) {
            log.error("freemark render error", e);
        }
        return result.toString();
    }

    public static Configuration buildConfiguration(String directory) throws IOException {
        Configuration cfg = new Configuration();
        Resource path = new DefaultResourceLoader().getResource(directory);
        cfg.setDirectoryForTemplateLoading(path.getFile());
        return cfg;
    }

}

