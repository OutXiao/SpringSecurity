package club.wenfan.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.xml.crypto.Data;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

/**
 * @author:wenfan
 * @description:
 * @data: 2018/12/11 22:01
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserController {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup(){

        mockMvc= MockMvcBuilders.webAppContextSetup(wac).build();
    }

    //@Test
    public void whenQuerySuccess(){
        try {
           String result= mockMvc.perform(MockMvcRequestBuilders.get("/user")
                    .param("username","java")
                    .param("password","123")
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
                   .andReturn().getResponse().getContentAsString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void WhenGetInfoSuccess() throws  Exception{
        String result= mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();


        System.out.println(result);
    }

    //@Test
    public void WhenCreateFail() throws  Exception{

        String content="{\"username\":\"tom\",\"password\":null,\"age\":23,\"salary\":2000}";
        String result= mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(content)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    //@Test
    public void WhenUpdateUser() throws  Exception{

        String content="{\"username\":,\"password\":null,\"age\":23,\"salary\":2000}";
        String result= mockMvc.perform(put("/user").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

}