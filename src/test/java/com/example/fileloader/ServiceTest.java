package com.example.fileloader;

import com.example.fileloader.service.FileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ServiceTest {

    FileService service;

    @Autowired
    public ServiceTest(FileService service){
        this.service = service;
    }

    @Test
    public void controllerCheck() throws Exception {
        assertThat(service.loadFileAsResource("test.txt")).isNotNull();
    }

}
