package models;

public class Root implements IOConference,IPermission{

    private static final String TYPE_CONFERENCE = "Root";

    private String rootName;
    private OptionsCoferences[] options;

    public Root(String rootName){
        this.rootName = rootName;
        options = new OptionsCoferences[]{OptionsCoferences.ADD_TOPIC};
    }

    public Root(String rootName, OptionsCoferences[] options){
        this.rootName = rootName;
        this.options = options;
    }

    @Override
    public boolean isFather() {
        return true;
    }

    @Override
    public String getName() {
        return rootName;
    }

    @Override
    public OptionsCoferences[] getOptions() {
        return options;
    }

    @Override
    public String getTag() {
        return TagConstants.ROOT_TAG;
    }
    
    @Override
    public String typeConference() {
        return TYPE_CONFERENCE;
    }
}
