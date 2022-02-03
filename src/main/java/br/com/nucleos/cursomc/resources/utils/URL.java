package br.com.nucleos.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

   public static List<Long> decodeLongList(String stringList) {
      if (stringList != null && !stringList.isBlank()) {
         return Arrays.asList(stringList.split(",")).stream().map(i -> Long.parseLong(i)).collect(Collectors.toList());
      } else {
         return null;
      }
   }

   public static String decodeParam(String nome) {
      try {
         return URLDecoder.decode(nome, "UTF-8");
      } catch (UnsupportedEncodingException e) {
         return "";
      }
   }

}
