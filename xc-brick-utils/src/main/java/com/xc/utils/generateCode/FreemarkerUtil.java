package com.xc.utils.generateCode;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

public class FreemarkerUtil {
    private static Configuration freemarkerConfig;

    public FreemarkerUtil() {
    }

    public static String escapeString(String originStr) {
        return originStr.replaceAll("井", "\\#").replaceAll("￥", "\\$");
    }

    public static String processTemplateIntoString(Template template, Object model) throws IOException, TemplateException {
        StringWriter result = new StringWriter();
        template.process(model, result);
        return result.toString();
    }

    public static String processString(String templateName, Map<String, Object> params) throws IOException, TemplateException {
        Template template = freemarkerConfig.getTemplate(templateName);
        return escapeString(processTemplateIntoString(template, params));
    }

    static {
        freemarkerConfig = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

        try {
            freemarkerConfig.setClassForTemplateLoading(FreemarkerUtil.class, "/templates");
            freemarkerConfig.setTemplateLoader(new ClassTemplateLoader(FreemarkerUtil.class, "/templates"));
            freemarkerConfig.setNumberFormat("#");
            freemarkerConfig.setClassicCompatible(true);
            freemarkerConfig.setDefaultEncoding("UTF-8");
            freemarkerConfig.setLocale(Locale.CHINA);
            freemarkerConfig.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        } catch (Exception var1) {
            var1.printStackTrace();
        }

    }
}
