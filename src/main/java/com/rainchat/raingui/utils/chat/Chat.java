package com.rainchat.raingui.utils.chat;

import com.rainchat.raingui.utils.placeholder.PlaceholderSupply;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chat {

    private static final Pattern PH_KEY = Pattern.compile("%([\\w\\._-]+)%");


    public static String translateRaw(String template, PlaceholderSupply<?>... replacementSource) {
        Matcher m = PH_KEY.matcher(template);

        while (m.find()) {
            if (replacementSource == null)   break;

            for (PlaceholderSupply<?> e : replacementSource) {
                String replacement = e.getReplacement(m.group(1));

                if (replacement == null) continue;

                if (!replacement.isEmpty()) {
                    template = template.replace(m.group(), replacement);
                    break;
                }
            }
        }

        return Color.parseHexString(template);
    }

    public static List<String> translateRaw(List<String> list, PlaceholderSupply<?>... replacementSource) {
        List<String> tempList = new ArrayList<>();
        list.forEach(template -> tempList.add(translateRaw(template, replacementSource)));
        return tempList;
    }
}
