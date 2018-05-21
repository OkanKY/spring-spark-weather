package com.okanky.weather.stream;

import com.okanky.weather.stream.excell.ExcelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SparkExperimentApplicationTests {

	@Autowired
	private ExcelService excelService;

	@Test
	public void contextLoads() {
		excelService.getDataFromExcell();
	}

}
