package com.xc.utils;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 描述： 代码生成
 *
 * @author xc
 * @date 20220830 11:02:09
 * @since v1.0
 */
public class GenerateCodeUtil {

    /**
     * 项目路径
     */
    private final static String PROJECT_PATH = "D:\\code\\project-tools\\testapp";
    /**
     * 输出文件路径
     */
    private final static String OUTPUT_DIR = "/src/main/java";
    /**
     * 包路径
     */
    private final static String PARENT_PACKAGE = "com.xc.testapp";
    /**
     * 作者
     */
    private final static String AUTHOR = "xc";
    /**
     * 数据库url
     */
    private final static String URL = "jdbc:mysql://localhost:3306/auth?useUnicode=true&useSSL=false&characterEncoding=utf8";
    /**
     * 数据库驱动
     */
    private final static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    /**
     * 数据库用户名
     */
    private final static String USER_NAME = "root";
    /**
     * 数据库密码
     */
    private final static String PASSWORD = "root";
    /**
     * 表名
     */
    private final static List<String> TABLES_NAME = Arrays.asList("auth_menu");

    /**
     * <p>
     * 读取控制台内容
     * </p>
     *
     * @param args the input arguments
     * @author xc
     * @date 20220830 11:02:09
     * @since v1.0
     */
    public static void main(String[] args) {

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();

        //生成路径，一般都是在/src/main/java下面
        gc.setOutputDir(PROJECT_PATH + OUTPUT_DIR);
        //设置作者，设置为自己的
        gc.setAuthor(AUTHOR);
        gc.setOpen(false);
        //生成的service接口名字首字母是否为I，这样设置就没有了
        gc.setServiceName("%sService");
//        gc.setSwagger2(true); //实体属性 Swagger2 注解
        //第二次执行生成代码  会将第一次的覆盖掉，true即执行
        gc.setFileOverride(true);
        mpg.setGlobalConfig(gc);


        // 数据源配置   配置自己的数据库
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(URL);
        // dsc.setSchemaName("public");
        dsc.setDriverName(DRIVER_NAME);
        dsc.setUsername(USER_NAME);
        dsc.setPassword(PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setController("controller");
//        pc.setModuleName(null); //一般没有模块名，没有的话就可以注释掉
        pc.setParent(PARENT_PACKAGE);   //配置为自己的包路径
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        String finalProjectPath = PROJECT_PATH;
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return finalProjectPath + "/src/main/resources/mapper/"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("cn.tedu.entity.BaseEntity");  //自己的父类实体，没有就不需要设置
        strategy.setEntityLombokModel(true);    //使用lombok
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("cn.tedu.controller.BaseController"); //自己的父类控制器，没有就不需要设置
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
        strategy.setInclude(StrUtil.join(",", TABLES_NAME));
        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(pc.getModuleName() + "_");  //表名前缀
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
