package org.junfalu.springcloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: lujunfa
 * @Date: 2018/11/29 16:14
 * @Description: zuul路由前端过滤器
 */
public class AccessFilter extends ZuulFilter {
    @Override
    public String filterType() {   //过滤器类型，他决定过滤器在请求的哪个生命周期执行，这里定义为pre代表会在请求被录用之前执行
        return "pre";
    }

    @Override
    public int filterOrder() {   //过滤器的执行顺序，当请求在一个阶段中存在多个过滤器时，需要根据该方法返回的值来依次执行
        return 0;
    }

    @Override
    public boolean shouldFilter() { //判断该过滤器是否需要执行，实际运用中我们可以利用该函数来指定过滤器的有效范围。
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        System.out.println("send {"+ request.getMethod()+"} request to {"+request.getRequestURI()+"}");

        Object token =request.getParameter("accessToken");
        if(token == null){
            System.out.println("acescc token is empty");
            context.setSendZuulResponse(false); //让zuul过滤该请求，不对其进行路由
            context.setResponseStatusCode(401); //返回错误码
            return null;
        }
        System.out.println("access token is ok");
        return null;
    }
}
