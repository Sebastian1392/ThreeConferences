package persistence;

import org.json.JSONObject;

public class JSONManagerServer {

    public static final String TYPE_DATA_TAG = "TypeData";
    public static final String USER_TYPE_TAG = "UserType";
    public static final String USER_NAME_TAG = "UserName";
    public static final String TREE_TAG = "Tree";
    public static final String ACTION_TAG = "Action";
    public static final String OPTION_TAG = "Option";
    public static final String FATHER_NAME_TAG = "FatherName";
    public static final String CHILD_NAME_TAG = "ChildName";
    public static final String NODE_NAME_TAG = "NodeName";
    public static final String FILE_NAME_TAG = "FileName";

    public static String[] readUserName(String dataJson){
        JSONObject object = new JSONObject(dataJson);
        return new String[]{object.getString(USER_TYPE_TAG),object.getString(USER_NAME_TAG)};
    }    

    public static String writeTypeData(String data){
        JSONObject object = new JSONObject();
        object.put(TYPE_DATA_TAG, data);
        return object.toString();
    }

    public static String[] readActionConferenceTree(String jsonAction){
        JSONObject object = new JSONObject(jsonAction);
        return new String[]{object.getString(TREE_TAG),object.getString(ACTION_TAG),object.getString(OPTION_TAG),
        object.getString(FATHER_NAME_TAG),object.getString(CHILD_NAME_TAG)};
    }

    public static String writePermissionToUser(String nodeName, String permission){
        JSONObject object = new JSONObject();
        object.put(NODE_NAME_TAG, nodeName);
        object.put(OPTION_TAG, permission);
        return object.toString();
    }

    public static String writeFileName(String fileName){
        JSONObject object = new JSONObject();
        object.put(FILE_NAME_TAG, fileName);
        return object.toString();
    }
}
