package com.example.shoppingmall;

import com.example.shoppingmall.Test.TestController;
import com.example.shoppingmall.domain.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith({SpringExtension.class})
@WebMvcTest(controllers = TestController.class)
@WithMockUser
public class TestControllerTest {

    @Autowired
    MockMvc mockMvc;

//    @BeforeEach
//    public void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(testController).build();
//    }

    @Test
    public void userDtoTest() throws Exception {
        UserDto user = UserDto.builder().name("Dahye").age("28").build();
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(user.toString()));

    }
}
