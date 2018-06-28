public class quoteAgg {

    private String date;
    private String symbol;
    private double high;
    private double low;
    private int volume;
    private double closing;
    private double monthlyHigh;
    private double monthlyLow;
    private int monthlyVolume;
    private double monthlyClosing;

    public void setSymbol(String s) { this.symbol = s; }
    public String getSymbol(){ return this.symbol; }

    public void setDate(String d) { this.date = d; }
    public String getDate(){ return this.date; }

    public void setHigh(double h){ this.high = h; }
    public double getHigh(){ return this.high; }

    public void setLow(double l){ this.low = l; }
    public double getLow(){ return this.low; }

    public void setVolume(int v){ this.volume = v; }
    public int getVolume(){ return this.volume; }

    public void setClosing(double c){ this.closing = c; }
    public double getClosing(){ return this.closing; }

    public void setMonthlyHigh(double h){ this.monthlyHigh = h; }
    public double getMonthlyHigh(){ return this.monthlyHigh; }

    public void setMonthlyLow(double l){ this.monthlyLow = l; }
    public double getMonthlyLow(){ return this.monthlyLow; }

    public void setMonthlyVolume(int v){ this.monthlyVolume = v; }
    public int getMonthlyVolume(){ return this.monthlyVolume; }

    public void setMonthlyClosing(double c){ this.monthlyClosing = c; }
    public double getMonthlyClosing(){ return this.monthlyClosing; }

    public void displayAgg(){
        System.out.println("Aggregate values for " + symbol + " on " + date + ": \nHigh: " + getHigh() + "\nLow: " + getLow()
                + "\nClosing Price: " + getClosing() + "\nTotal Volume: " + getVolume());
    }

    public void displayMonthlyAgg(){
        String month = this.date.split("-")[1];
        System.out.println("\nAggregate values for " + symbol + " in " + month(month) + ": \nHigh: " + getMonthlyHigh()
                + "\nLow: " + getMonthlyLow() + "\nClosing Price: " + getMonthlyClosing() + "\nVolume: " +
                getMonthlyVolume());
    }

    private static String month(String num){
        switch (num) {
            case "05":
                return "May";
            case "06":
                return "June";
            case "07":
                return "July";
            default: return "x";
        }
    }
}
