package br.com.floresdev.screenmatch.service.translate;

import br.com.floresdev.screenmatch.service.DataConverterService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TranslationService {

    private static String getTranslation(String text, String language) throws UnsupportedEncodingException {
        text = URLEncoder.encode(text, "UTF-8");
        String langpair = URLEncoder.encode("en|" + language, "UTF-8");
        String url = "https://api.mymemory.translated.net/get?q=" + text + "&langpair=" + langpair;
        return DataConverterService.convertData(url, TranslationData.class).responseData().translatedText();
    }

}
