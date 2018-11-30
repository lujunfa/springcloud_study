package org.junfalu.springcloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: lujunfa  2018/11/30 17:18
 * 为了防止自定义的pre过滤器抛出异常但是得不到SendErrorFilter过滤器的处理，这里可以自定义一个Error类型过滤器
 */
@Component
public class ErrorFilter extends ZuulFilter {
    Logger log = LoggerFactory.getLogger(ErrorFilter.class);
    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        Throwable throwable = ctx.getThrowable();
        log.error("this is Error filter :{}",throwable.getCause().getMessage());
        ctx.set("error.status_code",HttpServletResponse.SC_INTERNAL_SERVER_ERROR); //要想自定义过滤器发生异常时被SendErrorFilter
        ctx.set("error.exception",throwable.getCause());
        return null;
    }
}
