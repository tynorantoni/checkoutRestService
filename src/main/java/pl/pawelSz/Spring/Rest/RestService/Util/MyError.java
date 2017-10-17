package pl.pawelSz.Spring.Rest.RestService.Util;

/**
* @author Paweł Szymaszek
* @version 1.1
* @since 17.10.2017
*/

public class MyError {

	private String errorMessage;

	public MyError() {
	}

	public MyError(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}