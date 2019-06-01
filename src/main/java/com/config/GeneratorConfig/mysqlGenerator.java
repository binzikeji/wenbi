package com.config.GeneratorConfig;


import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.config.YmlConfig.YmlProperties;

import java.util.*;

/**
 * @Author 胡字蒙.
 * @Date 2018/11/26   12:17
 */
public class mysqlGenerator {
    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
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

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        /*加载配置属性的值*/
        YmlProperties pro = new YmlProperties();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        final String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");//生成路径
        gc.setAuthor("胡胡道人");//作者
        gc.setOpen(false);
        gc.setFileOverride(true);//文件覆盖
        //gc.setActiveRecord(true);  //是否支持AR模式
        gc.setServiceName("%sService");//设置生成的service是否首字母为 I
        mpg.setGlobalConfig(gc);

        // 从application.yml读取数据源配置
        String driver = pro.getYml("spring.datasource.driver-class-name");
        String url = pro.getYml("spring.datasource.url");
        String username = pro.getYml("spring.datasource.username");
        String password = pro.getYml("spring.datasource.password");
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);   //数据库url
        // dsc.setSchemaName("public");
        dsc.setDriverName(driver);  //数据库驱动
        dsc.setUsername(username);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);

        // 包配置
        final PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent("com.contaat");
        mpg.setPackageInfo(pc);
        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】  ${cfg.abc}
        // 自定义配置
       InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
        //focList.add(new FileOutConfig("/vm/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
   /*   // 自定义 xxListIndex.html 生成
        focList.add(new FileOutConfig("/templates/vm/list.html.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/templates/view/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "ListIndex.html";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);*/

         // 自定义  xxAdd.html 生成
      /*  focList.add(new FileOutConfig("/templates/vm/add.html.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/templates/view/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Add.html";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);*/

      /*   //  自定义 xxUpdate.html生成
        focList.add(new FileOutConfig("/templates/vm/update.html.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/templates/view/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() +"Update.html";
            }
        });*/

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        TemplateConfig tc = new TemplateConfig();
        tc.setController("/templates/muban/controller.java");
        //tc.setService("/templates/vm/service.java");
        //tc.setServiceImpl("/templates/vm/serviceImpl.java");
        //tc.setEntity("/templates/vm/entity.java");
       // tc.setMapper("/templates/vm/mapper.java.vm");
        //tc.setXml("/vm/mapper.xml.vm");

        mpg.setTemplate(new TemplateConfig().setXml(null));
        mpg.setTemplate(tc);//
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);

        /*strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");*/
        strategy.setEntityLombokModel(true);
        //strategy.setRestControllerStyle(true);   //true : 打开RestControll ；默认关闭，打开Controll
        strategy.setInclude(scanner("表名"));
       /* strategy.setSuperEntityColumns("id");*/

        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        //mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }
}
