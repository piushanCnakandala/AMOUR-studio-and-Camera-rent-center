package util;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class ValidationUtil {

   public static Object validate(LinkedHashMap<TextField,Pattern> map, JFXButton btn) {
       btn.setDisable(true);
        for (TextField textFieldValue : map.keySet()) {
            Pattern patternValue = map.get(textFieldValue);
            if (!patternValue.matcher(textFieldValue.getText()).matches()) {
                if (! textFieldValue.getText().isEmpty()) {
                    textFieldValue.setStyle("-fx-text-fill: red");
                }
                return textFieldValue;

            }
            textFieldValue.setStyle("-fx-text-fill: black");
        }
        btn.setDisable(false);
        return true;
    }
}
