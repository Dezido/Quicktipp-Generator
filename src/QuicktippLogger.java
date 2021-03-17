import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class QuicktippLogger {
	
	/*
	 * richtet den Logger ein
	 */
	public static Logger setupLogger()
	{	
		final Logger logger = Logger.getLogger(QuicktippLogger.class.getName());
		logger.setLevel(Level.ALL);			
		try {
			FileHandler fh;
			fh = new FileHandler("QuicktippLogger.log", true);
			fh.setLevel(Level.FINE);
			fh.setEncoding("UTF-8");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();  
			fh.setFormatter(formatter);  
		} catch (IOException e) {
			logger.log(Level.SEVERE, "QuicktippLogger funktioniert nicht", e);
		}
		logger.setUseParentHandlers(false);
		return logger;		
	}
}
