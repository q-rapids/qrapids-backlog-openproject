package org.qrapids.backlog.openproject.openprojectproxy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class UpdateQRDashboard {

	public static void main(String[] args) {

		LocalDate startDate = LocalDate.of(2018,10,01);
		LocalDate endDate = LocalDate.of(2019,9,19);
		
		
		for(LocalDate d : getDatesBetweenUsingJava8(startDate,endDate)) {
			System.out.println(d.format(DateTimeFormatter.ofPattern("dd-MM-YYYY")));
			try {
				HttpHeaders headers = new HttpHeaders();
				RestTemplate restTemplate = new RestTemplate();

				HttpEntity<String> request = new HttpEntity<String>(headers);
				restTemplate.exchange(
						"http://10.78.1.18:8080/qr-dashboard/api/strategicIndicators/assess?prj=modelio_ng_recalculation&train=NONE&from=" + d.format(DateTimeFormatter.ofPattern("dd-MM-YYYY")),
						HttpMethod.GET, request, Object.class);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static List<LocalDate> getDatesBetweenUsingJava8(LocalDate startDate, LocalDate endDate) {

		long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
		return IntStream.iterate(0, i -> i + 1).limit(numOfDaysBetween).mapToObj(i -> startDate.plusDays(i))
				.collect(Collectors.toList());
	}

}
