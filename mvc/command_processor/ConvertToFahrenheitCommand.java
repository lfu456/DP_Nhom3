package TempConvert.mvc.command_processor;

import TempConvert.mvc.TempConvertModel;

public class ConvertToFahrenheitCommand extends Command{
    private double num;
    
    public ConvertToFahrenheitCommand(TempConvertModel tempConvertModelRemote, 
    double num){
        this.num = num;
        this.tempConvertModelRemote = tempConvertModelRemote;
    }

    @Override
    public void execute() {
        tempConvertModelRemote.convertToFahrenheit(num);
    }

}
