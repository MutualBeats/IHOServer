package rmihelper;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

/**
 * The Handle of the quick start action
 * @author Saltwater
 * @version 1.0
 */
public class QuickStartController implements Initializable{
	
	@FXML private MenuItem run;
	@FXML private MenuItem restart;
	@FXML private MenuItem off;
	@FXML private MenuItem exit;
	@FXML private MenuItem changeport;
	@FXML private MenuItem reset;
	@FXML private MenuItem about;
	@FXML private ScrollPane news;
	@FXML private TextArea message;
	RMIHelper helper = RMIHelper.getInstance();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	@FXML
	public void handleRun() {
		helper.run();
	}
	
	@FXML
	public void handleRestart() {
		helper.restart();
	}
	
	@FXML
	public void handleOff() {
		helper.off();
	}
	
	@FXML
	public void handleExit() {
		System.out.println("Exit");
		helper.off();
		System.exit(0);
	}
	
	@FXML
	public void handleChangePort() {
		
	}
	
	@FXML
	public void handleReset() {
		
	}
	
	@FXML
	public void handleAbout() {
		
	}
	
	public void sendMessage(String message) {
		this.message.appendText("----------------------------------\r\n");
		SimpleDateFormat df=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String time=df.format(new Date());
		this.message.appendText(time);
		this.message.appendText("\r\n\r\n");
		this.message.appendText(message);
		this.message.appendText("\r\n");
		this.message.appendText("----------------------------------\r\n");
	}
	
	
}
