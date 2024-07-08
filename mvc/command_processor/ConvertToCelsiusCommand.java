package TempConvert.mvc.command_processor;

import TempConvert.mvc.TempConvertModel;

public class ConvertToCelsiusCommand extends Command{
    private double num;

    public ConvertToCelsiusCommand(TempConvertModel tempConvertModelRemote, 
    double num){
        this.num = num;
        this.tempConvertModelRemote = tempConvertModelRemote;
    }

    @Override
    public void execute() {
        tempConvertModelRemote.convertToCelsius(num);
    }

}
