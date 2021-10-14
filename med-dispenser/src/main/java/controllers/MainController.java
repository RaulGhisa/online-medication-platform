package controllers;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import rpc.MedicationGrpc;
import rpc.RpcMedication;

import java.net.URL;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    Label clock;

    @FXML
    Label medNameLabel;

    @FXML
    Label startHourLabel;

    @FXML
    Label endHourLabel;

    @FXML
    Button takenButton;

    private Timeline timeline;

    private int counter = 5;

    private MedicationGrpc.MedicationBlockingStub stub;

    private Iterator<RpcMedication.Med> medicationPlan;

    private RpcMedication.Med currentMed;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initClient();
        initClock();
        medicationPlan = getMedicationPlan();

        if (!medicationPlan.hasNext()) return;

        currentMed = updateGui(medicationPlan.next());
        startDispenser();

        takenButton.setStyle("-fx-background-color: #3f51b5; ");
        takenButton.setOnAction(e -> {
                    stub.sendMedicationTaken(RpcMedication.Taken.newBuilder()
                            .setIsTaken(true)
                            .setMedName(currentMed.getMedName())
                            .setPatientId(1970927)
                            .build());
                    counter = -2;
                }
        );
    }

    private Iterator<RpcMedication.Med> getMedicationPlan() {
        Iterator<RpcMedication.Med> medicationList = this.stub.getMedicationPlan(
                RpcMedication.Patient.newBuilder()
                        .setPatientId(1970927)
                        .build());
        return medicationList;
    }

    private void startDispenser() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            if (counter >= 0) {
                takenButton.setText("TAKEN (" + counter-- + ")");
            } else if (counter == -1) {
                stub.sendMedicationTaken(RpcMedication.Taken.newBuilder()
                        .setIsTaken(false)
                        .setMedName(currentMed.getMedName())
                        .setPatientId(1970927)
                        .build());

                if (!medicationPlan.hasNext()) {
                    timeline.stop();
                    medNameLabel.setText("No more meds.");
                    startHourLabel.setText("");
                    endHourLabel.setText("");
                    takenButton.setVisible(false);
                    return;
                }
                counter = 5;
                currentMed = updateGui(medicationPlan.next());
            } else {
                if (!medicationPlan.hasNext()) {
                    timeline.stop();
                    medNameLabel.setText("No more meds.");
                    startHourLabel.setText("");
                    endHourLabel.setText("");
                    takenButton.setVisible(false);
                    return;
                }
                counter = 5;
                currentMed = updateGui(medicationPlan.next());
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private RpcMedication.Med updateGui(RpcMedication.Med med) {
        medNameLabel.setText(med.getMedName());
        startHourLabel.setText(med.getStartHour());
        endHourLabel.setText(med.getEndHour());
        takenButton.setText("TAKEN (" + counter + ")");
        return med;
    }

    private void initClock() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            clock.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void initClient() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8081)
                .usePlaintext()
                .build();
        this.stub = MedicationGrpc.newBlockingStub(channel);
    }
}
