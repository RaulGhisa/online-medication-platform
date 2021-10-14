package ro.utcn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.utcn.models.MonitoredData;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MonitoredDataDto {
    private List<MonitoredData> monitoredData;
}
