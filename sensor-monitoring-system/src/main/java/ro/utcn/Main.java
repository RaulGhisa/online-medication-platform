package ro.utcn;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.joda.time.DateTime;
import ro.utcn.models.MonitoredData;
import ro.utcn.utils.FileReader;
import ro.utcn.dto.MonitoredDataDto;

import java.io.IOException;
import java.util.List;

public class Main {
    private final static String FILE_NAME = "activity.txt";
    private final static String QUEUE_NAME = "add-monitored-data";

    public static void main(String[] argv) throws Exception {
        FileReader fileReader = new FileReader(FILE_NAME);
        List<MonitoredData> monitoredData = fileReader.readData();

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            Gson gson = new Gson();

            monitoredData.forEach((e) -> {
                String message = gson.toJson(e);
                System.out.println(message);
                try {
                    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            });
        }
    }
}
