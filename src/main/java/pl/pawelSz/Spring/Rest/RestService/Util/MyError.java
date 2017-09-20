package pl.pawelSz.Spring.Rest.RestService.Util;


public class MyError {
	 
    private String errorMessage;
 
    public MyError(String errorMessage){
        this.errorMessage = errorMessage;
    }
 
    public String getErrorMessage() {
        return errorMessage;
    }
 
}