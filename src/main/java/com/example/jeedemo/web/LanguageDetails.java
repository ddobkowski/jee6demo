package com.example.jeedemo.web;

import java.util.Locale;

public class LanguageDetails {

    private static String locale = Locale.getDefault().getDisplayLanguage();

      public void setLocale(String locale1) {
        this.locale = locale1;
      }

      public synchronized String getLocale() {
        return locale;
      }

      public synchronized String changeLanguage() {
        return "changed";
      }
}