package models;

import java.util.ArrayList;

public class Permission {

    private String nodeName;
    private ArrayList<OptionsCoferences> optionsAvailables;
    

    public Permission(String nodeName, OptionsCoferences option) {
        this.optionsAvailables = new ArrayList<>();
        optionsAvailables.add(option);
        this.nodeName = nodeName;
    }

    public ArrayList<OptionsCoferences> getOptionsAvailables() {
        return optionsAvailables;
    }

    public void setOptionsAvailables(ArrayList<OptionsCoferences> optionsAvailables) {
        this.optionsAvailables = optionsAvailables;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public void addOption(OptionsCoferences option){
        optionsAvailables.add(option);
    }
}
