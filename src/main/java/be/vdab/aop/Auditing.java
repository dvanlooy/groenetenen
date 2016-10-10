package be.vdab.aop;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
class Auditing {
	private final static Logger LOGGER = Logger.getLogger(Auditing.class.getName());

	@AfterReturning(pointcut = "execution(* be.vdab.services.*.*(..))", returning = "returnValue")
	void schrijfAudit(JoinPoint joinPoint, Object returnValue) {
		StringBuilder builder = new StringBuilder("\nTijdstip\t").append(new Date());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && !"anonymousUser".equals(authentication.getName())) {
			builder.append("\nGebruiker\t").append(authentication.getName());
		}
		builder.append("\nMethod\t\t").append(joinPoint.getSignature().toLongString());
		Arrays.stream(joinPoint.getArgs()).forEach(object -> builder.append("\nParameter\t").append(object));
		if (returnValue != null) {
			builder.append("\nReturn\t\t");
			if (returnValue instanceof Collection) {
				builder.append(((Collection<?>) returnValue).size()).append(" objects");
			} else {
				builder.append(returnValue.toString());
			}
		}
		LOGGER.info(builder.toString());
	}
}