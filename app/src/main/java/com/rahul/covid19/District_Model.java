package com.rahul.covid19;

public class District_Model {
    private String active;
    private String deaths;
    private String recovered;
    private String district;
    private String confirmed;

    public District_Model(String active, String deaths, String recovered, String district, String confirmed) {
        this.active = active;
        this.deaths = deaths;
        this.recovered = recovered;
        this.district = district;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }
}
