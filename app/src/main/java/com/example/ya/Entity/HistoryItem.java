package com.example.ya.Entity;

public class HistoryItem {
    private int id;
    private String searchWord;
    private String searchLang;
    private String translatedWord;
    private String translatedLang;
    private Boolean favorite = false;

    public HistoryItem() {
    }

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public String getSearchLang() {
        return searchLang;
    }

    public void setSearchLang(String searchLang) {
        this.searchLang = searchLang;
    }

    public String getTranslatedWord() {
        return translatedWord;
    }

    public void setTranslatedWord(String translatedWord) {
        this.translatedWord = translatedWord;
    }

    public String getTranslatedLang() {
        return translatedLang;
    }

    public void setTranslatedLang(String translatedLang) {
        this.translatedLang = translatedLang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return searchWord + " ---> " + translatedWord;
    }
}
