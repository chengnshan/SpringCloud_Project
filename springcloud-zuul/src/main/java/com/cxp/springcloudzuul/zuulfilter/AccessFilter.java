package com.cxp.springcloudzuul.zuulfilter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 程
 * @date 2019/7/26 上午10:11
 */
@Slf4j
public class AccessFilter extends ZuulFilter {

    /**
     * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
             * pre：可以在请求被路由之前调用
             * routing：在路由请求时候被调用
             * post：在routing和error过滤器之后被调用
             * error：处理请求时发生错误时被调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filterOrder：通过int值来定义过滤器的执行顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可实现过滤器的开关。在上例中，我们直接返回true，所以该过滤器总是生效。
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可实现过滤器的开关。在上例中，我们直接返回true，所以该过滤器总是生效。
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String accessToken = request.getParameter("accessToken");

        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        if(accessToken == null) {
            log.warn("access token is empty");
            currentContext.getResponse().setCharacterEncoding("utf-8");
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
            currentContext.setResponseBody("failt 认证失败,access token is empty");
            return null;
        }
        log.info("access token ok");
        return null;
    }
}
