package cl.ido.ruta.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingInfoAspect {

    private static Logger logger = LogManager.getLogger(LoggingInfoAspect.class);
    private static final String IN = "Entrando";
    private static final String OUT = "Saliendo";

    /**
     * Método que logea la entrada y salida de un método.
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("@annotation(LoggingInfo)")
    public Object logMethodIO(final ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info(getLog(IN, joinPoint));
        Object proceed = joinPoint.proceed();
        logger.info(getLog(OUT, joinPoint));
        return proceed;
    }

    private String getLog(final String verb, final ProceedingJoinPoint joinPoint) {
        StringBuilder baseMessageBuilder = new StringBuilder();
        baseMessageBuilder.append(verb);
        baseMessageBuilder.append(" ");
        baseMessageBuilder.append(joinPoint.getSignature().getDeclaringTypeName());
        baseMessageBuilder.append(".");
        baseMessageBuilder.append(joinPoint.getSignature().getName());
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            baseMessageBuilder.append(" | Argumentos: ");
            baseMessageBuilder.append(Arrays.deepToString(joinPoint.getArgs()));
        }
        return baseMessageBuilder.toString();
    }

}

