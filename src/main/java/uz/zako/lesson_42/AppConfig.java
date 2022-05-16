package uz.zako.lesson_42;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import uz.zako.lesson_42.config.WebMVCConfig;

public class AppConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
            WebMVCConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebMVCConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
