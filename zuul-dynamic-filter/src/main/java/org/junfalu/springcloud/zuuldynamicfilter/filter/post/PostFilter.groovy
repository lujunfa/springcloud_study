package org.junfalu.springcloud.zuuldynamicfilter.filter.post

import com.netflix.ribbon.proxy.annotation.Http
import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.junfalu.springcloud.zuuldynamicfilter.filter.pre.PreFilter
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class PostFilter extends  ZuulFilter{
    Logger logger = LoggerFactory.getLogger(PreFilter.class);
    @Override
    String filterType() {
        return "post";
    }

    @Override
    int filterOrder() {
        return 1001
    }

    @Override
    boolean shouldFilter() {
        return true;
    }

    @Override
    Object run() {
        HttpServletResponse response = RequestContext.getCurrentContext().getResponse();
        response.getOutputStream().print(", i am  lujunfa")
        response.flushBuffer()
        return null
    }
}
