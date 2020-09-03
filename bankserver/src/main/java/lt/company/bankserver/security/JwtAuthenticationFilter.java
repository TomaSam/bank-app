package lt.company.bankserver.security;

import java.io.IOException;

import static lt.company.bankserver.security.SecurityConstants.HEADER_STRING;
import static lt.company.bankserver.security.SecurityConstants.TOKEN_PREFIX;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lt.company.bankserver.model.User;
import lt.company.bankserver.service.CustomUserDetailsService;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			String jwt = getJWTFromRequest(request);
			
			if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
				Long userId = tokenProvider.getUserFromJWT(jwt);
				User userDetails = customUserDetailsService.loadUserById(userId);
			
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, 
						null, java.util.Collections.emptyList());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		catch (Exception e) {
			logger.error("Could not set user authentication in security context");
		}
		filterChain.doFilter(request, response);
	}	

	private String getJWTFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader(HEADER_STRING);
		
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

}
