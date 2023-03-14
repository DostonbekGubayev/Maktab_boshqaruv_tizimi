package total;

import com.example.schoolmanagmentsystem.Dashboard;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class TotalStudentClass extends Dashboard {

    public static DropShadow DropShadowEffect(){
        DropShadow dropShadow=new DropShadow();
        dropShadow.setRadius(30);
        dropShadow.setColor(Color.valueOf("green"));
        return dropShadow;
    }
}