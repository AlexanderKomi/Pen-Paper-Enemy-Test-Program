package gui.util;

import javafx.scene.control.TextField;

import java.util.LinkedList;

public final class GuiUtils {

	private GuiUtils(){}

	/** A TextField, used by this method, can only have numbers. No default value is assigned to the field.
	 * */
	public static void setFieldToOnlyNumbers(TextField t) {
		t.textProperty().addListener((observable, oldValue, newValue) -> {

			if (!newValue.matches("\\d*")) {
				t.setText(newValue.replaceAll("[^\\d]", ""));
			}
			if (t.getText().equals("")) {
				t.setText("0");
			}

		});
	}

	public static void initialize_NumberFields(LinkedList<TextField> list){
		for(TextField tf : list){
			setFieldToOnlyNumbers( tf);
			tf.setText( "0" );
		}
	}

	public static void initialize_NumberFields( TextField... t){
		for(TextField tf : t){
			setFieldToOnlyNumbers( tf);
			tf.setText( "0" );
		}
	}


}
