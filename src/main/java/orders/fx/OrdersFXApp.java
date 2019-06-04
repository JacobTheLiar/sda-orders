package orders.fx;

import javafx.application.Application;
import javafx.stage.Stage;
import orders.core.MyApplication;
import orders.core.StorageCustomer;
import orders.core.StorageOrder;

public class OrdersFXApp extends Application implements MyApplication {

    private final StorageCustomer storageCustomer;
    private final StorageOrder storageOrder;

    private Stage primaryStage;

    public OrdersFXApp(StorageOrder storageOrder, StorageCustomer storageCustomer) {
        super();
        this.storageCustomer = storageCustomer;
        this.storageOrder = storageOrder;
    }

    @Override
    public void run() {
        Application.launch("");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("zamówienia");

    }
}

/*


public class Mediator extends Application {

    private DrawAppFx daf;
    private MainWindowFX mainWindow;
    private Stage primaryStage;
    public Mediator(){
        daf = new DrawAppFx(this);
        mainWindow = new MainWindowFx(this);

    }
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        mainWindow.start(primaryStage);
    }
    public void changeToDaf(){
        daf.start(primaryStage);
    }
}
 */