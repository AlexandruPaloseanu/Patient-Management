package PatientManagement;

import PatientManagement.GUI.LoginScreen;
import PatientManagement.Services.Services;

public class Main {

    public static void main(String[] args) {

        Services services = new Services();

        LoginScreen loginScreen = new LoginScreen(services);

    }
}
