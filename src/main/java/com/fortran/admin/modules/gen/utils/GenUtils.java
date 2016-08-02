package com.fortran.admin.modules.gen.utils;

import com.fortran.admin.modules.core.utils.FileUtils;
import com.fortran.admin.modules.core.utils.guava.MyStrings;
import com.fortran.admin.modules.core.xml.JaxbMapper;
import com.fortran.admin.modules.gen.domain.Column;
import com.fortran.admin.modules.gen.domain.Gen;
import com.fortran.admin.modules.gen.domain.GenScheme;
import com.fortran.admin.modules.gen.domain.GenTemplate;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @See {copy from jeesite}
 * Created by lin on 2016-06-13.
 */
@Slf4j
public class GenUtils {

    /**
     * jdbcType-->javaType
     *
     * @param jdbcType
     * @return
     */
    public static String toJavaType(String jdbcType) {

        String result = "";

        if (MyStrings.startsWithIgnoreCase(jdbcType, "CHAR")
                || MyStrings.startsWithIgnoreCase(jdbcType, "VARCHAR")
                || MyStrings.startsWithIgnoreCase(jdbcType, "NARCHAR")) {
            result = "String";
        } else if (MyStrings.startsWithIgnoreCase(jdbcType, "DATETIME")
                || MyStrings.startsWithIgnoreCase(jdbcType, "DATE")
                || MyStrings.startsWithIgnoreCase(jdbcType, "TIMESTAMP")) {
            result = "Date";
        } else if (MyStrings.startsWithIgnoreCase(jdbcType, "BIGINT")
                || MyStrings.startsWithIgnoreCase(jdbcType, "NUMBER")
                || MyStrings.startsWithIgnoreCase(jdbcType, "INT")
                || MyStrings.startsWithIgnoreCase(jdbcType, "SMALLINT")) {
            // 如果是浮点型
            String[] ss = MyStrings.split(MyStrings.substringBetween(jdbcType, "(", ")"), ",");
            if (ss != null && ss.length == 2 && Integer.parseInt(ss[1]) > 0) {
                result = "Double";
            } else if (ss != null && ss.length == 1 && Integer.parseInt(ss[0]) <= 10) {
                result = "Integer";
            } else {
                result = "Long";
            }
        }
        return result;

    }


    /**
     * 获取字段长度
     * @param jdbcType
     * @return
     */
    public static String getLength(String jdbcType){
        String length = "0";
        if (MyStrings.startsWithIgnoreCase(jdbcType, "BIGINT")
                || MyStrings.startsWithIgnoreCase(jdbcType, "NUMBER")
                || MyStrings.startsWithIgnoreCase(jdbcType, "INT")
                || MyStrings.startsWithIgnoreCase(jdbcType, "SMALLINT")) {
            length = MyStrings.substringBetween(jdbcType, "(", ")");
        }
        return length;
    }


    /**
     * XML文件转换为对象
     *
     * @param fileName
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T fileToObject(String fileName, Class<?> clazz) {
        try {
            String pathName = "/gen/templates/" + fileName;
            log.debug("template path :"+ pathName);
            Resource resource = new ClassPathResource(pathName);
            InputStream is = resource.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line).append("\r\n");
            }
            if (is != null) {
                is.close();
            }
            if (br != null) {
                br.close();
            }
            return (T) JaxbMapper.fromXml(sb.toString(), clazz);
        } catch (Exception e) {
            log.error("Error file convert: {}", e.getMessage());
        }
        return null;
    }

    /**
     * 获取模板路径
     *
     * @return
     */
    public static String getTemplatePath() {
        try {
            File file = new DefaultResourceLoader().getResource("").getFile();
            if (file != null) {
                return file.getAbsolutePath() + File.separator + MyStrings.replaceEach(GenUtils.class.getName(),
                        new String[]{"util." + GenUtils.class.getSimpleName(), "."}, new String[]{"template", File.separator});
            }
        } catch (Exception e) {
            log.error("{}", e);
        }

        return "";
    }


    /**
     * 根据生成方案得到模板
     *
     * @param scheme 与GenScheme枚举对应
     * @return
     */
    public static List<GenTemplate> getTemplateList(String scheme, Gen gen) {
        List<GenTemplate> templates = Lists.newArrayList();
        log.debug("scheme:" + scheme);
        GenScheme genScheme = new GenScheme(gen);
        if (Strings.isNullOrEmpty(scheme)) {

            for (Map.Entry<String,String> entry : genScheme.getScheme().entrySet()){
                String [] paths = entry.getValue().split(",");
                for (String path : paths) {
                    GenTemplate template = fileToObject(path, GenTemplate.class);
                    templates.add(template);
                }
            }

        } else {
            if (genScheme.getScheme().containsKey(scheme)) {
                String [] paths = genScheme.getScheme().get(scheme).split(",");
                for (String path : paths) {
                    GenTemplate template = fileToObject(path, GenTemplate.class);
                    templates.add(template);
                }
            }
        }
        return templates;
    }


