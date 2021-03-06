package com.demon.plugin.translate.trans;


import com.demon.plugin.translate.http.AbstractHttpAttribute;
import com.demon.plugin.translate.http.HttpParams;
import com.demon.plugin.translate.lang.LANG;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AbstractTranslator is an abstract base class for all translators
 * which includes several (abstract) functions. By setting parameters,
 * the request is sent to the target server, and then parse the return
 * result to achieve the the purpose of translation.
 *
 */
public abstract class AbstractTranslator extends AbstractHttpAttribute implements HttpParams {


    public Map<String, String> formData;
    public Map<LANG, String> langMap;

    public AbstractTranslator(String url) {
        super(url);
        this.formData = new HashMap<>();
        this.langMap = new HashMap<>();
        setLangSupport();
    }

    @Override
    public String run(LANG source, String text) {
        return null;
    }

    @Override
    public String run(LANG from, LANG to, String text) {
        String result = "";
        setFormData(from, to, text);
        try {
            result = parses(query());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Initialize the supported language mapping.
     */
    public abstract void setLangSupport();

    /**
     * Get the language currently supported for translation.
     *
     * @return support lang list.
     */
    public List<LANG> getSupportLang() {
        return langData;
    }

    @Override
    public void setFormData(LANG source, String text){}

    @Override
    public abstract void setFormData(LANG from, LANG to, String text);

    @Override
    public abstract String query() throws Exception;

    /**
     * Parse the string to extract the content of interest.
     *
     * @param text the string form of the translated result.
     * @return translation results after parsing.
     * @throws IOException if the parsing fails.
     */
    public abstract String parses(String text) throws IOException;

}
