package presenters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JTree;

import models.Manager;
import models.OptionsCoferences;
import views.DialogActionNode;
import views.DialogConfirmation;
import views.DialogReport;
import views.WindowMain;

public class Presenter extends MouseAdapter implements ActionListener, IObserver {

    private static final String EMPTY = "";

    private Manager manager;
    private WindowMain window;
    private DialogConfirmation dialogConfirmation;
    private DialogActionNode dialogAction;
    private DialogReport dialogReport;
    private String nodeName;

    public Presenter() {
        manager = new Manager();
        window = new WindowMain(this,this);
        dialogConfirmation = new DialogConfirmation(this);
        dialogAction = new DialogActionNode(window,this);
        dialogReport = new DialogReport(this);
        nodeName = EMPTY;
        windowEvent();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Commands.valueOf(e.getActionCommand())) {
            case LOGIN:
                loginUser();
                break;
            case NO_OPTION:
                closeDialogs();
                break;
            case YES_OPTION:
                closeApp();
                break;
            case ACTION_NODE:
                getActionNode(e);
                break;
            case ADD_NODE:
                addConferenceNode();
                break;
        }
    }

    private void closeDialogs(){
        dialogConfirmation.setVisible(false);
        dialogReport.setVisible(false);
    }

    private void loginUser() {
        try {
            manager.initConnection(this, window.getUserNameLogin());
            window.showMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeApp(){
        try {
            manager.closeConnection();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void windowEvent(){
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                dialogConfirmation.showWarning(window);
            }
        });
    }

    private void getActionNode(ActionEvent e){
        String text = ((JMenuItem)e.getSource()).getText();
        switch (OptionsCoferences.valueOf(OptionsCoferences.getOptionSelected(text).toString())) {
            case ADD_ASSISTANT:
            case ADD_CONFERENCE:
            case ADD_LECTURER:
            case ADD_SUBTOPIC:
            case ADD_TOPIC:
                dialogAction.showDialog(text);
                break;
            case DELETE_ASSISTANT:
            case DELETE_CONFERENCE:
            case DELETE_LECTURER:
            case DELETE_SUBTOPIC:
            case DELETE_TOPIC:
                deleteConferenceNode();
                break;
            case GET_REPORT:
                sendRequestReport();
                break;
            case REQUEST_PERMISSIONS:
                requestPermissions();
                break;
            default:
                break;
        }
    }

    private void sendRequestReport() {
        try {
            manager.sendRequestReport(nodeName);
            dialogReport.showMessage(window);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   

    private void getOptions(MouseEvent e) {
        try{
            Object[] path = ((JTree)e.getSource()).getSelectionPath().getPath();
            nodeName = path[path.length - 1].toString();
            OptionsCoferences[] options = manager.getOptionsNode(nodeName);
            window.showPopupPanel(options,this,e.getComponent(),e.getX(),e.getY());
        }catch(NullPointerException ex){}
    }

    private void addConferenceNode(){
        try {
            String[] data = dialogAction.getData();
            manager.addConferenceNode(data[0], nodeName, data[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteConferenceNode(){
        try {
            manager.deleteConferenceNode(nodeName);
            window.addPermissions(manager.getNodePermissions());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void requestPermissions(){
        try {
            manager.requestPermissions(nodeName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == 3){
            getOptions(e);
        }
    }

    @Override
    public void updateTree(ArrayList<String[]> treeData) {
        manager.generateTree(treeData);
        window.showTree(manager.getRoot());
    }
    
    @Override
    public void updatePermission(String[] data) {
       manager.addPermissionTolist(data[0],data[1]);
       window.addPermissions(manager.getNodePermissions());
    }

    public static void main(String[] args) {
        new Presenter();
    }
}
