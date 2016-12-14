package cn.cslg.WebBookmart.Filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class EncodeFilter implements Filter {
    private String characterEncoding = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        characterEncoding = filterConfig.getInitParameter("characterEncoding");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws ServletException {
        try {
            request.setCharacterEncoding(characterEncoding);
            response.setCharacterEncoding(characterEncoding);
        }catch (UnsupportedEncodingException e){
            System.out.println("Error : Unsupported encoding !");
        }
        try {
            chain.doFilter(request, response);
        }catch (IOException e){
            System.out.println("Error : !");
        }
    }

    public void destroy() {
    }
}
