package mg.comteen.server.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class ResponseDto<T> {
	
	private boolean status = true;
	
	private String message = "Operation done successfully";
	
	private T data;
	
	public ResponseDto() {
		
	}
	public ResponseDto(boolean status, String message, T data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
