package cl.ido.ruta.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoggingInfoAspectTest {

    private LoggingInfoAspect aspect;

    @Mock
    private ProceedingJoinPoint proceedingJoinPoint;

    @Test
    public void shouldProceedExecution() throws Throwable {
        aspect = new LoggingInfoAspect();

        Signature dummySignature = new DummySignature();
        when(proceedingJoinPoint.getSignature()).thenReturn(dummySignature);
        when(proceedingJoinPoint.proceed()).thenReturn(new String("whatever"));

        Object obj = aspect.logMethodIO(proceedingJoinPoint);
        Assert.assertNotNull(obj);
    }

}

/**
 * El propósito de esta clase para poder maquetear "getSignature()"
 */
class DummySignature implements Signature {

    @Override
    public String toShortString() {
        return "shortString";
    }

    @Override
    public String toLongString() {
        return "longString";
    }

    @Override
    public String getName() {
        return "name";
    }

    @Override
    public int getModifiers() {
        return 0;
    }

    @Override
    public Class getDeclaringType() {
        return null;
    }

    @Override
    public String getDeclaringTypeName() {
        return null;
    }
}