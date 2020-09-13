package com.example.fileloader;

import com.example.fileloader.controller.FileUploadController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ControllerTest {


    private FileUploadController controller;

    @Autowired
    public ControllerTest(FileUploadController controller){
        this.controller = controller;
    }

    @Test
    public void controllerCheck() throws Exception {
        assertThat(controller).isNotNull();
    }

}
