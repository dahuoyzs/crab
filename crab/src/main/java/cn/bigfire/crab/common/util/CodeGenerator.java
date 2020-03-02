package cn.bigfire.crab.common.util;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Date   ：2019/9/27  16:44
 * @ Desc   ：代码生成器
 */
public class CodeGenerator {

    public static void main(String[] args) {
        AutoGenerator autoGenerator = getAutoGenerator();
        autoGenerator.execute();
    }

    private static AutoGenerator getAutoGenerator() {

        String AUTHOR = "dahuo";
        String DB_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost:3306/crab?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
        String DB_USERNAME = "root";
        String DB_PASSWORD = "123456";
        String PACKAGE_BASE = "cn.bigfire.crab";
        String OUTPUT_DIR = Constant.USER_DIR + "/src/main/java";        //D:/code/crab/crab/src/main/java
        String OUTPUT_DIR_UPDATE = Constant.DIR_CACHE;                  //D:/code/crab/crab/data/cache

        String TPL_CONTROLLER = "/template/controller.java";
        String TPL_ENTITY = "/template/entity.java";
        String TPL_MAPPER = "/template/mapper.java";
        String TPL_MAPPER_XML = "/template/mapper.xml";
        String TPL_SERVICE = "/template/service.java";
        String TPL_SERVICE_IMPL = "/template/serviceImpl.java";

        //---------------------------------------------------------配置上方即可---------------------------------------------------------------------------

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig()
                .setAuthor(AUTHOR)
                .setOutputDir(OUTPUT_DIR_UPDATE)//当前项目目录   如:D:\code\crab\crab-base/src/main/java
                .setOpen(false)
                .setSwagger2(true)
                .setServiceName("%sService");                                   //生成的Service而不是IService
        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig()
                .setDriverName(DB_DRIVER)
                .setUrl(DB_URL)
                .setUsername(DB_USERNAME)
                .setPassword(DB_PASSWORD)
                .setTypeConvert(new MyTypeConvert());
//                .setTypeConvert();
//        new MySqlTypeConvert()
        // 包配置
        PackageConfig packageConfig = new PackageConfig()
                .setModuleName(scanner("模块名"))
                .setParent(PACKAGE_BASE);
        //----------------------------------------------------------------------------------------------自定义Mapper输出路径
        FileOutConfig mapperXmlPathConfig = new FileOutConfig(TPL_MAPPER_XML + ".ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义生成的Mapper.xml到resources/mapper目录下 并拼接文件名  如果Entity设置了前后缀，此处注意 xml 的名称会跟着发生变化！！
                return Constant.USER_DIR + "/src/main/resources/mapper/" + packageConfig.getModuleName() + "/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        };
        List<FileOutConfig> configList = new ArrayList<>();        // 自定义配置会被优先输出
        configList.add(mapperXmlPathConfig);
        //----------------------------------------------------------------------------------------------自定义Mapper输出路径
        // 模板配置
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setController(TPL_CONTROLLER);
        templateConfig.setEntity(TPL_ENTITY);
        templateConfig.setMapper(TPL_MAPPER);
        templateConfig.setXml(null);
        templateConfig.setService(TPL_SERVICE);
        templateConfig.setServiceImpl(TPL_SERVICE_IMPL);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
//        strategy.setSuperControllerClass("cn.bigfire.crab.sys.controller.BaseController");// Controller父类
//        strategy.setSuperEntityClass("cn.bigfire.crab.sys.entity.BaseEntity");              // Entity父类
//        strategy.setSuperEntityColumns("id");                                             // 写于父类中的公共字段
//        strategy.setTablePrefix(pc.getModuleName() + "_");        //去掉表前缀
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));//sys_config,sys_dict,sys_log,sys_menu,sys_role,sys_role_menu,sys_user,sys_user_role
        strategy.setControllerMappingHyphenStyle(true);

        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator()
                .setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setPackageInfo(packageConfig)

                .setCfg(new InjectionConfig() {//设置自定义Mapper.xml输出路径配置
                    @Override
                    public void initMap() {
                    }
                }.setFileOutConfigList(configList))

                .setTemplate(templateConfig)
                .setStrategy(strategy)
                .setTemplateEngine(new FreemarkerTemplateEngine());
        return autoGenerator;
    }

    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    static class MyTypeConvert extends MySqlTypeConvert {
        @Override
        public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
            String t = fieldType.toLowerCase();
            if (t.contains("char")) {
                return DbColumnType.STRING;
            } else if (t.contains("bigint")) {
                return DbColumnType.LONG;
            } else if (t.contains("tinyint(1)")) {
                return DbColumnType.BOOLEAN;
            } else if (t.contains("int")) {
                return DbColumnType.INTEGER;
            } else if (t.contains("text")) {
                return DbColumnType.STRING;
            } else if (t.contains("bit")) {
                return DbColumnType.BOOLEAN;
            } else if (t.contains("decimal")) {
                return DbColumnType.BIG_DECIMAL;
            } else if (t.contains("clob")) {
                return DbColumnType.CLOB;
            } else if (t.contains("blob")) {
                return DbColumnType.BLOB;
            } else if (t.contains("binary")) {
                return DbColumnType.BYTE_ARRAY;
            } else if (t.contains("float")) {
                return DbColumnType.FLOAT;
            } else if (t.contains("double")) {
                return DbColumnType.DOUBLE;
            } else if (t.contains("json") || t.contains("enum")) {
                return DbColumnType.STRING;
            } else if (t.contains("date") || t.contains("time") || t.contains("year")) {
                switch (globalConfig.getDateType()) {
                    case ONLY_DATE:
                        return DbColumnType.DATE;
                    case SQL_PACK:
                        switch (t) {
                            case "date":
                                return DbColumnType.DATE_SQL;
                            case "time":
                                return DbColumnType.TIME;
                            case "year":
                                return DbColumnType.DATE_SQL;
                            default:
                                return DbColumnType.TIMESTAMP;
                        }
                    case TIME_PACK:
                        switch (t) {
                            case "date":
                                return DbColumnType.LOCAL_DATE;
                            case "time":
                                return DbColumnType.LOCAL_TIME;
                            case "year":
                                return DbColumnType.YEAR;
                            default:
                                return DbColumnType.DATE;
                        }
                }
            }
            return DbColumnType.STRING;
        }
    }

}
