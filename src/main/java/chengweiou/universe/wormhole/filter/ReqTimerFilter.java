//package chengweiou.universe.wormhole.filter;
//
//import chengweiou.universe.blackhole.util.LogUtil;
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//public class ReqTimerFilter extends ZuulFilter {
//
//    @Override
//    public String filterType() {
//        return "pre";
//    }
//
//    @Override
//    public int filterOrder() {
//        return 0;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    @Override
//    public Object run() throws ZuulException {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//        HttpServletResponse response = ctx.getResponse();
//
//        LogUtil.i(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
//        LogUtil.i(String.format("%s response status %s", response.getStatus(), response.getContentType()));
//
//        return null;
//    }
//}
