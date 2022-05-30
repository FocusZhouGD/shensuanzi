package com.example.tools.generator;

import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成
 * 可以使用的
 */

@Slf4j
public class MybatisGenerator {
    //项目全路径吧
    private static final String BASE = "/priactice_9999_tools/tool";
    private static final String DIAN = ".";
    private static final String SUB_MODULE_NAME = "structure";

    public static void main(String[] args) {
        try {
            AutoGenerator generator = new AutoGenerator();
            generator.setGlobalConfig(getGlobalConfig());
            generator.setDataSource(getDataSourceConfig());
            generator.setPackageInfo(getPackageConfig());
            generator.setStrategy(getStrategyConfig());
            //自定义mapper.xml的生成路径
            generator.setCfg(getInjectionConfig());
            // 关闭默认 xml 生成
            TemplateConfig tc = new TemplateConfig();
            tc.setXml(null);
            generator.setTemplate(tc);
            generator.execute();
            log.info("generator success .............");
        } catch (Exception e) {
            log.error(" generator error, msg : {}", e.getMessage());
        }
    }


    private static InjectionConfig getInjectionConfig() {
        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        // 调整 xml 生成目录演示
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return userDir() + BASE + "/src/main/resources/mapper/" + SUB_MODULE_NAME + "/"
                        + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        //判断resources下有没有mappers文件夹，没有就生成mapper文件夹
        File file = new File(userDir() + BASE + "/src/main/resources/mapper/" + SUB_MODULE_NAME);
        if (!file.exists()) {
            file.mkdir();
        }
        return cfg;
    }

    /**
     * 获取全局配置
     *
     * @return 全局配置
     */
    private static GlobalConfig getGlobalConfig() {

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(userDir() + BASE + "/src/main/java");
        // 注释作者姓名
        globalConfig.setAuthor("zgd");
        // 是否打开资源管理器
        globalConfig.setOpen(false);
        // 是否覆盖
        globalConfig.setFileOverride(true);
        // 去掉Service的I前缀
        globalConfig.setServiceName("%sService");
        // 设置id类型
        globalConfig.setIdType(IdType.INPUT);
        // XML ResultMap
        globalConfig.setBaseResultMap(true);
        // XML columList
        globalConfig.setBaseColumnList(true);
        // 关闭XML 二级缓存
        globalConfig.setEnableCache(false);
        return globalConfig;
    }

    /**
     * 获取数据源
     *
     * @return 数据源
     */
    private static DataSourceConfig getDataSourceConfig() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3312/im");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        //本地 root / 123456
        dataSourceConfig.setPassword("123456");
        dataSourceConfig.setDbType(DbType.MYSQL);
        return dataSourceConfig;
    }


    /**
     * 获取包配置
     *
     * @return 包配置
     */
    private static PackageConfig getPackageConfig() {
        PackageConfig packageConfig = new PackageConfig();
        // 父包名称
        packageConfig.setParent("com.tool.zgd");
        // 模块名称
        packageConfig.setModuleName("base");
        //packageConfig.setController("controller");

        packageConfig.setService("service" + DIAN + SUB_MODULE_NAME);
        packageConfig.setServiceImpl("service" + DIAN + SUB_MODULE_NAME + ".impl");
        packageConfig.setMapper("dao" + DIAN + SUB_MODULE_NAME);
        packageConfig.setEntity("entity" + DIAN + SUB_MODULE_NAME);
        return packageConfig;
    }

    /**
     * 获取策略配置
     *
     * @return 策略配置
     */
    private static StrategyConfig getStrategyConfig() {
        // 需要自动生成的表，多表逗号隔开
        String[] tables = new String[]{"dc_clinic_oral_disease_price_dict"};
        StrategyConfig strategyConfig = new StrategyConfig();
        // 是否生成常量字段
        strategyConfig.setEntityColumnConstant(true);
        //大写命名、字段符合大写字母数字下划线命名
        strategyConfig.setCapitalMode(true);
        strategyConfig.setInclude(tables);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);
        //去掉表前缀
        strategyConfig.setTablePrefix("dc_");
        //逻辑删除字段
        //strategyConfig.setLogicDeleteFieldName("is_delete");

        // 自动填充配置
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(new TableFill(blank(), FieldFill.INSERT));
        tableFills.add(new TableFill(blank(), FieldFill.INSERT_UPDATE));
        strategyConfig.setTableFillList(tableFills);
        return strategyConfig;
    }

    /**
     * 空字符
     *
     * @return ""
     */
    private static String blank() {
        return "";
    }

    /**
     * 返回 系统项目目录
     *
     * @return ""
     */
    private static String userDir() {
        return System.getProperty("user.dir");
    }
}
