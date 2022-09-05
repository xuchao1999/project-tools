package com.xc.utils.generateCode;

import java.util.Arrays;
import java.util.List;

/*
 * 代码生成器
 *
 * @author lisd
 * @date 2021-05-22 15:00
 */

public class CodeGeneratorRun {
//public static void main(String[] args) {
//        //******根据自己需求修改******
//        String url = "jdbc:mysql://127.0.0.1:3306/union-rationalize?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
//        String user = "root";
//        String password = "root";
//
//        //作者
//        String authorName = "makeDoBetter";
//
//        //当前 API 工程名称
//        String projectPathForApi = "D:\\work\\union\\jxcc-union-rationalize\\jxcc-union-rationalize-api";
//        //当前 API 包路径
//        String packageNameForApi = "com.jxcc.union.api";
//        //当前工程名称
//        String projectPath = "D:\\work\\union\\jxcc-union-rationalize\\jxcc-union-rationalize-server";
//        //当前包路径
//        String packageName = "com.jxcc.union";
//        //模块名称（各层指定模块包名）
//        String moduleName = "declare";
//        //表名称
//        String tableName = "union_declare_admin_option";
//        //******根据自己需求修改******
//
//        ParamInfo paramInfo = new ParamInfo();
//        paramInfo.setAuthorName(authorName);
//        paramInfo.setUrl(url);
//        paramInfo.setUser(user);
//        paramInfo.setPassword(password);
//        paramInfo.setDataType("sql");
//        paramInfo.setNameCaseType("CamelCase");
//        paramInfo.setProjectPathForApi(projectPathForApi);
//        paramInfo.setPackageNameForApi(packageNameForApi);
//        paramInfo.setProjectPath(projectPath);
//        paramInfo.setPackageName(packageName);
//        paramInfo.setModuleName(moduleName);
//        paramInfo.setTableName(tableName);
//        paramInfo.setTinyintTransType("Integer");
//        //代码生成类
//        GeneratorService generatorService = new GeneratorService();
//        generatorService.codeGenerate(paramInfo);
//    }


    public static void main(String[] args) {

        List<String> tables = Arrays.asList("auth_menu");
        for(String tableName : tables){
            run(tableName);
        }
    }

    public static void run(String table){
        //******根据自己需求修改******
        String url = "jdbc:mysql://localhost:3306/auth?useUnicode=true&useSSL=false&characterEncoding=utf8";
        String user = "root";
        String password = "root";

        //作者
        String authorName = "xc";

        //当前 API 工程名称
        String projectPathForApi = "D:\\code\\project-tools\\xc-brick-auth";
        //当前 API 包路径
        String packageNameForApi = "com.xc.auth";
        //当前工程名称
        String projectPath = "D:\\code\\project-tools\\xc-brick-auth";
        //当前包路径
        String packageName = "com.xc.auth";
        //模块名称（各层指定模块包名）
        String moduleName = "";
        //表名称
        String tableName = table;
        //******根据自己需求修改******

        ParamInfo paramInfo = new ParamInfo();
        paramInfo.setAuthorName(authorName);
        paramInfo.setUrl(url);
        paramInfo.setUser(user);
        paramInfo.setPassword(password);
        paramInfo.setDataType("sql");
        paramInfo.setNameCaseType("CamelCase");
        paramInfo.setProjectPathForApi(projectPathForApi);
        paramInfo.setPackageNameForApi(packageNameForApi);
        paramInfo.setProjectPath(projectPath);
        paramInfo.setPackageName(packageName);
        paramInfo.setModuleName(moduleName);
        paramInfo.setTableName(tableName);
        paramInfo.setTinyintTransType("Integer");
        //代码生成类
        GeneratorService generatorService = new GeneratorService();
//        CodeGeneratorRun codeGeneratorRun = new CodeGeneratorRun();
        generatorService.codeGenerate(paramInfo);
    }

}
