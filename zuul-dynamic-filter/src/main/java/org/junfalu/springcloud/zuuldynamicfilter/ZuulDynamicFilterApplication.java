package org.junfalu.springcloud.zuuldynamicfilter;

import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;
import org.junfalu.springcloud.zuuldynamicfilter.config.FilterConfigration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringCloudApplication
@EnableZuulProxy
@EnableConfigurationProperties(FilterConfigration.class)
public class ZuulDynamicFilterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulDynamicFilterApplication.class, args);
    }

    @Bean
    public FilterLoader filterLoader(FilterConfigration filterConfigration){
        FilterLoader filterLoader = FilterLoader.getInstance();
        filterLoader.setCompiler(new GroovyCompiler());

        FilterFileManager.setFilenameFilter(new GroovyFileFilter());
        try {
            FilterFileManager.init(filterConfigration.getInterval(),
                      filterConfigration.getRoot()+"/pre",
                                    filterConfigration.getRoot()+"/post");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return filterLoader;
    }
}
