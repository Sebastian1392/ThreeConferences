package admins;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import persistence.JSONManager;
import persistence.XMLManager;
import presenters.IObserver;

public class Admin extends Thread {

    private static final int CLIENTS_PORT = 3000;
    private static final int CLOSE_CONNECTION_PORT = 3001;
    private static final int ACTION_CONNECTION_PORT = 3002;
    private static final String ADDRESS = "127.0.0.1";
    private static final String UPDATE_TREE_CONFERENCES = "Update Tree Conferences";
    private static final String UPDATE_TREE_PERMISSIONS = "Update Tree Permissions";
    private static final String PERMISSION = "Permission";
    private static final String EMPTY = "";
    private static final String ADMIN = "Admin";

    private Socket socketAdmin;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private boolean serverListen;
    private IObserver presenter;

    public Admin(IObserver presenter) throws UnknownHostException, IOException {
        socketAdmin = new Socket(ADDRESS, CLIENTS_PORT);
        inputStream = new DataInputStream(socketAdmin.getInputStream());
        outputStream = new DataOutputStream(socketAdmin.getOutputStream());
        this.presenter = presenter;
        serverListen = true;
        start();
    }

    public void sendUserName(String adminName) throws IOException {
        outputStream.writeUTF(JSONManager.writeTypeUserAndName(ADMIN,adminName));
    }

    public void closeConnection(String adminName) throws UnknownHostException, IOException {
        Socket socketClose = new Socket(ADDRESS, CLOSE_CONNECTION_PORT); 
        DataOutputStream outputStream = new DataOutputStream(socketClose.getOutputStream());
        outputStream.writeUTF(JSONManager.writeTypeUserAndName(ADMIN,adminName));
        socketClose.close();
    }

    public void sendOptionPermission(String fatherName, String nodeName,String option) throws UnknownHostException, 
    IOException{
        Socket socketAction = new Socket(ADDRESS, ACTION_CONNECTION_PORT); 
        DataOutputStream outputStream = new DataOutputStream(socketAction.getOutputStream());
        outputStream.writeUTF(JSONManager.writeActionPermissionsTree(UPDATE_TREE_PERMISSIONS,EMPTY,option,fatherName,
        nodeName));
        socketAction.close();
    }

    @Override
    public void run() {
        while (serverListen) {
            try {
                if (inputStream.available() > 0) {
                    manageRequest();
                }
                Thread.sleep(500);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void manageRequest() throws IOException {
        String text = inputStream.readUTF();
        switch (JSONManager.readTypeData(text)) {
            case UPDATE_TREE_CONFERENCES:
                manageTreeConferences();
            break;
            case UPDATE_TREE_PERMISSIONS:
                manageTreePermissions();
            break;
            case PERMISSION:
                managePermission();
            break;
        
        }
    }

    private void manageTreeConferences() throws IOException {
        presenter.updateConferencesTree(XMLManager.readConferenceTree(inputStream.readUTF()));
    }
    
    private void manageTreePermissions() throws IOException {
        presenter.updatePermissionsTree(XMLManager.readConferenceTree(inputStream.readUTF()));
    }

    private void managePermission() throws IOException {
        presenter.notifyPermission(JSONManager.readPermission(inputStream.readUTF()));
    }
}
