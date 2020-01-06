package ua.nure.apz.makieiev.apz.filter;

import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Component
public class SecureFilter implements Filter {

	private static final String TOKEN = "Token";
	private static final String MESSAGE_UNAUTHORIZED_PLEASE_AUTHORIZE = "Unauthorized. Please,authorize!";

	@Override
	public void init(FilterConfig filterConfig) {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		if (getTokenFromCookies(request).isPresent()) {
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, MESSAGE_UNAUTHORIZED_PLEASE_AUTHORIZE);
		}
	}

	@Override
	public void destroy() {
	}

	private Optional<Cookie> getTokenFromCookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (Objects.isNull(cookies)) {
			return Optional.empty();
		} else {
			return Arrays.stream(cookies).filter(cookie -> Objects.equals(cookie.getName(), TOKEN)).findFirst();
		}
	}

}