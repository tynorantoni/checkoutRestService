package pl.pawelSz.Spring.Rest.RestService.Util;


public class Error {
	 
    private String errorMessage;
 
    public Error(String errorMessage){
        this.errorMessage = errorMessage;
    }
 
    public String getErrorMessage() {
        return errorMessage;
    }
 
}