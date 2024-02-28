package pojoCreateAMI;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
	private List<DbData> db_data;
	private List<UpdatedData> updated_data;
	private SqsData sqs_data;

	public List<DbData> getDb_data() {
		return db_data;
	}

	public void setDb_data(List<DbData> db_data) {
		this.db_data = db_data;
	}

	public List<UpdatedData> getUpdated_data() {
		return updated_data;
	}

	public void setUpdated_data(List<UpdatedData> updated_data) {
		this.updated_data = updated_data;
	}

	public SqsData getSqs_data() {
		return sqs_data;
	}

	public void setSqs_data(SqsData sqs_data) {
		this.sqs_data = sqs_data;
	}

}
