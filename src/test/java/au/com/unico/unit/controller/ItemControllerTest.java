package au.com.unico.unit.controller;

import au.com.unico.controller.rest.ItemController;
import au.com.unico.dao.ItemRepository;
import au.com.unico.domain.Item;
import au.com.unico.message.producer.JMSProducer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Created by xiaofei on 2017/12/10.
 */
@RunWith(SpringRunner.class)
public class ItemControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private ItemController itemController;

    @Mock
    private JMSProducer jmsProducer;

    @Mock
    private ItemRepository itemRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
    }

    @Test
    public void testPushMessageWithSuccessfulResponse() throws Exception {
        when(jmsProducer.sendMessage(anyString())).thenReturn(true);

        mockMvc.perform(post("/item/push")
                .param("i1", "1")
                .param("i2", "2"))
                .andDo(print())
                .andExpect(content().string(equalTo("Successful")));
        verify(jmsProducer, times(2)).sendMessage(anyString());
    }

    @Test
    public void testPushMessageWithFailedResponse() throws Exception {
        when(jmsProducer.sendMessage(anyString())).thenReturn(false);

        mockMvc.perform(post("/item/push")
                .param("i1", "1")
                .param("i2", "2"))
                .andDo(print())
                .andExpect(content().string(equalTo("Failed")));
        verify(jmsProducer).sendMessage(anyString());
    }

    @Test
    public void testListWithEmptyResult() throws Exception {
        when(itemRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/item/all"))
                .andDo(print())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string(equalTo("[]")));
        verify(itemRepository).findAll();
    }

    @Test
    public void testListWithOneResult() throws Exception {
        Item item = new Item(1);
        item.setId(1L);
        when(itemRepository.findAll()).thenReturn(Arrays.asList(item));

        mockMvc.perform(get("/item/all"))
                .andDo(print())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string(equalTo("[1]")));
        verify(itemRepository).findAll();
    }
}
