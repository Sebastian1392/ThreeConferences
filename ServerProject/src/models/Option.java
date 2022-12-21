package models;

public class Option implements IPermission{

    private String optionName;

    public Option(String conferenceName){
        this.optionName = conferenceName;
    }

    @Override
    public boolean isFather() {
        return true;
    }

    @Override
    public String getName() {
        return optionName;
    }

    @Override
    public String getTag() {
        return TagConstants.OPTION_TAG;
    }
    
}
