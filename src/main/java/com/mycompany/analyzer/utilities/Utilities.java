package com.mycompany.analyzer.utilities;

import java.util.Arrays;
import java.util.List;

public class Utilities {
	public static List<String> getAsList(String data, String separador) {
        List<String> lista = Arrays.asList(data.split(separador));
        return lista;
    }
}
