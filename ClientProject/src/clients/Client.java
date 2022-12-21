package clients;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import persistence.FileManager;
import persistence.JSONManager;
import persistence.XMLManager;
import presenters.IObserver;

public class Client extends Thread {

    private static final int CLIENTS_PORT = 3000;
    private static final int CLOSE_CONNECTION_PORT = 3001;
    private static final int ACTION_CONNECTION_PORT = 3002;
    private static final int PERMISSIONS_CONNECTION_PORT = 3003;
    private static final String ADDRESS = "127.0.0.1";
    private static final String UPDATE_TREE_CONFERENCES = "Update Tree Conferences";
    private static final String PERMISSION = "Permission";
    private static final String USER = "User";
    private static final String REPORT = "Report";

    private Socket socketClient;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private boolean serverListen;
    private IObserver presenter;

    public Client(IObserver presenter) throws UnknownHostException, IOException {
        socketClient = new Socket(ADDRESS, CLIENTS_PORT);
        inputStream = new DataInputStream(socketClient.getInputStream());
        outputStream = new DataOutputStream(socketClient.getOutputStream());
        this.presenter = presenter;
        serverListen = true;
        start();
    }

    public void sendUserName(String userName) throws IOException {
        outputStream.writeUTF(JSONManager.writeTypeUserAndName(USER,userName));
    }

    public void closeConnection(String userName) throws UnknownHostException, IOException {
        Socket socketClose = new Socket(ADDRESS, CLOSE_CONNECTION_PORT); 
        DataOutputStream outputStream = new DataOutputStream(socketClose.getOutputStream());
        outputStream.writeUTF(JSONManager.writeTypeUserAndName(USER,userName));
        socketClose.close();
    }

    public void actionConferenceTree(String option,String action,String fatherName, String childName)throws UnknownHostException, 
    IOException {
        Socket socketAction = new Socket(ADDRESS, ACTION_CONNECTION_PORT); 
        DataOutputStream outputStream = new DataOutputStream(socketAction.getOutputStream());
        outputStream.writeUTF(JSONManager.writeActionConferenceTree(UPDATE_TREE_CONFERENCES,action,option,fatherName,
        childName));
        socketAction.close();
    }

    public void requestPermissions(String userName, String fatherName)throws UnknownHostException, 
    IOException {
        Socket socketRequest = new Socket(ADDRESS, PERMISSIONS_CONNECTION_PORT); 
        DataOutputStream outputStream = new DataOutputStream(socketRequest.getOutputStream());
        outputStream.writeUTF(JSONManager.writePermission(userName,fatherName));
        socketRequest.close();
    }

    public void requestReport(String nodeName) throws UnknownHostException, IOException {
        Socket socketClose = new Socket(ADDRESS, CLOSE_CONNECTION_PORT); 
        DataOutputStream outputStream = new DataOutputStream(socketClose.getOutputStream());
        DataInputStream inputStream = new DataInputStream(socketClose.getInputStream());
        outputStream.writeUTF(JSONManager.writeTypeUserAndName(REPORT,nodeName));
        String path = JSONManager.readFileName(inputStream.readUTF());
        byte[] buffer = new byte[inputStream.readInt()];
        inputStream.read(buffer);
        socketClose.close();
        FileManager.writeFile(path, buffer);
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
                manageTree();
            break;
            case PERMISSION:
                managePermission();
            break;
        }
    }

    private void manageTree() throws IOException {
        presenter.updateTree(XMLManager.readConferenceTree(inputStream.readUTF()));
    }

    private void managePermission() throws IOException {
        presenter.updatePermission(JSONManager.readPermissionToUser(inputStream.readUTF()));
    }
    
}
