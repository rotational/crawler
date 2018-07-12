package com.marfeel.spring.data.model;


import javax.persistence.*;


@Entity
public class Website {

    @Version
    private long version;

    protected Website() {}

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String URL;
    private Long rank;
    private Boolean isMarfeelizable;


    public String getURL() {
        return URL;
    }
    public void setURL(String URL) {
        this.URL = URL;
    }

    public Long getRank() { return rank; }
    public void setRank(Long rank) {this.rank = rank; }

    public Boolean getMarfeelizable() {
        return isMarfeelizable;
    }
    public void setMarfeelizable(Boolean marfeelizable) {
        isMarfeelizable = marfeelizable;
    }


    public Website(String URL, Long rank, Boolean isMarfeelizable) {

        this.URL = URL;
        this.rank = rank;
        this.isMarfeelizable = isMarfeelizable;

    }
}
