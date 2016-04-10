package pt.tecnico.myDrive;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.FenixFramework;
import pt.tecnico.myDrive.domain.Directory;
import pt.tecnico.myDrive.domain.MyDriveManager;
import pt.tecnico.myDrive.service.WriteFileService;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;


public class MyDriveApplication{


	private static final String DEBUG_1024_DIR = new String(new char[1025]).replace('\0', 'a');

	private static MyDriveManager manager;
	private static final Logger log = LogManager.getRootLogger();

	public static void main(String[] args) {
		try {
			log.trace("MyDriveApplication.main: Starting application.");

		setup();

		/*if (args.length == 1) {
			File f = new File(args[0]);
			if (f != null)
				xmlScan(f);
		}

		demoCreatePlainFile();
		demoCreateDirectory();
		demoPrintPlainFile();
		demoRemoveDirectory();

		xmlPrint(); // For the purpose of debug, this function drops too much output

		demoRemoveFile();*/
		//test();			// This will throw an InvalidUsernameException, it only serves as a test.
		}finally {
			FenixFramework.shutdown();
		}
	}
	

	@Atomic
	public static void setup(){
		log.trace("MyDriveApplication.setup: Setting up Manager." );
		manager = MyDriveManager.getInstance();
	}

	/*
	@Atomic
	public static void demoCreatePlainFile(){
		log.trace("MyDriveApplication.demoCreatePlainFile: Creating /home/README." );
		manager.createPlainFile("/home/README", "lista de utilizadores");


	}

	
	@Atomic
	public static void demoCreateDirectory(){
		log.trace("MyDriveApplication.demoCreateDirectory: Creating /usr/local/bin." );
		manager.createDirectory("/usr/local/bin");
	}

	
	@Atomic
	public static void demoPrintPlainFile(){
		log.trace("MyDriveApplication.demoPrintPlainFile: Printing /home/README." );
		manager.printPlainFile("/home/README");
	}

	
	@Atomic
	public static void demoRemoveDirectory(){
		log.trace("MyDriveApplication.demoRemoveDirectory: Removing /usr/local/bin." );
		manager.removeFile("/usr/local/bin");
	}

	
	@Atomic
	public static void demoRemoveFile(){
		log.trace("MyDriveApplication.demoRemoveFile: Removing /home/README." );
		manager.removeFile("/home/README");
	}
	*/
	@Atomic
	public static void test(){
		manager.createPlainFile(DEBUG_1024_DIR, "lista de utilizadores");
		manager.addUser("o");
	}
	
	@Atomic
	public static void xmlPrint() {
		log.trace("MyDriveApplication.xmlPrint: " + FenixFramework.getDomainRoot());
		Document doc = manager.xmlExport();
		XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
		try { xmlOutput.output(doc, new PrintStream(System.out));
		} catch (IOException e) { System.out.println(e); }
	}


	@Atomic
	public static void xmlScan(File file) {
		log.trace("MyDriveApplication.xmlScan: " + FenixFramework.getDomainRoot());
		SAXBuilder builder = new SAXBuilder();
		try {
		    Document document = (Document)builder.build(file);
		    manager.xmlImport(document.getRootElement());
		} catch (JDOMException | IOException e) {
		    e.printStackTrace();
		}
	}
	
}
	
