package au.com.unico.unit.service;

import au.com.unico.dao.GcdRepository;
import au.com.unico.message.consumer.JMSConsumer;
import au.com.unico.service.impl.GcdServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

/**
 * Created by xiaofei on 2017/12/11.
 */
@RunWith(SpringRunner.class)
public class GcdServiceTest {
    @InjectMocks
    private GcdServiceImpl gcdService;

    @Mock
    private GcdRepository gcdRepository;

    @Mock
    private JMSConsumer consumer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetGcd() {
        when(consumer.getMessage()).thenReturn("15").thenReturn("20");
        Optional<Integer> optional = gcdService.getGcd();

        verify(consumer, times(2)).getMessage();

        Assert.assertEquals(true, optional.isPresent());
        Assert.assertEquals(new Integer(5), optional.get());
    }

    @Test
    public void testGetGcdWithNumber0() {
        when(consumer.getMessage()).thenReturn("0").thenReturn("20");
        Optional<Integer> optional = gcdService.getGcd();

        verify(consumer, times(2)).getMessage();

        Assert.assertEquals(true, optional.isPresent());
        Assert.assertEquals(new Integer(20), optional.get());
    }

    @Test
    public void testGetGcdWithErrorsWhenGettingMessageFromQueue() {
        when(consumer.getMessage()).thenThrow(new RuntimeException());
        Optional<Integer> optional = gcdService.getGcd();

        verify(consumer).getMessage();

        Assert.assertEquals(false, optional.isPresent());
    }
}
