package com.xc.utils.generateCode;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class GeneratorService {
    public GeneratorService() {
    }

    public void codeGenerate(ParamInfo paramInfo) {
        paramInfo.setTableSql(OperateDB.getCreateSql(paramInfo.getUrl(), paramInfo.getUser(), paramInfo.getPassword(), paramInfo.getTableName()));
        ClassInfo classInfo = null;

        try {
            System.out.println(paramInfo.getTableSql());
            classInfo = TableParseUtil.processTableIntoClassInfo(paramInfo);
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        classInfo.setPackageName(paramInfo.getPackageName());
        classInfo.setModuleName(paramInfo.getModuleName());
        classInfo.setAuthorName(paramInfo.getAuthorName());
        Map<String, Object> params = new HashMap(8);
        params.put("classInfo", classInfo);
        params.put("tableName", classInfo.getTableName());
        params.put("authorName", paramInfo.getAuthorName());
        params.put("packageName", paramInfo.getPackageName());
        params.put("packageNameForApi", paramInfo.getPackageNameForApi());
        params.put("returnUtil", paramInfo.getReturnUtil());
        params.put("swagger", paramInfo.getSwagger());
        params.put("serialVersionUID", RandomUtil.randomString("123456789", 18));
        System.out.println("generated table:" + classInfo.getTableName() + ",field size:" + (classInfo.getFieldList() == null ? "" : classInfo.getFieldList().size()));
        Map result = null;

        try {
            result = this.getResultByParams(params);
            FileWriter((String)result.get("controller"), paramInfo.getProjectPath() + "/src/main/java/" + paramInfo.getPackageName(), paramInfo.getModuleName() + "/controller", StringUtils.upperCaseFirstAndUnderlineToCamelCase(classInfo.getTableName()) + "Controller.java");
            FileWriter((String)result.get("service"), paramInfo.getProjectPath() + "/src/main/java/" + paramInfo.getPackageName(), paramInfo.getModuleName() + "/service", "I" + StringUtils.upperCaseFirstAndUnderlineToCamelCase(classInfo.getTableName()) + "Service.java");
            FileWriter((String)result.get("service_impl"), paramInfo.getProjectPath() + "/src/main/java/" + paramInfo.getPackageName(), paramInfo.getModuleName() + "/service/impl", StringUtils.upperCaseFirstAndUnderlineToCamelCase(classInfo.getTableName()) + "ServiceImpl.java");
            FileWriter((String)result.get("data"), paramInfo.getProjectPath() + "/src/main/java/" + paramInfo.getPackageName(), paramInfo.getModuleName() + "/repository", StringUtils.upperCaseFirstAndUnderlineToCamelCase(classInfo.getTableName()) + "Repository.java");
            FileWriter((String)result.get("mapper"), paramInfo.getProjectPath() + "/src/main/java/" + paramInfo.getPackageName(), paramInfo.getModuleName() + "/mapper", "I" + StringUtils.upperCaseFirstAndUnderlineToCamelCase(classInfo.getTableName()) + "Mapper.java");
            FileWriter((String)result.get("mybatis"), paramInfo.getProjectPath() + "/src/main/resources", "/mapper/" + paramInfo.getModuleName(), StringUtils.upperCaseFirstAndUnderlineToCamelCase(classInfo.getTableName()) + "Mapper.xml");
            FileWriter((String)result.get("po"), paramInfo.getProjectPath() + "/src/main/java/" + paramInfo.getPackageName(), paramInfo.getModuleName() + "/entity/po", StringUtils.upperCaseFirstAndUnderlineToCamelCase(classInfo.getTableName()) + "PO.java");
            FileWriter((String)result.get("vo"), paramInfo.getProjectPath() + "/src/main/java/" + paramInfo.getPackageName(), paramInfo.getModuleName() + "/entity/vo", StringUtils.upperCaseFirstAndUnderlineToCamelCase(classInfo.getTableName()) + "VO.java");
            FileWriter((String)result.get("query_model"), paramInfo.getProjectPath() + "/src/main/java/" + paramInfo.getPackageName(), paramInfo.getModuleName() + "/entity/dto/req", StringUtils.upperCaseFirstAndUnderlineToCamelCase(classInfo.getTableName()) + "QueryDTO.java");
            FileWriter((String)result.get("save_model"), paramInfo.getProjectPath() + "/src/main/java/" + paramInfo.getPackageName(), paramInfo.getModuleName() + "/entity/dto/req", StringUtils.upperCaseFirstAndUnderlineToCamelCase(classInfo.getTableName()) + "SaveDTO.java");
        } catch (TemplateException | IOException var6) {
            var6.printStackTrace();
        }

    }

    public static void FileWriter(String content, String base, String path, String fileName) {
        try {
            base = base.replace(".", "/");
            System.out.println("path:" + base + "/" + path + "/" + fileName);
            File file = new File(base + "/" + path + "/" + fileName);
            file.getParentFile().mkdirs();
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(content);
            bw.close();
            System.out.println("finish");
        } catch (IOException var7) {
            var7.printStackTrace();
        }

    }

    public String getTemplateConfig() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("template.json");
        String templateCpnfig = (String)(new BufferedReader(new InputStreamReader(inputStream))).lines().collect(Collectors.joining(System.lineSeparator()));
        inputStream.close();
        return templateCpnfig;
    }

    public Map<String, String> getResultByParams(Map<String, Object> params) throws IOException, TemplateException {
        FreemarkerUtil freemarkerTool = new FreemarkerUtil();
        Map<String, String> result = new LinkedHashMap(32);
        result.put("tableName", params.get("tableName") + "");
        List<TemplateConfig> templateConfigList = JSON.parseArray(this.getTemplateConfig(), TemplateConfig.class);
        Iterator var5 = templateConfigList.iterator();

        while(var5.hasNext()) {
            TemplateConfig item = (TemplateConfig)var5.next();
            System.out.println("getResultByParams: " + FreemarkerUtil.processString(item.getName() + ".ftl", params));
            result.put(item.getName(), FreemarkerUtil.processString(item.getName() + ".ftl", params));
        }

        return result;
    }
}
