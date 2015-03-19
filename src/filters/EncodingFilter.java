package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns={"/*"}, filterName="EncodingFilter", asyncSupported=true)  
public class EncodingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncodingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		if (request instanceof HttpServletRequest
//				&& response instanceof HttpServletResponse) {
//			HttpServletRequest httpReq = (HttpServletRequest) request;
//			HttpServletResponse httpRes = (HttpServletResponse) response;
//			String userid = httpReq.getParameter("userid");
//			String password = httpReq.getParameter("password");
//			if (userid.equals("")||password.equals("")) {
//				httpRes.sendError(HttpServletResponse.SC_FORBIDDEN,
//						"Please input your userid or password");
//				return;
//			}
//		}// 在servlet处理请求之前截获
			// pass the request along the filter chain
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		chain.doFilter(request, response);
		return;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
