package com.example.stock.scraper;

import com.example.stock.model.Company;
import com.example.stock.model.Dividend;
import com.example.stock.model.ScrapedResult;
import com.example.stock.model.constant.Month;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class YahooFinanceScraper implements Scraper{
    private static final String STATISTICS_URL = "https://finance.yahoo.com/quote/%s/history?period1=%d&period2=%d&interval=1mo";
    private static final String SUMMARY_URL = "https://finance.yahoo.com/quote/%s?p=%s";
    private static final long START_TIME = 86400;   //60 * 60 * 24

    @Override
    public ScrapedResult scrap(Company company) {
        ScrapedResult scrapedResult = new ScrapedResult();
        scrapedResult.setCompany(company);

        try {
            long now = System.currentTimeMillis() / 1000;
            String url = String.format(STATISTICS_URL, company.getTicker(), START_TIME, now);

            Connection connection = Jsoup.connect(url);
            Document document = connection.get();

            Elements parsingDivs = document.getElementsByAttributeValue("data-test", "historical-prices");
            Element tableElement = parsingDivs.get(0);  //table 전체

            //parsingDivs 리스트와 tableElement 변수에 대한 추가적인 널 체크를 수행
            Element tBody;
            if (tableElement != null) {
                if (tableElement.children().size() >= 2) {
                    tBody = tableElement.children().get(1);
                } else {
                    throw new RuntimeException("테이블의 값이 부족합니다.");
                }
            } else {
                throw new RuntimeException("리스트에 값이 없습니다.");
            }

            List<Dividend> dividends = new ArrayList<>();
            for (Element e : tBody.children()) {
                String text = e.text();
                if (!text.endsWith("Dividend")) {
                    continue;
                }

                //문자 파싱
                String[] splits = text.split(" ");
                int month = Month.strToNumber(splits[0]);
                if (month < 0) {
                    throw new RuntimeException("Unexpected Month enum value ->" + splits[0]);
                }

                int day = Integer.parseInt(splits[1].replace(",", ""));
                int year = Integer.parseInt(splits[2]);
                String dividend = splits[3];

                dividends.add(new Dividend(LocalDateTime.of(year, month, day, 0, 0), dividend));
            }

            scrapedResult.setDividends(dividends);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return scrapedResult;
    }

    @Override
    public Company scrapCompanyByTicker(String ticker) {
        String url = String.format(SUMMARY_URL, ticker, ticker);

        try {
            Document document = Jsoup.connect(url).get();

            Element titleElement = document.getElementsByTag("h1").get(0);
            String title = titleElement.text().split("\\(")[0].trim();

            return Company.builder()
                    .ticker(ticker)
                    .name(title)
                    .build();

        } catch (IOException e) {
            throw new RuntimeException("scrapCompanyByTicker Error : " + e);
        }

    }
}
