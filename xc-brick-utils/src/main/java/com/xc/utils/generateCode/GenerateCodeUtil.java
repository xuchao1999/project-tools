//package com.xc.utils.generateCode;
//
//import cn.hutool.core.util.StrUtil;
//
//import com.baomidou.mybatisplus.core.toolkit.StringPool;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.InjectionConfig;
//import com.baomidou.mybatisplus.generator.config.*;
//import com.baomidou.mybatisplus.generator.config.po.TableInfo;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//
//import javax.crypto.spec.OAEPParameterSpec;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * 描述： 代码生成
// *
// * @author xc
// * @date 20220830 11:02:09
// * @since v1.0
// */
//public class GenerateCodeUtil {
//
//    /**
//     * 作者
//     */
//    private final static String AUTHOR = "xc";
//
//    //-------------------------------------各层文件-------------------------------------------
//    /**
//     * 项目路径
//     */
//    private final static String PROJECT_PATH = "D:\\code\\project-tools\\xc-brick-auth";
//    /**
//     * 输出文件路径
//     */
//    private final static String OUTPUT_DIR = "/src/main/java";
//    /**
//     * 包路径
//     */
//    private final static String PARENT_PACKAGE = "com.xc.auth";
//    private final static String PARENT_PACKAGE_temp = "/com/xc/auth";
//
//    // -----------------------------------------------数据库设置-------------------------------------------
//    /**
//     * 数据库url
//     */
//    private final static String URL = "jdbc:mysql://localhost:3306/auth?useUnicode=true&useSSL=false&characterEncoding=utf8";
//    /**
//     * 数据库驱动
//     */
//    private final static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
//    /**
//     * 数据库用户名
//     */
//    private final static String USER_NAME = "root";
//    /**
//     * 数据库密码
//     */
//    private final static String PASSWORD = "root";
//    /**
//     * 表名
//     */
//    private final static List<String> TABLES_NAME = Arrays.asList("auth_menu");
//
//    // ---------------------------------------各层包名-----------------------------
//    private static String ENTITY_PATH = "/data/po/";
//    private static String MAPPER_PATH = "/mapper/";
//    private static String XML_PATH = "/resources/mapper/";
//    private static String SERVICE_PATH = "/service/";
//    private static String SERVICE_IMPL_PATH = "/service/impl/";
//    private static String CONTROLLER_PATH = "/controller/";
//
//
//    // -----------------------------------自定义输出模板和位置----------------------------------------
//    // 文件位置输出模式: file output path = projectPath + XX_OUTPUT_PATH  + File
//    // XX_OUTPUT_PATH = modulePath + packagePath
//    /** entity输出模板 */
//    private static String ENTITY_TEMPLATE = "templates/entity.java.ftl";
//    private static String ENTITY_OUTPUT_PATH = OUTPUT_DIR + PARENT_PACKAGE_temp + ENTITY_PATH;
//    /** mapper.xml输出模板 */
//    private static String XML_TEMPLATE = "templates/mapper.xml.ftl";
//    private static String XML_OUTPUT_PATH = "/src/main" + XML_PATH;
//    /** mapper.java输出模板 */
//    private static String MAPPER_TEMPLATE = "templates/mapper.java.ftl";
//    private static String MAPPER_OUTPUT_PATH = OUTPUT_DIR + PARENT_PACKAGE_temp + MAPPER_PATH;
//    /** service输出模板 */
//    private static String SERVICE_TEMPLATE = "templates/service.java.ftl";
//    private static String SERVICE_OUTPUT_PATH = OUTPUT_DIR + PARENT_PACKAGE_temp + SERVICE_PATH;
//    /** serviceImpl输出模板 */
//    private static String SERVICE_IMPL_TEMPLATE = "templates/serviceImpl.java.ftl";
//    private static String SERVICE_IMPL_OUTPUT_PATH = OUTPUT_DIR + PARENT_PACKAGE_temp + SERVICE_IMPL_PATH;
//    /** controller输出模板 */
//    private static String CONTROLLER_TEMPLATE = "templates/controller.java.ftl";
//    private static String CONTROLLER_OUTPUT_PATH = OUTPUT_DIR + PARENT_PACKAGE_temp + CONTROLLER_PATH;
//
//
//    public static void main(String[] args) {
//        // 全局配置
//        GlobalConfig globalConfig = globalConfig();
//        // 数据源配置
//        DataSourceConfig dataSourceConfig = dataSourceConfig();
//        // 策略配置
//        StrategyConfig strategyConfig = strategyConfig();
//        // 包配置
//        PackageConfig packageConfig = packageConfig();
//        // 模板配置
//        TemplateConfig templateConfig = templateConfig();
//        // 自定义配置
//        InjectionConfig injectionConfig = injectionConfig();
//
//        // 执行
//        new AutoGenerator().setGlobalConfig(globalConfig)
//                .setDataSource(dataSourceConfig)
//                .setStrategy(strategyConfig)
//                .setPackageInfo(packageConfig)
//                // 因为使用了自定义模板,所以需要把各项置空否则会多生成一次
//                .setTemplate(templateConfig)
//                // 使用的模板引擎，如果不是默认模板引擎则需要添加模板依赖到pom
//                .setTemplateEngine(new FreemarkerTemplateEngine())
//                .setCfg(injectionConfig)
//                .execute();
//
//    }
//
//    /**
//     * 全局配置
//     */
//    private static GlobalConfig globalConfig() {
//        return new GlobalConfig()
//                // 打开文件
//                .setOpen(false)
//                // 文件覆盖
//                .setFileOverride(true)
//                // 开启activeRecord模式
//                .setActiveRecord(true)
//                // XML ResultMap: mapper.xml生成查询映射结果
//                .setBaseResultMap(true)
//                // XML ColumnList: mapper.xml生成查询结果列
//                .setBaseColumnList(true)
//                // swagger注解; 须添加swagger依赖
////                .setSwagger2(true)
//                // 作者
//                .setAuthor(AUTHOR)
//                .setServiceName("I%sService")
//                // 设置实体类名称
////                .setEntityName("%sDao")
//                ;
//    }
//
//    /**
//     * 数据源配置
//     */
//    private static DataSourceConfig dataSourceConfig() {
//        return new DataSourceConfig()
//                // 数据库类型
////                .setDbType(DB_TYPE)
//                // 连接驱动
//                .setDriverName(DRIVER_NAME)
//                // 地址
//                .setUrl(URL)
//                // 用户名
//                .setUsername(USER_NAME)
//                // 密码
//                .setPassword(PASSWORD);
//    }
//
//    /**
//     * 策略配置
//     */
//    private static StrategyConfig strategyConfig() {
//        return new StrategyConfig()
//                // 表名生成策略：下划线连转驼峰
//                .setNaming(NamingStrategy.underline_to_camel)
//                // 表字段生成策略：下划线连转驼峰
//                .setColumnNaming(NamingStrategy.underline_to_camel)
//                // 需要生成的表
//                .setInclude(StrUtil.join(",", TABLES_NAME))
//                // 生成controller
//                .setRestControllerStyle(true)
//                // 去除表前缀
//                .setTablePrefix(null)
//                // controller映射地址：驼峰转连字符
//                .setControllerMappingHyphenStyle(true)
//                // 是否启用builder 模式
//                .setEntityBuilderModel(true)
//                // 是否为lombok模型; 需要lombok依赖
//                .setEntityLombokModel(true)
//                // 生成实体类字段注解
////                .setEntityTableFieldAnnotationEnable(true)
//                ;
//    }
//
//    /**
//     * 包配置
//     * 设置包路径用于导包时使用，路径示例：com.path
//     */
//    private static PackageConfig packageConfig() {
//
//        String entity = ENTITY_PATH;
//        String mapper = MAPPER_PATH;
//        String xml = XML_PATH;
//        String service = SERVICE_PATH;
//        String serviceImpl = SERVICE_IMPL_PATH;
//        String controller = CONTROLLER_PATH;
//
//        return new PackageConfig()
//                // 父包名
//                .setParent(PARENT_PACKAGE)
//                .setEntity(entity.replace('/', '.').substring(1, entity.length()-1))
//                .setMapper(mapper.replace('/', '.').substring(1, mapper.length()-1))
//                .setXml(xml.replace('/', '.').substring(1, xml.length()-1))
//                .setService(service.replace('/', '.').substring(1, service.length()-1))
//                .setServiceImpl(serviceImpl.replace('/', '.').substring(1, serviceImpl.length()-1))
//                .setController(controller.replace('/', '.').substring(1, controller.length()-1));
//    }
//
//    /**
//     * 模板配置
//     */
//    private static TemplateConfig templateConfig() {
//        return new TemplateConfig()
//                // 置空后方便使用自定义输出位置
//                .setEntity(null)
//                .setXml(null)
//                .setMapper(null)
//                .setService(null)
//                .setServiceImpl(null)
//                .setController(null);
//    }
//
//    /**
//     * 自定义配置
//     */
//    private static InjectionConfig injectionConfig() {
//        InjectionConfig injectionConfig = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                // 注入配置
//            }
//        };
//        injectionConfig.setFileOutConfigList(fileOutConfigList());
//        return injectionConfig;
////                // 判断是否创建文件
////                .setFileCreate(new IFileCreate() {
////                    @Override
////                    public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
////
////                        // 检查文件目录，不存在自动递归创建
////                        checkDir(filePath);
////
////                        // 指定需要覆盖的文件
////                        // 文件结尾名字参照 全局配置 中对各层文件的命名,未修改为默认值
////                        if (isExists(filePath) && (!filePath.endsWith("Mapper.xml") && !filePath.endsWith("Dao.java") && !filePath.endsWith("Mapper.java"))) {
////                            return false;
////                        }
////
////                        return true;
////                    }
////                })
////                // 自定义输出文件
////                .setFileOutConfigList(fileOutConfigList());
//    }
//
//    /**
//     * 自定义输出文件配置
//     */
//    private static List<FileOutConfig> fileOutConfigList() {
//
//        List<FileOutConfig> list = new ArrayList<>();
//        // 当前项目路径
////        String projectPath = System.getProperty("user.dir");
//
//        // 实体类文件输出
//        list.add(new FileOutConfig(ENTITY_TEMPLATE) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return PROJECT_PATH + ENTITY_OUTPUT_PATH + tableInfo.getEntityName() + StringPool.DOT_JAVA;
//            }
//        });
//        // mapper xml文件输出
//        list.add(new FileOutConfig(XML_TEMPLATE) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return PROJECT_PATH + XML_OUTPUT_PATH + tableInfo.getMapperName() + StringPool.DOT_XML;
//            }
//        });
//        // mapper文件输出
//        list.add(new FileOutConfig(MAPPER_TEMPLATE) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return PROJECT_PATH + MAPPER_OUTPUT_PATH + tableInfo.getMapperName() + StringPool.DOT_JAVA;
//            }
//        });
//        // service文件输出
//        list.add(new FileOutConfig(SERVICE_TEMPLATE) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return PROJECT_PATH + SERVICE_OUTPUT_PATH + tableInfo.getServiceName() + StringPool.DOT_JAVA;
//            }
//        });
//        // service impl文件输出
//        list.add(new FileOutConfig(SERVICE_IMPL_TEMPLATE) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return PROJECT_PATH + SERVICE_IMPL_OUTPUT_PATH + tableInfo.getServiceImplName() + StringPool.DOT_JAVA;
//            }
//        });
//        // controller文件输出
//        list.add(new FileOutConfig(CONTROLLER_TEMPLATE) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return PROJECT_PATH + CONTROLLER_OUTPUT_PATH + tableInfo.getControllerName() + StringPool.DOT_JAVA;
//            }
//        });
//
//        return list;
//    }
//    /**
//     * <p>
//     * 读取控制台内容
//     * </p>
//     *
//     * @param args the input arguments
//     * @author xc
//     * @date 20220830 11:02:09
//     * @since v1.0
//     */
////    public static void main(String[] args) {
////
////        // 代码生成器
////        AutoGenerator mpg = new AutoGenerator();
////
////        // 全局配置
////        GlobalConfig gc = new GlobalConfig();
////
////        //生成路径，一般都是在/src/main/java下面
////        gc.setOutputDir(PROJECT_PATH + OUTPUT_DIR);
////        //设置作者，设置为自己的
////        gc.setAuthor(AUTHOR);
////        gc.setOpen(false);
////        //生成的service接口名字首字母是否为I，这样设置就没有了
////        gc.setServiceName("%sService");
//////        gc.setSwagger2(true); //实体属性 Swagger2 注解
////        //第二次执行生成代码  会将第一次的覆盖掉，true即执行
////        gc.setFileOverride(true);
////        mpg.setGlobalConfig(gc);
////
////
////        // 数据源配置   配置自己的数据库
////        DataSourceConfig dsc = new DataSourceConfig();
////        dsc.setUrl(URL);
////        // dsc.setSchemaName("public");
////        dsc.setDriverName(DRIVER_NAME);
////        dsc.setUsername(USER_NAME);
////        dsc.setPassword(PASSWORD);
////        mpg.setDataSource(dsc);
////
////        // 包配置
////        PackageConfig pc = new PackageConfig();
////        pc.setController("controller");
//////        pc.setModuleName(null); //一般没有模块名，没有的话就可以注释掉
////        pc.setParent(PARENT_PACKAGE);   //配置为自己的包路径
////        mpg.setPackageInfo(pc);
////
////        // 自定义配置
////        InjectionConfig cfg = new InjectionConfig() {
////            @Override
////            public void initMap() {
////                // to do nothing
////            }
////        };
////
////        // 如果模板引擎是 freemarker
////        String templatePath = "/templates/mapper.xml.ftl";
////        // 如果模板引擎是 velocity
////        // String templatePath = "/templates/mapper.xml.vm";
////
////        // 自定义输出配置
////        List<FileOutConfig> focList = new ArrayList<>();
////        // 自定义配置会被优先输出
////        String finalProjectPath = PROJECT_PATH;
////        focList.add(new FileOutConfig(templatePath) {
////            @Override
////            public String outputFile(TableInfo tableInfo) {
////                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
////                return finalProjectPath + "/src/main/resources/mapper/"
////                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
////            }
////        });
////        /*
////        cfg.setFileCreate(new IFileCreate() {
////            @Override
////            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
////                // 判断自定义文件夹是否需要创建
////                checkDir("调用默认方法创建的目录，自定义目录用");
////                if (fileType == FileType.MAPPER) {
////                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
////                    return !new File(filePath).exists();
////                }
////                // 允许生成模板文件
////                return true;
////            }
////        });
////        */
////        cfg.setFileOutConfigList(focList);
////        mpg.setCfg(cfg);
////
////        // 配置模板
////        TemplateConfig templateConfig = new TemplateConfig();
////
////        // 配置自定义输出模板
////        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
////        // templateConfig.setEntity("templates/entity2.java");
////        // templateConfig.setService();
////        // templateConfig.setController();
////
////        templateConfig.setController("/templates/controller.java");
////        templateConfig.setService("/templates/service.java");
////        templateConfig.setServiceImpl("/templates/serviceImpl.java");
////        templateConfig.setXml("/templates/mapper.xml");
////        mpg.setTemplate(templateConfig);
////
////        // 策略配置
////        StrategyConfig strategy = new StrategyConfig();
////        strategy.setNaming(NamingStrategy.underline_to_camel);
////        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//////        strategy.setSuperEntityClass("cn.tedu.entity.BaseEntity");  //自己的父类实体，没有就不需要设置
////        strategy.setEntityLombokModel(true);    //使用lombok
////        strategy.setRestControllerStyle(true);
////        // 公共父类
//////        strategy.setSuperControllerClass("cn.tedu.controller.BaseController"); //自己的父类控制器，没有就不需要设置
////        // 写于父类中的公共字段
//////        strategy.setSuperEntityColumns("id");
////        strategy.setInclude(StrUtil.join(",", TABLES_NAME));
////        strategy.setControllerMappingHyphenStyle(true);
//////        strategy.setTablePrefix(pc.getModuleName() + "_");  //表名前缀
////        mpg.setStrategy(strategy);
////        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
////        mpg.execute();
////    }
//
//}
