package cl.ido.ruta.aspect;

import cl.ido.ruta.dummies.DummySignature;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.reflect.Field;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoggingInfoAspectTest {

    private LoggingInfoAspect aspect;

    @Mock
    private ProceedingJoinPoint proceedingJoinPoint;

    @Mock
    Logger logger;

    @Before
    public void setUp() throws Throwable {
        aspect = new LoggingInfoAspect();
        Signature dummySignature = new DummySignature();
        when(proceedingJoinPoint.getSignature()).thenReturn(dummySignature);
        when(proceedingJoinPoint.proceed()).thenReturn(new String("whatever"));
    }

    @Test
    public void shouldProceedExecution() throws Throwable {
        Object obj = aspect.logMethodIO(proceedingJoinPoint);
        Assert.assertNotNull(obj);
    }

    @Test
    public void shouldVerifyLogInvocation() throws Throwable {
        Class<?> clazz = LoggingInfoAspect.class;
        Object obj = clazz.newInstance();
        Field field = obj.getClass().getDeclaredField("logger");
        field.setAccessible(true);
        field.set(obj, logger);

        aspect.logMethodIO(proceedingJoinPoint);
        verify(logger, times(2)).info(anyString());
    }
}

