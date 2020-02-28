package com.hayes.code.cloudzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AccessFilter extends ZuulFilter {
    private final Logger logger = LoggerFactory.getLogger(AccessFilter.class);


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        // 执行顺序 : 当有多个过滤器时
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //该过滤器是否需要被执行
        return false;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext() ;
        HttpServletRequest request = ctx.getRequest() ;

        logger.info("send{} request to{}", request.getMethod() , request.getRequestURL().toString());

        String name = request.getParameter("name");
        if (name == null ){

            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("请求失败");

            logger.info("请求失败");
            return null ;

        }
        logger.info("请求成功");
        return null;
    }
}
