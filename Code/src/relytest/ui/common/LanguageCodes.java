/*
 * Copyright (C) 2016 Gabriela Sanchez - Miguel Sanchez
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package relytest.ui.common;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public class LanguageCodes {

    final Map<String, String> map;

    /**
     *
     */
    public LanguageCodes() {
        map = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);

        map.put("af", "Afrikaans ");
        map.put("ar", "Arabic ");
        map.put("be", "Belarusian ");
        map.put("bg", "Bulgarian ");
        map.put("ca", "Catalan ");
        map.put("cs", "Czech ");
        map.put("Cy", "Azeri (Cyrillic) ");
        map.put("Cy", "Serbian (Cyrillic) ");
        map.put("Cy", "Uzbek (Cyrillic) ");
        map.put("da", "Danish ");
        map.put("de", "German ");
        map.put("div", "Dhivehi ");
        map.put("el", "Greek ");
        map.put("en", "English ");
        map.put("es", "Spanish ");
        map.put("et", "Estonian ");
        map.put("eu", "Basque ");
        map.put("fa", "Farsi ");
        map.put("fi", "Finnish ");
        map.put("fo", "Faroese ");
        map.put("fr", "French ");
        map.put("gl", "Galician ");
        map.put("gu", "Gujarati ");
        map.put("he", "Hebrew ");
        map.put("hi", "Hindi ");
        map.put("hr", "Croatian ");
        map.put("hu", "Hungarian ");
        map.put("hy", "Armenian ");
        map.put("id", "Indonesian ");
        map.put("is", "Icelandic ");
        map.put("it", "Italian ");
        map.put("ja", "Japanese ");
        map.put("ka", "Georgian ");
        map.put("kk", "Kazakh ");
        map.put("kn", "Kannada ");
        map.put("ko", "Korean ");
        map.put("kok", "Konkani ");
        map.put("ky", "Kyrgyz ");
        map.put("Lt", "Azeri (Latin) ");
        map.put("lt", "Lithuanian ");
        map.put("Lt", "Serbian (Latin) ");
        map.put("Lt", "Uzbek (Latin) ");
        map.put("lv", "Latvian ");
        map.put("mk", "Macedonian (FYROM)");
        map.put("mn", "Mongolian ");
        map.put("mr", "Marathi ");
        map.put("ms", "Malay ");
        map.put("nb", "Norwegian (Bokmål) ");
        map.put("nl", "Dutch ");
        map.put("nn", "Norwegian (Nynorsk) ");
        map.put("pa", "Punjabi ");
        map.put("pl", "Polish ");
        map.put("pt", "Portuguese ");
        map.put("pt", "Portuguese ");
        map.put("ro", "Romanian ");
        map.put("ru", "Russian ");
        map.put("sa", "Sanskrit ");
        map.put("sk", "Slovak ");
        map.put("sl", "Slovenian ");
        map.put("sq", "Albanian ");
        map.put("sv", "Swedish ");
        map.put("sw", "Swahili ");
        map.put("syr", "Syriac ");
        map.put("ta", "Tamil ");
        map.put("te", "Telugu ");
        map.put("th", "Thai ");
        map.put("tr", "Turkish ");
        map.put("tt", "Tatar ");
        map.put("uk", "Ukrainian ");
        map.put("ur", "Urdu ");
        map.put("vi", "Vietnamese ");
        map.put("zh", "Chinese ");

    }
    
    public String getLanguage(String code) {
        String codeFound = map.get(code);
        if (codeFound == null) {
            codeFound = "Spanish";
        }
        return codeFound;
    }
}
