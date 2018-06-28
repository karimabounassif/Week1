import java.util.Date;

public class Quote {

    private String symbol;
    private double price;
    private int volume;
    private Date date;

    public String getSymbol(){
        return this.symbol;
    }
    public void setSymbol(String symbol){
        this.symbol = symbol;
    }

    public double getPrice(){
        return this.price;
    }
    public void setPrice(double price){
        this.price = price;
    }

    public int getVolume(){ return this.volume; }
    public void setVolume(int volume){
        this.volume = volume;
    }

    public Date getDate(){ return this.date; }
    public void setDate(Date date){
        this.date = date;
    }

}
