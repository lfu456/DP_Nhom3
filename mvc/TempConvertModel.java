package TempConvert.mvc;

import TempConvert.mvc.observer.Publisher;

public class TempConvertModel extends Publisher{
    //field
    private double result;

    //method


    public void convertToFahrenheit(double num){
        this.result = Math.ceil(num * 1.8 + 32);
        changeState();

    }
    public void convertToCelsius(double num){
        this.result =Math.ceil((num-32) / 1.8);
        changeState();
    }

    public double getResult() {
        return result;
    }

    public void changeState() {
        notifySubcribers();
    }

}
