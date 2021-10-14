package ro.utcn.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class MonitoredData {
	private Long startTime;
	private Long endTime;
	private String activityLabel;
}