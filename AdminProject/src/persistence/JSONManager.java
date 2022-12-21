package persistence;

import org.json.JSONObject;

public class JSONManager {

    public static final String TYPE_DATA_TAG = "TypeData";
    public static final String USER_TYPE_TAG = "UserType";
    public static final String USER_NAME_TAG = "UserName";
    public static final String FATHER_NAME_TAG = "FatherName";
    public static final String TREE_TAG = "Tree";
    public static final String ACTION_TAG = "Action";
    public static final String OPTION_TAG = "Option";
    public static final String CHILD_NAME_TAG = "ChildName";

    public static String readTypeData(String data){
        JSONObject object = new JSONObject(data);
        return object.getString(TYPE_DATA_TAG);
    }

    public static String writeTypeUserAndName(String userType, String userName){
        JSONObject object = new JSONObject();
        object.put(USER_TYPE_TAG, userType);
        object.put(USER_NAME_TAG, userName);
        return object.toString();
    }

    public static String[] readPermission(String jsonPermission){
        JSONObject object = new JSONObject(jsonPermission);
        return new String[]{object.getString(USER_NAME_TAG),object.getString(FATHER_NAME_TAG)};
    }

    public static String writeActionPermissionsTree(String tree, String action, String option, String fatherName, 
    String childName){
        JSONObject object = new JSONObject();
        object.put(TREE_TAG, tree);
        object.put(ACTION_TAG, action);
        object.put(OPTION_TAG, option);
        object.put(FATHER_NAME_TAG, fatherName);
        object.put(CHILD_NAME_TAG, childName);
        return object.toString();
    }
}
