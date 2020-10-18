package lk.rc.ms.apigateway.filter;

import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
public class PostZuulFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
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
    public Object run() throws ZuulException {
        System.out.println("Hit this method while returning response from relevant service");

        RequestContext context = RequestContext.getCurrentContext();
        try (final InputStream responseDataStream = context.getResponseDataStream()) {

            if (responseDataStream == null) return null;

            String responseData = CharStreams.toString(new InputStreamReader(responseDataStream, StandardCharsets.UTF_8));
            context.setResponseBody(responseData);
            System.out.println("Response Data : " + responseData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
