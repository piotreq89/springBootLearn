package com.example;

import com.example.model.LottoResultModel;
import com.example.repository.LottoResultRepository;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Set;

@SpringBootApplication //określenie że jest to klasa rozruchowa
public class SpringBootLearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLearnApplication.class, args);
	}

	@Bean
	public CommandLineRunner prepareDatabase(LottoResultRepository lottoResultRepository){
		return args -> {
			File urlFile = File.createTempFile("lotto", "url");
			URL url = new URL("http://www.mbnet.com.pl/dl.txt");
//			URL url = new URL("http://englishfortamils.com/comics/index.php?album=Manga/Dragon%20Ball%20Z/Dragon%20Ball%20Z%20v23");
			FileUtils.copyURLToFile(url, urlFile);

			String fileText = FileUtils.readFileToString(urlFile);
			String[] linesText = fileText.split(System.lineSeparator());

			DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");

			for(int x = 0; x < linesText.length; x++){
				String[] dataText = linesText[x].split(" ");
				LottoResultModel lottoResultModel = new LottoResultModel();
				//Parsowanie daty
				DateTime dt = formatter.parseDateTime(dataText[1]);
				lottoResultModel.setLotteryDrawingDate(dt.toDate());
				//Parsowanie liczb
				Set<Integer> lotteryNumbers = new LinkedHashSet();
				String[] numbers = dataText[2].split(",");
				for(int i = 0; i < numbers.length; i++){
					lotteryNumbers.add(Integer.parseInt(numbers[i]));
				}
				lottoResultModel.setNumbers(lotteryNumbers);

				lottoResultRepository.save(lottoResultModel);
			}
			System.out.println("Ilość zapisanych pozycji: " + lottoResultRepository.count());


		};
	}
}
