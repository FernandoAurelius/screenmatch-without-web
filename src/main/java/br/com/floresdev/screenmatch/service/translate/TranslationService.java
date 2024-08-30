package br.com.floresdev.screenmatch.service.translate;

import br.com.floresdev.screenmatch.service.DataConverterService;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TranslationService {

    public static String getTranslation(String text, String language) {
        text = URLEncoder.encode(text, StandardCharsets.UTF_8);
        String langpair = URLEncoder.encode("autodetect|" + language, StandardCharsets.UTF_8);
        String url = "https://api.mymemory.translated.net/get?q=" + text + "&langpair=" + langpair;
        return DataConverterService.convertData(url, TranslationData.class).responseData().translatedText();
    }

}
