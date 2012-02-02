package com.example.jeedemo.web;

import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
@ManagedBean
@SessionScoped
public class Language{
private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
public Locale getLocale(){
return locale;
}
public String getLanguage(){
return locale.getLanguage();
}
public void setLanguage(String language){
locale = new Locale(language);
FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
}
public void choose(ActionEvent e)
{
FacesContext ctx = FacesContext.getCurrentInstance();
String lang = ctx.getExternalContext().getRequestParameterMap().get("locale");
if(lang!=null)
{
setLanguage(lang);
}

}

}