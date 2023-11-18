import com.patikadev.Clone;
import com.patikadev.Helper.DBConnector;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Operator;
import com.patikadev.View.OperatorGUI;

public class Main {
    public static void main(String[] args) {
        //Clone cl = new Clone();
        Helper.setLayout();
        Operator op = new Operator();
        OperatorGUI opGUI = new OperatorGUI(op);


    }
}