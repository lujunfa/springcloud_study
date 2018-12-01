package org.junfalu.springcloud.zuuldynamicfilter.filter.pre

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.servlet.http.HttpServletRequest

/**
 *
 */

class PreFilter extends ZuulFilter{
    Logger logger = LoggerFactory.getLogger(PreFilter.class);
    @Override
    String filterType() {
        return "pre"
    }

    @Override
    int filterOrder() {
        return 1000
    }

    @Override
    boolean shouldFilter() {
        return true
    }

    @Override
    Object run() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        logger.info("this is a pre filter:Send {} request to{}", request.getMethod(),request.getRequestURI().toString());
        return null
    }
}
