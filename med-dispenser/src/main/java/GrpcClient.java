import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.internal.JsonUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rpc.MedicationGrpc;
import rpc.RpcMedication;
import services.RpcMedicationService;

import java.util.Iterator;
import java.util.List;

public class GrpcClient extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainScreen.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Pill Box");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
