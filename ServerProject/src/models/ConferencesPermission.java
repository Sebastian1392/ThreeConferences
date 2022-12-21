package models;

public class ConferencesPermission implements IPermission{

    

    private String conferenceName;

    public ConferencesPermission(String conferenceName){
        this.conferenceName = conferenceName;
    }

    @Override
    public boolean isFather() {
        return true;
    }

    @Override
    public String getName() {
        return conferenceName;
    }

    @Override
    public String getTag() {
        return TagConstants.CONFERENCES_PERMISSION_TAG;
    }
    
}
