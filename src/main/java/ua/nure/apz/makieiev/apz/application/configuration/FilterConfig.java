package ua.nure.apz.makieiev.apz.application.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.nure.apz.makieiev.apz.filter.SecureFilter;
import ua.nure.apz.makieiev.apz.util.constant.RequestMappingLink;

@Configuration
public class FilterConfig {

	private static final String ALL_PAGES = "/*";

	@Bean
	public FilterRegistrationBean<SecureFilter> securityFilter() {
		FilterRegistrationBean<SecureFilter> securityFilterFilterRegistrationBean = new FilterRegistrationBean<>();
		securityFilterFilterRegistrationBean.setFilter(new SecureFilter());
		securityFilterFilterRegistrationBean.addUrlPatterns(RequestMappingLink.ACHIEVEMENT + ALL_PAGES);
		securityFilterFilterRegistrationBean.addUrlPatterns(RequestMappingLink.COMPANY + ALL_PAGES);
		securityFilterFilterRegistrationBean.addUrlPatterns(RequestMappingLink.USER + ALL_PAGES);
		securityFilterFilterRegistrationBean.addUrlPatterns(RequestMappingLink.EVENT + ALL_PAGES);
		securityFilterFilterRegistrationBean.addUrlPatterns(RequestMappingLink.GIFT + ALL_PAGES);
		securityFilterFilterRegistrationBean.addUrlPatterns(RequestMappingLink.TASK + ALL_PAGES);
		return securityFilterFilterRegistrationBean;
	}

}