//package ru.devolw.tacos.controller;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.hamcrest.Matchers.containsString;
//
///*
//    Тест выполняет GET-запрос на localhost:8080/ и проверяет,
//    что контроллер выбрал имя представления home, а страница
//    содержит фразу "Welcome to..."
// */
//
//@WebMvcTest(HomeController.class) // Тест для `HomeController`
//public class HomeControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc; // Внедряем `MockMvc`
//
//    @Test
//    public void testHomePage() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/"))              // GET на localhost:8080/
//                .andExpect(MockMvcResultMatchers.status().isOk())     // Ожидает ответ 200
//                .andExpect(MockMvcResultMatchers.view().name("home")) // Ожидает имя представления home
//                .andExpect(MockMvcResultMatchers.content().string(    // Ожидает наличие строки "Welcome to..."
//                        containsString("Welcome to...")
//                ));
//    }
//}