    /**
     * 构建数据模型
     *
     * @param gen
     * @return
     */
    public static Map<String, Object> getDataModel(Gen gen, List<Column> columns) {
        Map<String, Object> model = Maps.newHashMap();
        model.put("packageName", MyStrings.lowerCase(gen.getPackageName()));
        model.put("lastPackageName", MyStrings.substringAfterLast((String) model.get("packageName"), "."));
        model.put("moduleName", MyStrings.lowerCase(gen.getModuleName()));
        model.put("subModuleName", MyStrings.lowerCase(gen.getSubModuleName()));
        model.put("className", MyStrings.uncapitalize(gen.getClassName()));
        model.put("ClassName", MyStrings.capitalize(gen.getClassName()));
        model.put("funcName", MyStrings.capitalize(gen.getFuncName()));
        model.put("urlPrefix", model.get("moduleName") + (MyStrings.isNotBlank(gen.getSubModuleName())
                ? "/" + MyStrings.lowerCase(gen.getSubModuleName()) : "") + "/" + model.get("className"));
        model.put("viewPrefix", MyStrings.substringAfterLast((String)model.get("packageName"),".")+"/"+
                model.get("urlPrefix"));
        model.put("permissionPrefix", model.get("moduleName") + (MyStrings.isNotBlank(gen.getSubModuleName())
                ? ":" + MyStrings.lowerCase(gen.getSubModuleName()) : "") + ":" + model.get("className"));
        model.put("tableName", gen.getTableName());
        model.put("columns", columns);
        //list页面查询条件
        List<Column> queryColumns = Lists.newArrayList();
        //list页面查询列表
        List<Column> ListColumns = Lists.newArrayList();
        for ( Column column : columns){
            if (column.isQuery()){
                queryColumns.add(column);
            }
            if (column.isList()){
                ListColumns.add(column);
            }
        }
        model.put("queryColumns", queryColumns);
        model.put("listColumns", ListColumns);
        model.put("listColumnsSize", ListColumns.size()+1);//列长
        model.put("gen",gen);
        return model;
    }

    /**
     * 生成文件
     *
     * @param tpl           模板
     * @param model         数据模型
     * @param isReplaceFile 是否替换文件
     */
    public static void generate(GenTemplate tpl, Map<String, Object> model, boolean isReplaceFile) throws Exception {
        // 获取生成文件
        String fileName = getProjectPath() + File.separator
                + MyStrings.replaceEach(FreeMarkerUtils.renderString(tpl.getFilePath() + "/", model),
                new String[]{"//", "/", "."}, new String[]{File.separator, File.separator, File.separator})
                + FreeMarkerUtils.renderString(tpl.getFileName(), model);
        // 获取生成文件内容
        String content = FreeMarkerUtils.renderString(MyStrings.trimToEmpty(tpl.getContent()), model);

       /* if (log.isDebugEnabled()){
            log.debug("target fileName === " + fileName);
            log.debug("=========before ==========");
            log.debug(tpl.getContent());
            log.debug("========= after ==========");
            log.debug(content);
        }
*/
        if (isReplaceFile){
            FileUtils.deleteFile(fileName);
        }
        // 创建并写入文件
        if (FileUtils.createFile(fileName)){
            FileUtils.writeToFile(fileName, content, true);
            log.debug(" create " + fileName);
        }

    }

    /**
     * 获取项目路径
     *
     * @return
     */
    private static String getProjectPath() {
        String projectPath = "";
        try {
            File file = new DefaultResourceLoader().getResource("").getFile();
            if (file != null) {
                while (true) {
                    File f = new File(file.getPath() + File.separator + "src" + File.separator + "main");
                    if (f == null || f.exists()) {
                        break;
                    }
                    if (file.getParentFile() != null) {
                        file = file.getParentFile();
                    } else {
                        break;
                    }
                }
                projectPath = file.toString();
                log.debug("project path :" + projectPath);
            }
        } catch (IOException e) {
            log.info("get project path error", e);
        }
        return projectPath;
    }

}
