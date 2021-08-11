package com.rahul.covid19;

public class State_Model {
    private String active;
    private String deaths;
    private String recovered;
    private String state;
    private String confirmed;

    public State_Model(String active, String deaths, String recovered, String state, String confirmed) {
        this.active = active;
        this.deaths = deaths;
        this.recovered = recovered;
        this.state = state;
        this.confirmed = confirmed;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }
}
