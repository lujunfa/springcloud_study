package org.junfalu.springcloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: lujunfa  2018/11/30 17:01
 * 模拟自定义的pre过滤器在发生异常时，如果不在请求上下文设置error.status_code是不会被SendErrorFilter过滤器捕获并返回给前端的
 */
/*@Component*/
public class ThrowExceptionFilter extends ZuulFilter {
    Logger logger = LoggerFactory.getLogger(ThrowExceptionFilter.class);
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        logger.info("this is a pre filter ,it will throw a runtimeException");
        RequestContext context = RequestContext.getCurrentContext();
        try {
            doSomething();
        }catch (Exception e){
            context.set("error.status_code",HttpServletResponse.SC_INTERNAL_SERVER_ERROR); //要想自定义过滤器发生异常时被SendErrorFilter
            context.set("error.exception",e);                                              //拦截报告，就必须往请求上下文填入error.status_code值
        }
        return null;
    }

    public void doSomething() throws Exception{
        throw new Exception("test Exception");
    }
}
