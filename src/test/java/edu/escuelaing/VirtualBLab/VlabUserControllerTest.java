package edu.escuelaing.VirtualBLab;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.escuelaing.VirtualBLab.Controllers.VlabUserController;

@SpringBootTest
public class VlabUserControllerTest {

    @Autowired
	private VlabUserController controller;

    @Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
}
