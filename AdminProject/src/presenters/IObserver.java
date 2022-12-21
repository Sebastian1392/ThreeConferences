package presenters;

import java.util.ArrayList;

public interface IObserver {

    public void updateConferencesTree(ArrayList<String[]> treeData);

    public void updatePermissionsTree(ArrayList<String[]> treeData);

    public void notifyPermission(String[] data);
    
}
