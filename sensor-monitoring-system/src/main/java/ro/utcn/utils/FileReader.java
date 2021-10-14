package ro.utcn.utils;

import lombok.AllArgsConstructor;
import org.joda.time.DateTime;
import ro.utcn.models.MonitoredData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public class FileReader {

    private final String fileName;

    public List<MonitoredData> readData() {
        Stream<String> stream;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            stream = Files.lines(Paths.get(fileName));

            List<MonitoredData> data = stream
                    .map((s) -> {
                        Date startTime;
                        Date endTime;
                        String activityLabel;
                        Scanner scanner = new Scanner(s);
                        StringBuilder builder = new StringBuilder();

                        try {
                            builder.append(scanner.next());
                            builder.append(" ");
                            builder.append(scanner.next());

                            startTime = format.parse(builder.toString());

                            builder.setLength(0);

                            builder.append(scanner.next());
                            builder.append(" ");
                            builder.append(scanner.next());

                            endTime = format.parse(builder.toString());

                            activityLabel = scanner.next();

                            return new MonitoredData(new DateTime(startTime).toLocalDateTime().toDateTime().getMillis(), new DateTime(endTime).toLocalDateTime().toDateTime().getMillis(), activityLabel);

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .collect(Collectors.toList());

            return data;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
