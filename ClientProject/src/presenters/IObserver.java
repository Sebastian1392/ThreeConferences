package presenters;

import java.util.ArrayList;

public interface IObserver {

    public void updateTree(ArrayList<String[]> treeData);

    public void updatePermission(String[] data);
    
}
