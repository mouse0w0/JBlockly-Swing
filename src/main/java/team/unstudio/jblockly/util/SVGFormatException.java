package team.unstudio.jblockly.util;

public class SVGFormatException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public SVGFormatException() {}
	
	public SVGFormatException(String arg0) {
		super(arg0);
	}
	
	public SVGFormatException(Throwable arg0) {
		super(arg0);
	}
	
	public SVGFormatException(String arg0,Throwable arg1) {
		super(arg0, arg1);
	}

}
