package models;

public class King {
    private String Name;
    private String NienHieu;
    private String Year;
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getNienHieu() {
        return NienHieu;
    }
    public void setNienHieu(String nienHieu) {
        NienHieu = nienHieu;
    }
    public String getYear() {
        return Year;
    }
    public void setYear(String year) {
        Year = year;
    }
    public King() {
    }
    public King(String name, String nienHieu, String year) {
        Name = name;
        NienHieu = nienHieu;
        Year = year;
    }

    
}
