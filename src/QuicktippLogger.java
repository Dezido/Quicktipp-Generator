import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuicktippLogger {
		
	public static Logger setupLogger()
	{	
		final Logger logger = Logger.getLogger(QuicktippLogger.class.getName());
		logger.info("Quicktipp Logger");
		logger.setLevel(Level.ALL);			
		try {
			FileHandler fh;
			fh = new FileHandler("QuicktippLogger.log", false);
			fh.setLevel(Level.FINE);
			logger.addHandler(fh);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "File Logger not working.", e);
		}
		logger.setUseParentHandlers(false);
		return logger;		
	}
}
